grammar Cmm;	

@header {
    import ast.*;
    import ast.expressions.*;
    import ast.expressions.literals.*;
    import ast.types.*;
    import ast.statements.*;
    import ast.definitions.*;
}

program returns [Program ast]
            locals [List<Definition> defs = new ArrayList<Definition>();]:
        (d1=definition
            { $defs.addAll($d1.ast); }
        )*  m1=main_function
            { $defs.add($m1.ast); }
         EOF
            { $ast = new Program($m1.start.getLine(), $m1.ast.getColumn(), $defs); }
        ;

main_function returns [Definition ast]:
            a='void' 'main' '(' ')' '{' b1=function_body '}'
                { $ast = new FuncDefinition($a.getLine(), $a.getCharPositionInLine()+1,
                                                new FunctionType($a.getLine(), $a.getCharPositionInLine()+1,
                                                            new VoidType($a.getLine(), $a.getCharPositionInLine()+1),
                                                            new ArrayList<Definition>()),
                                                "main",
                                                $b1.ast); }
            ;

definition returns [List<Definition> ast = new ArrayList<Definition>();]
        locals [List<Definition> params = new ArrayList<Definition>();]:
            v1=variable_definition//variables
                { $ast.addAll($v1.ast); }
          | rt1=return_type id1=ID '('
          (p1=parameters
            { $params.addAll($p1.ast); }
          )? ')' '{' b1=function_body '}' //function
            { $ast.add( new FuncDefinition($rt1.start.getLine(), $rt1.ast.getColumn(),
                                            new FunctionType($rt1.start.getLine(), $rt1.ast.getColumn(), $rt1.ast, $params),
                                            $id1.text, $b1.ast) ); }
          ;

function_body returns [List<Statement> ast = new ArrayList<Statement>();]:
    (v1=variable_definition
        { $ast.addAll($v1.ast); }
    )* (s1=statement
        { $ast.addAll($s1.ast); }
    )*
            ;

parameters returns [List<Definition> ast = new ArrayList<Definition>();]:
        bt1=built_in_type id1=ID
            { $ast.add( new VarDefinition($id1.getLine(), $id1.getCharPositionInLine()+1, $id1.text, $bt1.ast) ); }
         (',' bt2=built_in_type id2=ID
            { $ast.add( new VarDefinition($id2.getLine(), $id2.getCharPositionInLine()+1, $id2.text, $bt2.ast) ); }
         )*
          ;

variable_definition returns [List<VarDefinition> ast = new ArrayList<VarDefinition>();]:
        t1=type id1=ID
            { $ast.add( new VarDefinition($id1.getLine(), $id1.getCharPositionInLine()+1, $id1.text, $t1.ast) ); }
        (',' id2=ID
            { $ast.add( new VarDefinition($id2.getLine(), $id2.getCharPositionInLine()+1, $id2.text, $t1.ast) ); }
        )* ';'
                    ;

return_type returns [Type ast]:
            bt1=built_in_type
                { $ast = $bt1.ast; }
            | a='void'
                { $ast = new VoidType($a.getLine(), $a.getCharPositionInLine()+1); }
            ;

type returns [Type ast]
        locals [List<StructField> fields = new ArrayList<StructField>();]:
    bt1=built_in_type
        { $ast = $bt1.ast; }
    | t1=type '[' i1=INT_CONSTANT ']'
        { $ast = ArrayType.createArray($t1.start.getLine(), $t1.ast.getColumn(), $t1.ast, LexerHelper.lexemeToInt($i1.text) );}
    | a='struct' '{'
    (t1=type id1=ID
        { $fields.add(new StructField($t1.start.getLine(), $t1.ast.getColumn(), $t1.ast, $id1.text)); }
    (',' id2=ID
        { $fields.add(new StructField($t1.start.getLine(), $t1.ast.getColumn(), $t1.ast, $id2.text)); }
    )*
    ';' )* '}'
        { $ast = new StructType($a.getLine(), $a.getCharPositionInLine()+1, $fields); }
    ;

built_in_type returns [Type ast]:
               a='int'
                { $ast = new IntType($a.getLine(), $a.getCharPositionInLine()+1); }
             | a='double'
                { $ast = new DoubleType($a.getLine(), $a.getCharPositionInLine()+1); }
             | a='char'
                { $ast = new CharType($a.getLine(), $a.getCharPositionInLine()+1); }
             | a='boolean'
                { $ast = new BooleanType($a.getLine(), $a.getCharPositionInLine()+1); }
             ;
statement returns [List<Statement> ast = new ArrayList<Statement>();]
                locals [List<Statement> elseBody = new ArrayList<Statement>();, List<Expression> args = null]:
        e1=expression '=' e2=expression ';'
            { $ast.add(new Assignment($e1.start.getLine(), $e1.ast.getColumn(), $e1.ast, $e2.ast)); }
        | 'write' e1=expression
                { $ast.add(new Write($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast)); }
          ( ','
            e2=expression
                { $ast.add(new Write($e2.ast.getLine(), $e2.ast.getColumn(), $e2.ast)); }
           )* ';'
        | 'read' e1=expression
                { $ast.add(new Read($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast)); }
          ( ','
            e2=expression
                { $ast.add(new Read($e2.ast.getLine(), $e2.ast.getColumn(), $e2.ast)); }
           )* ';'
        | 'while' '(' e1=expression ')' b1=block
            { $ast.add(new While($e1.start.getLine(), $e1.ast.getColumn(), $e1.ast, $b1.ast)); }
        | 'if' '(' e1=expression ')' b1=block
            ('else' b2=block
                { $elseBody = $b2.ast; }
            )?
                { $ast.add(new IfElse($e1.start.getLine(), $e1.ast.getColumn(), $e1.ast, $b1.ast, $elseBody));}
        | 'return' e1=expression ';' //no precedence because there's no ambiguity
            { $ast.add(new Return($e1.start.getLine(), $e1.ast.getColumn(), $e1.ast)); }
        | ID '('
            (a1=arguments
              { $args = $a1.ast; }
            )? ')' ';'//invocation can be considered a statement
              { $ast.add(new FunctionInvocation($ID.getLine(), $ID.getCharPositionInLine()+1,
              new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text), $args)); }
        ;

block returns [List<Statement> ast = new ArrayList<Statement>();]:
    s1=statement
        { $ast.addAll( $s1.ast ); }
    | '{' (s2=statement
        { $ast.addAll( $s2.ast ); }
    )* '}'
    ;

expression returns [Expression ast]
                    locals [List<Expression> args = null]:
          '(' e1=expression ')'
            { $ast = $e1.ast; }
        | ID '('
          (a1=arguments
            { $args = $a1.ast; }
          )? ')' //invocation
            { $ast = new FunctionInvocation($ID.getLine(), $ID.getCharPositionInLine()+1,
            new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text), $args); }
        | e1=expression '[' e2=expression ']' //array access
            { $ast = new Indexing($e1.start.getLine(), $e1.ast.getColumn(),
                                  $e1.ast, $e2.ast); }
        | e1=expression '.' ID //field access
            { $ast = new FieldAccess($e1.start.getLine(), $e1.ast.getColumn(),
                                     $e1.ast, $ID.text); }
        | '(' t1=built_in_type ')' e1=expression //cast
            { $ast = new Cast($e1.start.getLine(), $e1.ast.getColumn(),
                              $t1.ast, $e1.ast); }
        | '-' e1=expression //unary minus
            { $ast = new UnaryMinus($e1.start.getLine(), $e1.ast.getColumn(),
                                    $e1.ast); }
        | '!' e1=expression //unary not
            { $ast = new UnaryNot($e1.start.getLine(), $e1.ast.getColumn(),
                                  $e1.ast); }
        | e1=expression op=('*'|'/'|'%') e2=expression
            { $ast = Arithmetic.createArithmeticOperation($e1.start.getLine(), $e1.ast.getColumn(), $e1.ast, $op.text, $e2.ast); }
        |  e1=expression op=('+'|'-') e2=expression
            { $ast = new Arithmetic($e1.start.getLine(), $e1.ast.getColumn(),
                                    $e1.ast, $op.text, $e2.ast); }
        | e1=expression op=('>'|'>='|'<'|'<='|'!='|'==') e2=expression //comparisson
            { $ast = new Comparison($e1.start.getLine(), $e1.ast.getColumn(),
                                    $e1.ast, $op.text, $e2.ast); }
        | e1=expression op=('&&'|'||') e2=expression //logical
            { $ast = new Logical($e1.start.getLine(), $e1.ast.getColumn(),
                                 $e1.ast, $op.text, $e2.ast); }
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
        | b1=TRUE_CONSTANT
            { $ast = new TrueLiteral($b1.getLine(), $b1.getCharPositionInLine()+1); }
        | b1=FALSE_CONSTANT
            { $ast = new FalseLiteral($b1.getLine(), $b1.getCharPositionInLine()+1); }
       ;

arguments returns [List<Expression> ast = new ArrayList<Expression>();]:
        e1=expression
            { $ast.add( $e1.ast ); }
        ( ','
        e2=expression
            { $ast.add( $e2.ast ); }
         )*
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


TRUE_CONSTANT: 'true'
    ;

FALSE_CONSTANT: 'false'
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