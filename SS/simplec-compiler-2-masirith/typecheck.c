#include <stdio.h>
#include <stdbool.h>

#include "ast_printer.h"
#include "util.h"
#include "table.h"
#include "typecheck.h"

static T_type INTTYPE;
static T_type CHARTYPE;
static T_type STRINGTYPE;
static T_type STRINGARRAYTYPE;

static T_scope current_scope;

static T_scope create_scope(T_scope parent) {
  T_scope scope = xmalloc(sizeof(*scope));
  scope->table = create_table();
  scope->parent = parent;
  return scope;
}

static void destroy_scope(T_scope scope) {
  free(scope);
}

static T_type lookup_in_all_scopes(T_scope scope, string ident) {
  // loop over each scope and check for a binding for ident
  while (NULL != scope) {
    // check for a binding in this scope
    T_type type = (T_type) lookup(scope->table, ident);
    if (NULL != type) {
      return type;
    }
    // no binding in this scope, so check the parent
    scope = scope->parent;
  }
  // found no binding in any scope
  return NULL;
}

/* the root of the AST */
void check_prog(T_prog prog) {
  // initialize useful types
  INTTYPE = create_primitivetype(E_typename_int);
  CHARTYPE = create_primitivetype(E_typename_char);
  STRINGTYPE = create_pointertype(CHARTYPE);
  STRINGARRAYTYPE = create_pointertype(STRINGTYPE);
  // create the global scope
  current_scope = create_scope(NULL);
  // add the global declarations
  check_decllist(prog->decllist);
  // check the function definitions
  check_funclist(prog->funclist);
  // check the main function
  check_main(prog->main);
  // clean-up the global scope
  destroy_scope(current_scope);
}

/* declarations and function definitions */
static void check_decllist(T_decllist decllist) {
  while (NULL != decllist) {
    check_decl(decllist->decl);
    decllist = decllist->tail;
  }
}

static void check_decl(T_decl decl) {
  // check for duplicate definitions in local scope
  if (NULL != lookup(current_scope->table, decl->ident)) {
    type_error("duplicate declaration of same symbol");
  }
  // add the binding
  insert(current_scope->table, decl->ident, decl->type);
}

static void check_funclist(T_funclist funclist) {
  while (NULL != funclist) {
    check_func(funclist->func);
    funclist = funclist->tail;
  }
}

static void check_func(T_func func) {
  // create a new scope
  current_scope = create_scope(current_scope);
  // add the parameters to the symtab
  T_paramlist params = func->paramlist;
  while (NULL != params) {
    // check for duplicate definitions in local scope
    if (NULL != lookup(current_scope->table, params->ident)) {
      type_error("duplicate declaration of same symbol");
    }
    // add the binding
    insert(current_scope->table, params->ident, params->ident);
    params = params->tail;
  }
  // add the declarations to the symtab
  check_decllist(func->decllist);
  // check the function body for type errors
  check_stmtlist(func->stmtlist);
  // check the return expression for type errors
  check_expr(func->returnexpr);
  // check that the return type matches the function type
  if (! compare_types(func->type, func->returnexpr->type)) {
    type_error("the return expression type does not match the function type");
  }
  // restore the parent symbol table
  T_scope parent_scope = current_scope->parent;
  destroy_scope(current_scope); current_scope = parent_scope;
}

// GIVEN
static void check_main(T_main main) {
  // fprintf(stderr, "check_main");
  // create a new scope
  current_scope = create_scope(current_scope);
  // add argc and argv with their C runtime types
  insert(current_scope->table, "argc", INTTYPE);
  insert(current_scope->table, "argv", STRINGARRAYTYPE);
  // add the declarations to the symtab
  check_decllist(main->decllist);
  // check the function body for type errors
  check_stmtlist(main->stmtlist);
  // check the return expression for type errors
  check_expr(main->returnexpr);
  // check that the return type is an int, per C runtime
  if (! compare_types(main->returnexpr->type, INTTYPE)) {
    type_error("the return expression type does not match the function type");
  }
  // restore the parent symbol table
  T_scope parent_scope = current_scope->parent;
  destroy_scope(current_scope); current_scope = parent_scope;
}

/* statements */
static void check_stmtlist(T_stmtlist stmtlist) {
  while (NULL != stmtlist) {
    check_stmt(stmtlist->stmt);
    stmtlist = stmtlist->tail;
  }
}

static void check_stmt(T_stmt stmt) {
  if (NULL == stmt) {
    fprintf(stderr, "FATAL: stmt is NULL in check_stmt\n");
    exit(1);
  }
  switch (stmt->kind) {
  case E_assignstmt: check_assignstmt(stmt); break;
  case E_ifstmt: check_ifstmt(stmt); break;
  case E_ifelsestmt: check_ifelsestmt(stmt); break;
  case E_whilestmt: check_whilestmt(stmt); break;
  case E_compoundstmt: check_compoundstmt(stmt); break;
  default: fprintf(stderr, "FATAL: unexpected stmt kind in check_stmt\n"); exit(1); break;
  }
}

static void check_assignstmt(T_stmt stmt) {
  // check the type of the left-hand-side
  check_expr(stmt->assignstmt.left);
  // check the type of the right-hand-side
  check_expr(stmt->assignstmt.right);
  // check that the left-hand-side is an l-value, i.e., an identexpr or a deref unary expression
  switch (stmt->assignstmt.left->kind) {
  case E_identexpr:
    // okay
    break;
  case E_unaryexpr:
    switch (stmt->assignstmt.left->unaryexpr.op) {
    case E_op_deref:
      // okay
      break;
    default:
      type_error("assignment is not to an l-value");
      break;
    }
    break;
  case E_arrayexpr:
    // okay
    break;
  default:
    type_error("assignment is not to an l-value");
    break;
  }
  // check that the types of the left- and right-hand sides match
  if (! compare_types(stmt->assignstmt.left->type, stmt->assignstmt.right->type)) {
    type_error("left- and right-hand sides of the assignstmt have mismatched types");
  }
}

static void check_ifstmt(T_stmt stmt) {
  // check the condition expression
  check_expr(stmt->ifstmt.cond);
  // check that the condition is an int
  if (! compare_types(stmt->ifstmt.cond->type, INTTYPE)) {
    type_error("if condition must be an int");
  }
  // recursively check the if branch
  check_stmt(stmt->ifstmt.body);
}

static void check_ifelsestmt(T_stmt stmt) {
  // check the condition expression
  check_expr(stmt->ifelsestmt.cond);
  // check that the condition is an int
  if (! compare_types(stmt->ifelsestmt.cond->type, INTTYPE)) {
    type_error("if condition must be an int");
  }
  // recursively check the if branch
  check_stmt(stmt->ifelsestmt.ifbranch);
  // recursively check the else branch
  check_stmt(stmt->ifelsestmt.elsebranch);
}

static void check_whilestmt(T_stmt stmt) {
  // check the condition expression
  check_expr(stmt->whilestmt.cond);
  // check that the condition is an int
  if (! compare_types(stmt->whilestmt.cond->type, INTTYPE)) {
    type_error("while condition must be an int");
  }
  // recursively check the body
  check_stmt(stmt->whilestmt.body);
}

static void check_compoundstmt(T_stmt stmt) {
  // create a new scope
  current_scope = create_scope(current_scope);
  // add the declarations to the symtab
  check_decllist(stmt->compoundstmt.decllist);
  // check the statements for type errors
  check_stmtlist(stmt->compoundstmt.stmtlist);
  // restore the parent symbol table
  T_scope parent_scope = current_scope->parent;
  destroy_scope(current_scope); current_scope = parent_scope;
}

/* expressions */
static void check_expr(T_expr expr) {
  if (NULL == expr) {
    fprintf(stderr, "FATAL: unexpected NULL in check_expr\n");
    exit(1);
  }
  switch (expr->kind) {
  case E_identexpr: check_identexpr(expr); break;
  case E_callexpr: check_callexpr(expr); break;
  case E_intexpr: check_intexpr(expr); break;
  case E_charexpr: check_charexpr(expr); break;
  case E_strexpr: check_strexpr(expr); break;
  case E_arrayexpr: check_arrayexpr(expr); break;
  case E_unaryexpr: check_unaryexpr(expr); break;
  case E_binaryexpr: check_binaryexpr(expr); break;
  case E_castexpr: check_castexpr(expr); break;
  default: fprintf(stderr, "FATAL: unexpected expr kind in check-expr\n"); exit(1); break;
  }
}

static void check_identexpr(T_expr expr) {
  // fprintf(stderr, "check_identexpr\n");
  // lookup the symbol
  T_type binding = lookup_in_all_scopes(current_scope, expr->identexpr);
  // check that the symbol has been declared
  if (NULL == binding) {
    type_error("use of undeclared symbol in identexpr");
  }
  // check that the symbol is not a function type
  if (E_functiontype == binding->kind) {
    type_error("cannot use a function as a value");
  }
  // set the type to the binding from the symtab
  expr->type = binding;
}

static void check_callexpr(T_expr expr) {
  // fprintf(stderr, "check_callexpr\n");
  // lookup the symbol
  T_type binding = lookup_in_all_scopes(current_scope, expr->callexpr.ident);
  // check that symbol is function type
  if (E_functiontype != binding->kind) {
    type_error("call to non-function type in callexpr");
  }
  // compare the types of each argument's expression (in the list) match each corresponding parameter type from the function type
  T_typelist paramtypes = binding->functiontype.paramtypes;
  T_exprlist args = expr->callexpr.args;
  while (NULL != paramtypes && NULL != args) {
    check_expr(args->expr);
    if (! compare_types(paramtypes->type, args->expr->type)) {
      type_error("argument type does not match parameter type in callexpr");
    }
    paramtypes = paramtypes->tail;
    args = args->tail;
  }
  if (NULL != paramtypes || NULL != args) {
    type_error("number of arguments does not match number of parameters in callexpr");
  }
  // set the type to the return type of the function
  expr->type = binding->functiontype.returntype;
}

static void check_intexpr(T_expr expr) {
  // fprintf(stderr, "check_intexpr\n");
  // integer constants are int type by definition
  expr->type = INTTYPE;
}

static void check_charexpr(T_expr expr) {
  // fprintf(stderr, "check_charexpr\n");
  expr->type = CHARTYPE;
}

static void check_strexpr(T_expr expr) {
  // fprintf(stderr, "check_strexpr\n");
  expr->type = STRINGTYPE;
}

static void check_arrayexpr(T_expr expr) {
  // fprintf(stderr, "check_arrayexpr\n");
  // check the array expression
  check_expr(expr->arrayexpr.expr);
  // check the index expression
  check_expr(expr->arrayexpr.index);
  // check that expression being dereferenced is either pointer or array type
  if (E_pointertype != expr->arrayexpr.expr->type->kind && E_arraytype != expr->arrayexpr.expr->type->kind) {
    type_error("array expression is not a pointer or array type");
  }
  // check that the index expression is an int
  if (! compare_types(expr->arrayexpr.index->type, INTTYPE)) {
    type_error("index expression is not an int type");
  }
  // set the type to the type of the array
  expr->type = expr->arrayexpr.expr->type->arraytype.type;
}

static void check_unaryexpr(T_expr expr) {
  // fprintf(stderr, "check_unaryexpr\n");
  // typecheck the operand
  check_expr(expr->unaryexpr.expr);
  // check the type of the operator
  switch (expr->unaryexpr.op) {
  case E_op_ref:
    // gets the pointer to a variable of a given type
    expr->type = create_pointertype(expr->unaryexpr.expr->type);
    break;
  case E_op_deref:
    // the operand should be a pointer type
    if (expr->unaryexpr.expr->type->kind != E_pointertype) {
      type_error("dereference of non-pointer type");
    }
    // set the type to the type the pointer points to
    expr->type = expr->unaryexpr.expr->type->pointertype;
    break;
  case E_op_minus:
    // the operand should be an int
    if (! compare_types(expr->unaryexpr.expr->type, INTTYPE)) {
      type_error("unary minus of non-int type");
    }
    // set the type to the type of the operand
    expr->type = expr->unaryexpr.expr->type;
    break;
  case E_op_not:
    // the operand should be an int
    if (! compare_types(expr->unaryexpr.expr->type, INTTYPE)) {
      type_error("logical not of non-int type");
    }
    // set the type to the type of the operand
    expr->type = expr->unaryexpr.expr->type;
    break;
  default:
    type_error("unexpected unary operator");
    break;
  }
}

static void check_binaryexpr(T_expr expr) {
  // fprintf(stderr, "check_binaryexpr\n");
  // check the left-hand-side expression
  check_expr(expr->binaryexpr.left);
  // check the right-hand-side expression
  check_expr(expr->binaryexpr.right);
  
  // check the type of the operator
  switch (expr->binaryexpr.op) {
  case E_op_plus:
  case E_op_minus:
  case E_op_times:
  case E_op_divide:
  case E_op_mod:
    // check that both left and right are the same type
    if (! compare_types(expr->binaryexpr.left->type, expr->binaryexpr.right->type)) {
      type_error("left- and right-hand-side expressions have mismatched types");
    }
    break;
  case E_op_eq:
  case E_op_ne:
  case E_op_lt:
  case E_op_le:
  case E_op_gt:
  case E_op_ge:
    // check that both left and right are either int or char
    if (! (compare_types(expr->binaryexpr.left->type, INTTYPE) || compare_types(expr->binaryexpr.left->type, CHARTYPE))) {
      type_error("left-hand-side expression is not an int or char type");
    }
    if (! (compare_types(expr->binaryexpr.right->type, INTTYPE) || compare_types(expr->binaryexpr.right->type, CHARTYPE))) {
      type_error("right-hand-side expression is not an int or char type");
    }
    // check that both left and right are the same type
    if (! compare_types(expr->binaryexpr.left->type, expr->binaryexpr.right->type)) {
      type_error("left- and right-hand-side expressions have mismatched types");
    }
    // the result of a comparison is an int
    expr->type = INTTYPE;
    break;
  case E_op_and:
  case E_op_or:
    // check that the left-hand-side expression is an int
    if (! compare_types(expr->binaryexpr.left->type, INTTYPE)) {
      type_error("left-hand-side expression is not an int type");
    }
   // check that the right-hand-side expression is an int
    if (! compare_types(expr->binaryexpr.right->type, INTTYPE)) {
      type_error("right-hand-side expression is not an int type");
    }
    // the result of a logical operator is an int
    expr->type = INTTYPE;
    break;
  default:
    type_error("unexpected binary operator");
    break;
  }
  // set the type to the type of the left-hand-side expression
  expr->type = expr->binaryexpr.left->type;
}

static void check_castexpr(T_expr expr) {
  // fprintf(stderr, "check_castexpr\n");
  // check the cast type
  check_expr(expr->castexpr.expr);
  // set the type to the cast type
  expr->type = expr->castexpr.type;
}

/* type error */
static void type_error(char *msg) {
  fprintf(stderr, "%s\n", msg);
  exit(3);
}

/* type comparison */
bool compare_types(T_type type1, T_type type2) {
  if (NULL == type1 || NULL == type2) {
    fprintf(stderr, "FATAL: unexpected NULL values in compare_types\n");
    exit(1);
  }
  if (type1->kind == type2->kind) {
    switch (type1->kind) {
    case E_primitivetype:
      // the primitive type names should match
      return type1->primitivetype == type2->primitivetype;
      break;
    case E_pointertype:
      // the types the pointers point to should match
      return compare_types(type1->pointertype, type2->pointertype);
      break;
    case E_arraytype:
      // both the size and the type should match
      return type1->arraytype.size == type2->arraytype.size
        && compare_types(type1->arraytype.type, type2->arraytype.type);
      break;
    case E_functiontype:
      {
        // the parameter types, their number, and the return type should match
        T_typelist params1 = type1->functiontype.paramtypes;
        T_typelist params2 = type2->functiontype.paramtypes;
        while (NULL != params1 && NULL != params2) {
          if (! compare_types(params1->type, params2->type)) {
            // the parameter types do not match
            return false;
          }
          params1 = params1->tail;
          params2 = params2->tail;
        }
        if (NULL != params1 || NULL != params2) {
          // the number of parameters do not match
          return false;
        }
        return compare_types(type1->functiontype.returntype, type2->functiontype.returntype);
      }
      break;
    default:
      fprintf(stderr, "FATAL: unexpected kind in compare_types\n");
      exit(1);
    }
  } else {
    return false;
  }
}
