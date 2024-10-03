#include <stdio.h>

#include "codegen.h"
#include "table.h"

FILE *codegenout;  // the output of code generation

#define PUSH(arg1) fprintf(codegenout,  "\tpush\t%s\n", arg1 )
#define POP(arg1) fprintf(codegenout,  "\tpop\t%s\n", arg1 )
#define MOV(arg1, arg2) fprintf(codegenout, "\tmov\t%s, %s\n", arg1, arg2)
#define MOV_FROM_IMMEDIATE(arg1, arg2) fprintf(codegenout, "\tmov\t$%d, %s\n", arg1, arg2)
#define MOV_FROM_OFFSET(offset, reg) fprintf(codegenout, "\tmov\t-%d(%%rbp), %s\n", offset, reg)
#define MOV_TO_OFFSET(reg, offset) fprintf(codegenout, "\tmov\t%s, -%d(%%rbp)\n", reg, offset)
#define MOV_FROM_GLOBAL(reg, global) fprintf(codegenout, "\tmov\t%s(%%rip), %s\n", global, reg)
#define MOV_TO_GLOBAL(reg, global) fprintf(codegenout, "\tmov\t%s, %s(%%rip)\n", reg, global)
#define ADD(arg1, arg2) fprintf(codegenout, "\tadd\t%s, %s\n", arg1, arg2)
#define SUB(arg1, arg2) fprintf(codegenout, "\tsub\t%s, %s\n", arg1, arg2)
#define SUBCONST(arg1, arg2) fprintf(codegenout, "\tsub\t$%d, %s\n", arg1, arg2)
#define IMUL(arg1, arg2) fprintf(codegenout, "\timul\t%s, %s\n", arg1, arg2)
#define CDQ() fprintf(codegenout, "\tcdq\n")
#define IDIV(reg) fprintf(codegenout, "\tidiv\t%s\n", reg)
#define CALL(arg1) fprintf(codegenout, "\tcall\t%s\n", arg1)
#define RET fprintf(codegenout, "\tret\n")
#define COMMENT(arg1) fprintf(codegenout, "\t# %s\n", arg1)

const string const param_registers[] = { "%rdi", "%rsi", "%rdx", "%rcx", "%r8", "%r9"}; // all const

static void codegen_main(T_main main);

static void codegen_decllist(T_decllist decllist);

static void codegen_stmtlist(T_stmtlist stmtlist);

static void codegen_funclist(T_funclist funclist);

static void codegen_func(T_func func);

static void codegen_stmtlist(T_stmtlist stmtlist);

static void codegen_stmt(T_stmt stmt);

static void codegen_assignstmt(T_stmt stmt);

static void codegen_ifstmt(T_stmt stmt);

static void codegen_ifelsestmt(T_stmt stmt);

static void codegen_whilestmt(T_stmt stmt);

static void codegen_compoundstmt(T_stmt stmt);

static void codegen_expr(T_expr expr);

static void codegen_identexpr(T_expr expr);

static void codegen_callexpr(T_expr expr);

static void codegen_intexpr(T_expr expr);

static void codegen_charexpr(T_expr expr);

static void codegen_strexpr(T_expr expr);

static void codegen_arrayexpr(T_expr expr);

static void codegen_unaryexpr(T_expr expr);

static void codegen_binaryexpr(T_expr expr);

static void codegen_castexpr(T_expr expr);

static void emit_prologue(int size);

static void emit_epilogue();

static int bitwidth(T_type type);

typedef struct S_offset_scope *T_offset_scope;

struct S_offset_scope {
  T_table table;
  int stack_size;
  int current_offset;
  T_offset_scope parent;
};

static T_offset_scope create_offset_scope(T_offset_scope parent) {
  T_offset_scope offset_scope = xmalloc(sizeof(*offset_scope));
  offset_scope->table = create_table();
  // start after the dynamic link to the caller's %rbp
  offset_scope->current_offset = 8;
  offset_scope->stack_size = 0;
  offset_scope->parent = parent;
  return offset_scope;
}

static T_offset_scope destroy_offset_scope(T_offset_scope offset_scope) {
  T_offset_scope parent_offset_scope = offset_scope->parent;
  destroy_table(offset_scope->table);
  free(offset_scope);
  return parent_offset_scope;
}

struct S_offset {
  int offset;
};

typedef struct S_offset * T_offset;

static T_table global_symbols;

static T_offset_scope current_offset_scope;

void insert_offset(T_offset_scope scope, string ident, int size) {
  T_offset entry = xmalloc(sizeof(*entry));
  entry->offset = scope->current_offset;
  insert(scope->table, ident, (void*) entry);
  scope->stack_size += size;
  scope->current_offset += size;
}

static int lookup_offset_in_scope(T_offset_scope offset_scope, string ident) {
  T_offset offset = (T_offset) lookup(offset_scope->table, ident);
  if (NULL == offset) {
    fprintf(stderr, "FATAL: symbol not in table.  double-check the code that inserts the symbol into the offset scope.");
  }
  return offset->offset;
}

static T_offset lookup_offset_in_all_offset_scopes(T_offset_scope offset_scope, string ident) {
  // loop over each offset_scope and check for a binding for ident
  while (NULL != offset_scope) {
    // check for a binding in this offset_scope
    T_offset offset = (T_offset) lookup(offset_scope->table, ident);
    if (NULL != offset) {
      return offset;
    }
    // no binding in this offset_scope, so check the parent
    offset_scope = offset_scope->parent;
  }
  // found no binding in any offset_scope
  return NULL;
}

void codegen(T_prog prog) {
  // no need to record symbols in the global offset_scope.  the assembler and linker handle them.
  current_offset_scope = NULL;
  
  // emit assembly header
  fprintf(codegenout, ".file \"stdin\"\n");
  
  // emit a .comm for global vars
  global_symbols = create_table();
  // loop over each global symbol.  emit .comm and add to global symtab
  T_decllist decllist = prog->decllist;
  while (NULL != decllist) {
    if (E_functiontype != decllist->decl->type->kind) {
      fprintf(codegenout, ".comm\t%s,%d\n", decllist->decl->ident, bitwidth(decllist->decl->type));
      insert(global_symbols, decllist->decl->ident, decllist->decl->ident);
    }
    decllist = decllist->tail;
  }

  // optional: emit an .rodata section and label for strings

  // go through each function
  codegen_funclist(prog->funclist);

  // generate the code for main
  codegen_main(prog->main);

  // free the global symbol table
  free(global_symbols);
}

static void codegen_main(T_main main) {
  // create a new scope
  current_offset_scope = create_offset_scope(NULL);

  // emit the pseudo ops for the function definition
  fprintf(codegenout, ".text\n");
  fprintf(codegenout, ".globl %s\n", "main");
  fprintf(codegenout, ".type %s, @function\n", "main");

  // emit a label for the function
  fprintf(codegenout, "%s:\n", "main");

  // add local declarations to the scope
  codegen_decllist(main->decllist);

  COMMENT("stack space for argc and argv");
  insert_offset(current_offset_scope, "argc", 8);  // int argc
  insert_offset(current_offset_scope, "argv", 8);  // char **argv

  COMMENT("emit main's prologue");
  emit_prologue(current_offset_scope->stack_size);

  COMMENT("move argc and argv from parameter registers to the stack");
  int offset;
  offset = lookup_offset_in_scope(current_offset_scope, "argc");
  MOV_TO_OFFSET("%rdi", offset);
  offset = lookup_offset_in_scope(current_offset_scope, "argv");
  MOV_TO_OFFSET("%rsi", offset);
  
  COMMENT("generate code for the body");
  codegen_stmtlist(main->stmtlist);

  COMMENT("generate code for the return expression");
  codegen_expr(main->returnexpr);
  COMMENT("save the return expression into %rax per the abi");
  POP("%rax");

  COMMENT("emit main's epilogue");
  emit_epilogue();

  // exit the scope
  current_offset_scope = destroy_offset_scope(current_offset_scope);
}

static void codegen_funclist(T_funclist funclist) {
  while (NULL != funclist) {
    codegen_func(funclist->func);
    funclist = funclist->tail;
  }
}

static void codegen_func(T_func func) {
  // create a new scope
  current_offset_scope = create_offset_scope(current_offset_scope);

  // emit the pseudo ops for the function definition
  fprintf(codegenout, ".text\n");
  fprintf(codegenout, ".globl %s\n", func->ident);
  fprintf(codegenout, ".type %s, @function\n", func->ident);

  // emit a label for the function
  fprintf(codegenout, "%s:\n", func->ident);

  // add parameters to the offset table assume there is just one parameter
  T_paramlist paramlist = func->paramlist;
  insert_offset(current_offset_scope, paramlist->ident, 8);

  // add local declarations to the scope
  codegen_decllist(func->decllist);

  COMMENT("emit the function prologue");
  emit_prologue(current_offset_scope->stack_size);

  COMMENT("move parameter onto the stack");
  paramlist = func->paramlist;
  int offset = lookup_offset_in_scope(current_offset_scope, paramlist->ident);
  MOV_TO_OFFSET("%rdi", offset);

  COMMENT("generate code for the body");
  codegen_stmtlist(func->stmtlist);

  COMMENT("generate code for the return expression");
  codegen_expr(func->returnexpr);

  COMMENT("save the return expression into %rax per the abi");
  POP("%rax");

  COMMENT("emit the epilogue");
  emit_epilogue();

  // exit the scope
  current_offset_scope = destroy_offset_scope(current_offset_scope);
  // fprintf(stderr, "TODO: codegen_func (remove this message when implemented).");
}

static void codegen_decllist(T_decllist decllist) {
  while (NULL != decllist) {
    T_decl decl = decllist->decl;
    // int size = bitwidth(decl->type);
    insert_offset(current_offset_scope, decl->ident, 8);
    decllist = decllist->tail;
  }
  // fprintf(stderr, "TODO: codegen_decllist (remove this message when implemented).");
}

/* statements */
static void codegen_stmtlist(T_stmtlist stmtlist) {
  while (NULL != stmtlist) {
    codegen_stmt(stmtlist->stmt);
    stmtlist = stmtlist->tail;
  }
}

static void codegen_stmt(T_stmt stmt) {
  if (NULL == stmt) {
    fprintf(stderr, "FATAL: stmt is NULL in codegen_stmt\n");
    exit(1);
  }
  switch (stmt->kind) {
  case E_assignstmt: codegen_assignstmt(stmt); break;
  case E_ifstmt: codegen_ifstmt(stmt); break;
  case E_ifelsestmt: codegen_ifelsestmt(stmt); break;
  case E_whilestmt: codegen_whilestmt(stmt); break;
  case E_compoundstmt: codegen_compoundstmt(stmt); break;
  default: fprintf(stderr, "FATAL: unexpected stmt kind in codegen_stmt\n"); exit(1); break;
  }
}

static void codegen_assignstmt(T_stmt stmt) {
  COMMENT("generate code for the right-hand side of the assignment");
  codegen_expr(stmt->assignstmt.right);
  // find address of left-hand side of the assignment
  int offset = lookup_offset_in_scope(current_offset_scope, stmt->assignstmt.left->identexpr);
  // mov the register that holds right-hand side into the stack address
  POP("%rax");
  MOV_TO_OFFSET("%rax", offset);
  // fprintf(stderr, "TODO: codegen_assignstmt (remove this message when implemented).");
}

static void codegen_ifstmt(T_stmt stmt) {
  // pending project 4
  fprintf(stderr, "TODO: codegen_ifstmt (project 4)\n");
}

static void codegen_ifelsestmt(T_stmt stmt) {
  // pending project 4
  fprintf(stderr, "TODO: codegen_ifelsestmt (project 4)\n");
}

static void codegen_whilestmt(T_stmt stmt) {
  // pending project 4
  fprintf(stderr, "TODO: codegen_whilestmt (project 4)\n");
}

static void codegen_compoundstmt(T_stmt stmt) {
  // generate code for body of compound statement
  codegen_decllist(stmt->compoundstmt.decllist);
  codegen_stmtlist(stmt->compoundstmt.stmtlist);
  // fprintf(stderr, "TODO: codegen_compoundstmt (remove this message when implemented).");
}

/* expressions */
static void codegen_expr(T_expr expr) {
  if (NULL == expr) {
    fprintf(stderr, "FATAL: unexpected NULL in codegen_expr\n");
    exit(1);
  }
  switch (expr->kind) {
  case E_identexpr: codegen_identexpr(expr); break;
  case E_callexpr: codegen_callexpr(expr); break;
  case E_intexpr: codegen_intexpr(expr); break;
  case E_charexpr: codegen_charexpr(expr); break;
  case E_strexpr: codegen_strexpr(expr); break;
  case E_arrayexpr: codegen_arrayexpr(expr); break;
  case E_unaryexpr: codegen_unaryexpr(expr); break;
  case E_binaryexpr: codegen_binaryexpr(expr); break;
  case E_castexpr: codegen_castexpr(expr); break;
  default: fprintf(stderr, "FATAL: unexpected expr kind in codegen_expr\n"); exit(1); break;
  }
}

static void codegen_identexpr(T_expr expr) {
  // todo: given in class
  //look up the ident, then move it to an intermidate register
  int offset = lookup_offset_in_scope(current_offset_scope, expr->identexpr);
  MOV_FROM_OFFSET(offset, "%rax");
  PUSH("%rax");
}

static void codegen_callexpr(T_expr expr) {
  // generate code for the parameter expression (recall that this will be saved onto the stack)
  T_exprlist args = expr->callexpr.args;
  while (NULL != args) {
    codegen_expr(args->expr);
    args = args->tail;
  }
  // pass the parameter to the callee via the %rdi register according to intel abi
  // there is one and only one integer argument to every function definition in the arg list
  COMMENT("pass the parameter");
  args = expr->callexpr.args;
  if (NULL != args) {
    POP("%rdi");
  }
  
  COMMENT("call the function");
  CALL(expr->callexpr.ident);
  // save the return value by pushing it onto the stack (passed via %rax)
  COMMENT("push the return value");
  PUSH("%rax");
  // fprintf(stderr, "TODO: codegen_callexpr (remove this message when implemented).");
}

static void codegen_intexpr(T_expr expr) {
  COMMENT("push the integer");
  MOV_FROM_IMMEDIATE(expr->intexpr, "%rax");
  PUSH("%rax");
  // fprintf(stderr, "TODO: codegen_intexpr (remove this message when implemented).");
}

static void codegen_charexpr(T_expr expr) {
  COMMENT("push the character");
  MOV_FROM_IMMEDIATE((int) expr->charexpr, "%rax");
  PUSH("%rax");
}

static void codegen_strexpr(T_expr expr) {
  // bonus exercise
  // move address of string to register and push it to stack
  MOV_FROM_GLOBAL("%rax", expr->strexpr);
  PUSH("%rax");
}

static void codegen_arrayexpr(T_expr expr) {
  // bonus exercise
  // generate code for the index expression
  codegen_expr(expr->arrayexpr.index);
  // generate code for the array expression
  codegen_expr(expr->arrayexpr.expr);
  // pop the index into %rcx
  POP("%rcx");
  // pop the array address into %rax
  POP("%rax");
  // multiply the index by the size of the array element
  IMUL("$4", "%rcx");
  // add the index to the array address
  ADD("%rcx", "%rax");
  // push the result onto the stack
  PUSH("%rax");
}

static void codegen_unaryexpr(T_expr expr) {
  // unary minus
  if (E_op_minus == expr->unaryexpr.op) {
    // generate code for the expression
    codegen_expr(expr->unaryexpr.expr);
    // pop the expression into %rax
    POP("%rax");
    // negate the value in %rax
    MOV("$0", "%rdx");
    SUB("%rax", "%rdx");
    MOV("%rdx", "%rax");
    // push the result onto the stack
    PUSH("%rax");
  }
  // fprintf(stderr, "TODO: codegen_unaryexpr\n");
}

static void codegen_binaryexpr(T_expr expr) {
  // generate code for left and right operands of current expression
  COMMENT("generate code for the left operand");
  codegen_expr(expr->binaryexpr.left);
  COMMENT("generate code for the right operand");
  codegen_expr(expr->binaryexpr.right);
  // pop the right operand
  COMMENT("pop the right operand");
  POP("%rbx");
  // pop the left operand
  COMMENT("pop the left operand");
  POP("%rax");
  // compute result of current expression
  switch (expr->binaryexpr.op) {
  case E_op_plus:
    COMMENT("do the addition");
    ADD("%rbx", "%rax");
    break;
  case E_op_minus:
    COMMENT("do the subtraction");
    SUB("%rbx", "%rax");
    break;
  case E_op_times:
    COMMENT("do the multiplication");
    IMUL("%rbx", "%rax");
    break;
  case E_op_divide:
    COMMENT("do the division");
    CDQ();
    IDIV("%rbx");
    break;
  case E_op_mod:
    COMMENT("do the remainder");
    CDQ();
    IDIV("%rbx");
    MOV("%rdx", "%rax");
    break;
  default:
    fprintf(stderr, "FATAL: unexpected binary operator in codegen_binaryexpr\n");
    exit(1);
    break;
  }
  // push the result onto the stack
  COMMENT("push the expression result");
  PUSH("%rax");
  // fprintf(stderr, "TODO: codegen_binaryexpr (remove this message when implemented).");
}

static void codegen_castexpr(T_expr expr) {
  // bonus: truncate or extend data between bitwidths depending on type  
  // generate code for the expression
  codegen_expr(expr->castexpr.expr);
  // pop the expression into %rax
  POP("%rax");
  // push the result onto the stack
  PUSH("%rax");
}

/**
 * Emit a function prologue, given some size in bytes of the local
 * variables in the stack frame.
 */
static void emit_prologue(int size) {
	//save stack
	PUSH("%rbp");
	MOV("%rsp", "%rbp");
	if (size > 0) {
		SUBCONST(size, "%rsp");
	}
	PUSH("%rbx");
}

static void emit_epilogue() {
	POP("%rbx");
	MOV("%rbp", "%rsp");
	POP("%rbp");
	RET;
}

/**
 * This function returns the size of a type in bytes.
 */
static int bitwidth(T_type type) {
  switch (type->kind) {
  case E_primitivetype:
    switch (type->primitivetype) {
    case E_typename_int:
      // 32-bit integers
      return 4;
    case E_typename_char:
      // characters are 1 byte
      return 1;
    default:
      fprintf(stderr, "FATAL: unexpected kind in compare_types\n");
      exit(1);
    }
    break;
  case E_pointertype:
    // pointers are to 64-bit addresses
    return 8;
  case E_arraytype:
    // arrays are the size times the bitwidth of the type
    return type->arraytype.size * bitwidth(type->arraytype.type);
  case E_functiontype:
    fprintf(stderr, "FATAL: functions as values are not supported\n");
    exit(1);
    break;
  default:
    fprintf(stderr, "FATAL: unexpected kind in bitwidth\n");
    exit(1);
    break;
  }  
}
