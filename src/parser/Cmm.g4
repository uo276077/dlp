grammar Cmm;	

@header {
    import ast.*;
    import ast.expressions.*;
    import ast.expressions.literals.*;
    import ast.types.*;
}

program: definition*  main_function
        ;

main_function: 'void' 'main' '(' ')' '{' function_body '}'
            ;

definition: variable_definition//variables
          | return_type ID '(' parameters? ')' '{' function_body '}' //function
          ;

function_body: variable_definition* statement*
            ;

parameters: built_in_type ID (',' built_in_type ID)*
          ;

variable_definition: type ID (',' ID)* ';'
                    ;

return_type: built_in_type
            | 'void'
            ;

type: built_in_type
    | type '[' INT_CONSTANT ']'
    | 'struct' '{' variable_definition* '}'
    ;

built_in_type returns [Type ast]:
               a='int'
                { $ast = new IntType($a.getLine(), $a.getCharPositionInLine()+1); }
             | a='double'
                { $ast = new DoubleType($a.getLine(), $a.getCharPositionInLine()+1); }
             | a='char'
                { $ast = new CharType($a.getLine(), $a.getCharPositionInLine()+1); }
             ;
statement: expression '=' expression ';'
        | 'write' arguments ';'
        | 'read' arguments ';'
        | 'while' '(' expression ')' block
        | 'if' '(' expression ')' block
        | 'if' '(' expression ')' block 'else' block
        | 'return' expression ';'
        | ID '(' arguments? ')' ';'//invocation can be considered a statement
        ;

block: statement
    | '{' statement* '}'
    ;

expression returns [Expression ast]:
//          ID '(' arguments? ')' //invocation
//            { $ast = null; }
//        | e1=expression '[' e2=expression ']' //array access
//            { $ast = new Indexing($e1.start.getLine(), $e1.ast.getColumn(),
//                                  $e1.ast, $e2.ast); }
//        | e1=expression '.' ID //field access
//            { $ast = new FieldAccess($e1.start.getLine(), $e1.ast.getColumn(),
//                                     $e1.ast, $ID.text); }
//        | '(' t1=built_in_type ')' e1=expression //cast
//            { $ast = new Cast($e1.start.getLine(), $e1.ast.getColumn(),
//                              $t1.ast, $e1.ast); }
//        | '-' e1=expression //unary minus
//            { $ast = new UnaryMinus($e1.start.getLine(), $e1.ast.getColumn(),
//                                    $e1.ast); }
//        | '!' e1=expression //unary not
//            { $ast = new UnaryNot($e1.start.getLine(), $e1.ast.getColumn(),
//                                  $e1.ast); }
//        | e1=expression op=('*'|'/'|'%') e2=expression
//            { $ast = new Arithmetic($e1.start.getLine(), $e1.ast.getColumn(),
//                                    $e1.ast, $op.text, $e2.ast); }
          e1=expression op=('+'|'-') e2=expression
            { $ast = new Arithmetic($e1.start.getLine(), $e1.ast.getColumn(),
                                    $e1.ast, $op.text, $e2.ast); }
//        | e1=expression op=('>'|'>='|'<'|'<='|'!='|'==') e2=expression //comparisson
//            { $ast = new Comparison($e1.start.getLine(), $e1.ast.getColumn(),
//                                    $e1.ast, $op.text, $e2.ast); }
//        | e1=expression op=('&&'|'||') e2=expression //logical
//            { $ast = new Logical($e1.start.getLine(), $e1.ast.getColumn(),
//                                 $e1.ast, $op.text, $e2.ast); }
        | ID
            { $ast = new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text); }
        | i1=INT_CONSTANT
            { $ast = new IntLiteral( $i1.getLine(), $i1.getCharPositionInLine()+1,
                                     LexerHelper.lexemeToInt($i1.text) ); }
        | r1=REAL_CONSTANT
            { $ast = new DoubleLiteral($r1.getLine(), $r1.getCharPositionInLine()+1,
                                       LexerHelper.lexemeToReal($r1.text)); }
        | c1=CHAR_CONSTANT
            { $ast = new CharLiteral($c1.getLine(), $c1.getCharPositionInLine()+1,
                                    LexerHelper.lexemeToChar($c1.text)); }
       ;

arguments:  expression ( ',' expression )*
        ;

fragment
        DIGIT: [0-9]
;
fragment
        LETTER: [A-Za-z]
;
fragment
        ID_CHAR: LETTER | DIGIT | '_'
;
fragment
        CHAR: .
            | '\\n'
            | '\\t'
            | '\\' INT_CONSTANT
;
fragment
        DECIMAL: INT_CONSTANT '.' DIGIT*
                | DIGIT* '.' DIGIT+
;


ID: (LETTER | '_') ID_CHAR*
    ;

INT_CONSTANT: [1-9] DIGIT*
            | '0'
            ;

REAL_CONSTANT: DECIMAL
             | (INT_CONSTANT | DECIMAL) [Ee] [+-]? DIGIT+
             ;

CHAR_CONSTANT: '\'' CHAR '\''
             ;

ONE_LINE_COMMENT: '//' .*? '\r'? ('\n' | EOF)
                -> skip
                ;

MULTIPLE_LINE_COMMENT: '/*' .*? '*/'
                        -> skip
                     ;

WS : [ \n\t\r]+
    -> skip
    ;