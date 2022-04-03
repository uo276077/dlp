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
        if (t instanceof IntType)
            return this;
        if (t instanceof DoubleType)
            return this;
        if (t instanceof CharType)
            return this;
        return new ErrorType(line, column, String.format(
                "The type of %s does not support arithmetic operations.", t
        ));
    }

    @Override
    public Type compare(Type t, int line, int column) {
        if (t instanceof ErrorType)
            return t;
        if (t instanceof IntType)
            return t;
        if (t instanceof DoubleType)
            return new IntType(line, column); //TODO ??
        if (t instanceof CharType)
            return new IntType(line, column); //TODO ??
        return new ErrorType(line, column, String.format(
                "The type of %s does not support comparisons.", t
        ));
    }

    @Override
    public Type toUnaryMinus(int line, int column) {
        return this;
    }
}
