package codegeneration;

import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.expressions.literals.CharLiteral;
import ast.expressions.literals.DoubleLiteral;
import ast.expressions.literals.IntLiteral;
import ast.statements.*;
import ast.types.*;
import semantic.Visitor;

public abstract class AbstractCGVisitor<TR, TP> implements Visitor<TR, TP> {

    protected CodeGenerator cg;

    public AbstractCGVisitor(CodeGenerator cg){
        this.cg = cg;
    }

    @Override
    public TP visit(Program program, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Program", program.getLine(), program.getColumn()));
    }

    @Override
    public TP visit(Variable variable, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Variable", variable.getLine(), variable.getColumn()));
    }

    @Override
    public TP visit(Arithmetic arith, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Arithmetic", arith.getLine(), arith.getColumn()));
    }

    @Override
    public TP visit(Cast cast, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Cast", cast.getLine(), cast.getColumn()));
    }

    @Override
    public TP visit(Comparison comparison, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Comparison", comparison.getLine(), comparison.getColumn()));
    }

    @Override
    public TP visit(FieldAccess fieldAccess, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Field access", fieldAccess.getLine(), fieldAccess.getColumn()));
    }

    @Override
    public TP visit(FunctionInvocation invocation, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Function invocation", invocation.getLine(), invocation.getColumn()));
    }

    @Override
    public TP visit(Indexing indexing, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Indexing", indexing.getLine(), indexing.getColumn()));
    }

    @Override
    public TP visit(Logical logical, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Logical", logical.getLine(), logical.getColumn()));
    }

    @Override
    public TP visit(Modulus modulus, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Modulus", modulus.getLine(), modulus.getColumn()));
    }

    @Override
    public TP visit(UnaryMinus unaryMinus, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Unary minus", unaryMinus.getLine(), unaryMinus.getColumn()));
    }

    @Override
    public TP visit(UnaryNot unaryNot, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Unary not", unaryNot.getLine(), unaryNot.getColumn()));
    }

    @Override
    public TP visit(CharLiteral charLiteral, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Character literal", charLiteral.getLine(), charLiteral.getColumn()));
    }

    @Override
    public TP visit(DoubleLiteral doubleLiteral, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Double literal", doubleLiteral.getLine(), doubleLiteral.getColumn()));
    }

    @Override
    public TP visit(IntLiteral intLiteral, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Integer literal", intLiteral.getLine(), intLiteral.getColumn()));
    }

    @Override
    public TP visit(FuncDefinition funcDefinition, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: FuncDefinition", funcDefinition.getLine(), funcDefinition.getColumn()));
    }

    @Override
    public TP visit(VarDefinition varDefinition, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: VarDefinition", varDefinition.getLine(), varDefinition.getColumn()));
    }

    @Override
    public TP visit(Assignment assign, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Assignment", assign.getLine(), assign.getColumn()));
    }

    @Override
    public TP visit(IfElse ifElse, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: IfElse", ifElse.getLine(), ifElse.getColumn()));
    }

    @Override
    public TP visit(Read readSt, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Read", readSt.getLine(), readSt.getColumn()));
    }

    @Override
    public TP visit(Return returnSt, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Return", returnSt.getLine(), returnSt.getColumn()));
    }

    @Override
    public TP visit(Write writeSt, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: Write", writeSt.getLine(), writeSt.getColumn()));
    }

    @Override
    public TP visit(While whileSt, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: While", whileSt.getLine(), whileSt.getColumn()));
    }

    @Override
    public TP visit(ArrayType arrayType, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: ArrayType", arrayType.getLine(), arrayType.getColumn()));
    }

    @Override
    public TP visit(CharType charType, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: CharType", charType.getLine(), charType.getColumn()));
    }

    @Override
    public TP visit(DoubleType doubleType, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: DoubleType", doubleType.getLine(), doubleType.getColumn()));
    }

    @Override
    public TP visit(ErrorType errorType, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: ErrorType", errorType.getLine(), errorType.getColumn()));
    }

    @Override
    public TP visit(FunctionType functionType, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: FunctionType", functionType.getLine(), functionType.getColumn()));
    }

    @Override
    public TP visit(IntType intType, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: IntType", intType.getLine(), intType.getColumn()));
    }

    @Override
    public TP visit(StructField structField, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: StructField", structField.getLine(), structField.getColumn()));
    }

    @Override
    public TP visit(StructType structType, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: StructType", structType.getLine(), structType.getColumn()));
    }

    @Override
    public TP visit(VoidType voidType, TR param) {
        throw new RuntimeException(String.format("[ERROR] %d %d: VoidType", voidType.getLine(), voidType.getColumn()));
    }
}
