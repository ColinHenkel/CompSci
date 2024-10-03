#include <stdio.h>

#include "ast.h" // AST data structures
#include "parser.tab.h" // declared yyparser
#include "codegen.h" // tree walker

extern FILE *yyout; // lexer interface, output of flex
extern int yylex(void); // lexer interface, get next token

extern T_statement_list ast; // interface to parser result

int main(int argc, char **argv) {
  /* while(1) {
   * yylex();
   * } */

  // print input program and parse errors to stderr
  yyout = stderr;

  // kick off parser
  yyparse();

  gencode_statement_list(ast);

  return 0;
}
