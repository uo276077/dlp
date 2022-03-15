package semantic;

import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.expressions.literals.CharLiteral;
import ast.expressions.literals.DoubleLiteral;
import ast.expressions.literals.IntLiteral;
import ast.statements.*;
import ast.types.*;

public interface Visitor<TP, TR> {
    TR visit(Program program, TP param);
    TR visit(Variable variable, TP param);

    TR visit(Arithmetic arith, TP param);
    TR visit(Cast cast, TP param);
    TR visit(Comparison comparison, TP param);
    TR visit(FieldAccess fieldAccess, TP param);
    TR visit(FunctionInvocation invocation, TP param);
    TR visit(Indexing indexing, TP param);
    TR visit(Logical logical, TP param);
    TR visit(Modulus modulus, TP param);
    TR visit(UnaryMinus unaryMinus, TP param);
    TR visit(UnaryNot unaryNot, TP param);
    TR visit(CharLiteral charLiteral, TP param);
    TR visit(DoubleLiteral doubleLiteral, TP param);
    TR visit(IntLiteral intLiteral, TP param);

    TR visit(FuncDefinition funcDefinition, TP param);
    TR visit(VarDefinition varDefinition, TP param);

    TR visit(Assignment assign, TP param);
    TR visit(IfElse ifElse, TP param);
    TR visit(Read readSt, TP param);
    TR visit(Return returnSt, TP param);
    TR visit(Write writeSt, TP param);
    TR visit(While whileSt, TP param);

    TR visit(ArrayType arrayType, TP param);
    TR visit(CharType charType, TP param);
    TR visit(DoubleType doubleType, TP param);
    TR visit(ErrorType errorType, TP param);
    TR visit(FunctionType functionType, TP param);
    TR visit(IntType intType, TP param);
    TR visit(StructField structField, TP param);
    TR visit(StructType structType, TP param);
    TR visit(VoidType voidType, TP param);
}
