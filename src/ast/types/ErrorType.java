package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import ast.errorhandler.ErrorHandler;
import semantic.Visitor;

public class ErrorType extends AbstractType {

    private String message;

    public ErrorType(int line, int column, String message){
        super(line, column);
        this.message = message;
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public String toString() {
        return "ERROR at line: " + getLine() + " and column: " + getColumn() + ": " + message;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type arithmetic(Type t, int line, int column) {
        return this;
    }

    @Override
    public Type squareBrackets(Type t, int line, int column) {
        return this;
    }

    @Override
    public Type castTo(Type castType, int line, int column) {
        return this;
    }

    @Override
    public Type compare(Type t, int line, int column) {
        return this;
    }

    @Override
    public Type dot(String field, int line, int column) {
        return this;
    }

    @Override
    public Type logical(Type t, int line, int column) {
        return this;
    }

    @Override
    public Type modulus(Type t, int line, int column) {
        return this;
    }

    @Override
    public Type toUnaryMinus(int line, int column) {
        return this;
    }

    @Override
    public Type negation(int line, int column) {
        return this;
    }

    @Override
    public void assign(Type type, int line, int column) {
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
    }
}