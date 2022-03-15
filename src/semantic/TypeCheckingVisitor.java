package semantic;

import ast.expressions.*;
import ast.expressions.literals.CharLiteral;
import ast.expressions.literals.DoubleLiteral;
import ast.expressions.literals.IntLiteral;
import ast.statements.*;
import ast.types.*;

public class TypeCheckingVisitor extends AbstractVisitor<Void, Void> {
    @Override
    public Void visit(Variable variable, Void param){
        variable.setLvalue(true);
        return null;
    }

    @Override
    public Void visit(Assignment assign, Void param){
        assign.getLeft().accept(this, param);
        assign.getRight().accept(this, param);
        if (!assign.getLeft().getLvalue())
            new ErrorType(assign.getLeft().getLine(),assign.getLeft().getColumn(),
                    "L-value required");
        return null;
    }

    @Override
    public Void visit(Arithmetic arith, Void param) {
        arith.getLeft().accept(this, param);
        arith.getRight().accept(this, param);
        arith.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {
        cast.getExpression().accept(this,param);
        cast.getCastType().accept(this, param);
        cast.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(Comparison comparison, Void param) {
        comparison.getLeft().accept(this,param);
        comparison.getRight().accept(this,param);
        comparison.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {
        fieldAccess.getStruct().accept(this, param);
        fieldAccess.setLvalue(true);
        return null;
    }

    @Override
    public Void visit(FunctionInvocation invocation, Void param) {
        invocation.getParameters().forEach(p -> p.accept(this, param));
        invocation.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(Indexing indexing, Void param) {
        indexing.getArray().accept(this, param);
        indexing.getIndex().accept(this, param);
        indexing.setLvalue(true);
        return null;
    }

    @Override
    public Void visit(Logical logical, Void param) {
        logical.getLeft().accept(this, param);
        logical.getRight().accept(this, param);
        logical.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(Modulus modulus, Void param) {
        modulus.getLeft().accept(this, param);
        modulus.getRight().accept(this, param);
        modulus.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {
        unaryMinus.getExpression().accept(this, param);
        unaryMinus.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(UnaryNot unaryNot, Void param) {
        unaryNot.getExpression().accept(this, param);
        unaryNot.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(CharLiteral charLiteral, Void param) {
        charLiteral.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(DoubleLiteral doubleLiteral, Void param) {
        doubleLiteral.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Void param) {
        intLiteral.setLvalue(false);
        return null;
    }
}
