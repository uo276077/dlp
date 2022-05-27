package ast.types;

import ast.Type;
import semantic.Visitor;

public class BooleanType extends AbstractType{

    public BooleanType(int line, int column) {
        super(line, column);
    }

    @Override
    public Type logical(Type t, int line, int column) {
        if (t instanceof BooleanType)
            return this;
        return new ErrorType(line, column, String.format(
                "The type of %s does not support logical operations.", t
        ));
    }

    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public Type negation(int line, int column) {
        return this;
    }

    @Override
    public void asBoolean(int line, int column) {
    }

    @Override
    public void readable(int line, int column) {
    }

    @Override
    public void writable(int line, int column) {
    }

    @Override
    public void returnable(Type type, int line, int column) {
        if (type instanceof BooleanType) return;
        if (type instanceof ErrorType) return;

        new ErrorType(line, column, String.format(
                "Cannot return type %s for function of return type %s.", type, this
        ));
    }

    @Override
    public void assign(Type type, int line, int column) {
        if (type instanceof BooleanType) return;
        if (type instanceof ErrorType) return;

        new ErrorType(line, column, String.format(
                "Cannot assign type %s to type %s.", type, this
        ));
    }

    @Override
    public String suffix() {
        return "i";
    }

    @Override
    public int numberOfBytes() {
        return 2;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
