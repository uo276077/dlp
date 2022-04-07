package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import semantic.Visitor;

public class DoubleType extends AbstractType {

    public DoubleType(int line, int column) {
        super(line, column);
    }

    @Override
    public String toString() {
        return "double";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type arithmetic(Type t, int line, int column) {
        if (t instanceof ErrorType)
            return t;
        if (t instanceof DoubleType)
            return this;
        return new ErrorType(line, column, String.format(
                "The type of %s does not support arithmetic operations with %s.", t, this
        ));
    }

    @Override
    public Type compare(Type t, int line, int column) {
        if (t instanceof ErrorType)
            return t;
        if (t instanceof DoubleType)
            return new IntType(line, column);
        return new ErrorType(line, column, String.format(
                "The type of %s does not support comparisons with %s.", t, this
        ));
    }

    @Override
    public Type toUnaryMinus(int line, int column) {
        return this;
    }

    @Override
    public void assign(Type type, int line, int column) {
        if (type instanceof ErrorType) return;
        if (type instanceof DoubleType) return;

        new ErrorType(line, column, String.format(
                "Cannot assign type %s to type %s.", type, this
        ));
    }

    @Override
    public void readable(int line, int column) {
    }

    @Override
    public void writable(int line, int column) {
    }

    @Override
    public void returnable(Type type, int line, int column) {
        if (type instanceof DoubleType) return;
        if (type instanceof ErrorType) return;

        new ErrorType(line, column, String.format(
                "Cannot return type %s for function of return type %s.", type, this
        ));
    }

    @Override
    public Type castTo(Type castType, int line, int column) {
        if (castType instanceof ErrorType)
            return castType;
        if (castType instanceof IntType)
            return castType;
        if (castType instanceof CharType)
            return castType;
        if (castType instanceof DoubleType)
            return this;
        return new ErrorType(line, column, String.format(
                "The type %s does not support casting to %s.", this, castType
        ));
    }

    @Override
    public int numberOfBytes() {
        return 4;
    }
}
