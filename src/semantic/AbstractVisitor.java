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

public abstract class AbstractVisitor<TP,TR> implements Visitor<TP,TR> {
    @Override
    public TR visit(Arithmetic arith, TP param) {
        arith.getLeft().accept(this,param);
        arith.getRight().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Cast cast, TP param) {
        cast.getExpression().accept(this,param);
        cast.getCastType().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Comparison comparison, TP param) {
        comparison.getLeft().accept(this,param);
        comparison.getRight().accept(this,param);
        return null;
    }

    @Override
    public TR visit(FieldAccess fieldAccess, TP param) {
        fieldAccess.getStruct().accept(this, param);
        return null;
    }

    @Override
    public TR visit(FunctionInvocation invocation, TP param) {
        invocation.getParameters().forEach(p -> p.accept(this, param));
        invocation.getName().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Indexing indexing, TP param) {
        indexing.getArray().accept(this, param);
        indexing.getIndex().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Logical logical, TP param) {
        logical.getLeft().accept(this, param);
        logical.getRight().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Modulus modulus, TP param) {
        modulus.getLeft().accept(this, param);
        modulus.getRight().accept(this, param);
        return null;
    }

    @Override
    public TR visit(UnaryMinus unaryMinus, TP param) {
        unaryMinus.getExpression().accept(this, param);
        return null;
    }

    @Override
    public TR visit(UnaryNot unaryNot, TP param) {
        unaryNot.getExpression().accept(this, param);
        return null;
    }

    @Override
    public TR visit(CharLiteral charLiteral, TP param) {
        return null;
    }

    @Override
    public TR visit(DoubleLiteral doubleLiteral, TP param) {
        return null;
    }

    @Override
    public TR visit(IntLiteral intLiteral, TP param) {
        return null;
    }

    @Override
    public TR visit(FuncDefinition funcDefinition, TP param) {
        funcDefinition.getParameters().forEach(p -> p.accept(this, param));
        funcDefinition.getBody().forEach(b -> b.accept(this, param));
        return null;
    }

    @Override
    public TR visit(VarDefinition varDefinition, TP param) {
        varDefinition.getType().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Assignment assign, TP param) {
        assign.getLeft().accept(this, param);
        assign.getRight().accept(this, param);
        return null;
    }

    @Override
    public TR visit(IfElse ifElse, TP param) {
        ifElse.getCondition().accept(this, param);
        ifElse.getIfBody().forEach(s -> s.accept(this, param));
        ifElse.getElseBody().forEach(s -> s.accept(this, param));
        return null;
    }

    @Override
    public TR visit(Read readSt, TP param) {
        readSt.getArgument().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Return returnSt, TP param) {
        returnSt.getReturnExpression().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Write writeSt, TP param) {
        writeSt.getArgument().accept(this, param);
        return null;
    }

    @Override
    public TR visit(While whileSt, TP param) {
        whileSt.getCondition().accept(this, param);
        whileSt.getBody().forEach(s -> s.accept(this, null));
        return null;
    }

    @Override
    public TR visit(ArrayType arrayType, TP param) {
        arrayType.getType().accept(this, param);
        return null;
    }

    @Override
    public TR visit(CharType charType, TP param) {
        return null;
    }

    @Override
    public TR visit(DoubleType doubleType, TP param) {
        return null;
    }

    @Override
    public TR visit(ErrorType errorType, TP param) {
        return null;
    }

    @Override
    public TR visit(FunctionType functionType, TP param) {
        functionType.getReturnType().accept(this, param);
        functionType.getParameters().forEach(p -> p.accept(this, param));
        return null;
    }

    @Override
    public TR visit(IntType intType, TP param) {
        return null;
    }

    @Override
    public TR visit(StructField structField, TP param) {
        structField.getType().accept(this, param);
        return null;
    }

    @Override
    public TR visit(StructType structType, TP param) {
        structType.getFields().forEach(field -> field.accept(this, param));
        return null;
    }

    @Override
    public TR visit(VoidType voidType, TP param) {
        return null;
    }

    @Override
    public TR visit(Program program, TP param){
        program.getDefinitions().forEach(def -> def.accept(this, param));
        return null;
    }

    @Override
    public TR visit(Variable e, TP param) {
        return null;
    }
}
