package ast.types;

import ast.AbstractASTNode;
import ast.Type;

import java.util.List;

public abstract class AbstractType extends AbstractASTNode implements Type {

    public AbstractType(int line, int column) {
        super(line, column);
    }

    @Override
    public Type arithmetic(Type t, int line, int column) {
        return new ErrorType(line, column, String.format(
                "The types of %s and %s do not support arithmetic operations.", this, t
        ));
    }

    @Override
    public Type squareBrackets(Type t, int line, int column) {
        return new ErrorType(line, column, String.format(
                "The types of %s and %s do not support array indexing.", this, t
        ));
    }

    @Override
    public Type castTo(Type castType, int line, int column) {
        return new ErrorType(line, column, String.format(
                "The type %s does not support casting to %s.", this, castType
        ));
    }

    @Override
    public Type compare(Type t, int line, int column) {
        return new ErrorType(line, column, String.format(
                "The types of %s and %s do not support comparison.", this, t
        ));
    }

    @Override
    public Type dot(String field, int line, int column) {
        return new ErrorType(line, column, String.format(
                "The type of %s does not support field accessing.", this
        ));
    }

    @Override
    public Type logical(Type t, int line, int column) {
        return new ErrorType(line, column, String.format(
                "The types of %s and %s do not support logical operations.", this, t
        ));
    }

    @Override
    public Type modulus(Type t, int line, int column) {
        return new ErrorType(line, column, String.format(
                "The types of %s and %s do not support modulus operation.", this, t
        ));
    }

    @Override
    public Type toUnaryMinus(int line, int column) {
        return new ErrorType(line, column, String.format(
                "The type of %s does not support unary minus.", this
        ));
    }

    @Override
    public Type negation(int line, int column) {
        return new ErrorType(line, column, String.format(
                "The type of %s does not support negation.", this
        ));
    }

    @Override
    public void assign(Type type, int line, int column) {
        new ErrorType(line, column, String.format(
                "The type of %s cannot be assigned to the type of %s.", type, this
        ));
    }

    @Override
    public void asBoolean(int line, int column) {
        new ErrorType(line, column, String.format(
                "The type of %s cannot be evaluated as a boolean.", this
        ));
    }

    @Override
    public void readable(int line, int column) {
        new ErrorType(line, column, String.format(
                "The type of %s cannot be read.", this
        ));
    }

    @Override
    public void writable(int line, int column) {
        new ErrorType(line, column, String.format(
                "The type of %s cannot be written.", this
        ));
    }

    @Override
    public void returnable(Type type, int line, int column) {
        new ErrorType(line, column, String.format(
                "The type of %s cannot be returned for return type %s.", type, this
        ));
    }

    @Override
    public Type parenthesis(List<Type> argTypes, int line, int column) {
        return new ErrorType(line, column, String.format(
                "The types of %s and %s do not support invocation.", this, argTypes
        ));
    }

    @Override
    public void invoke(List<Type> argTypes, int line, int column) {
        new ErrorType(line, column, String.format(
                "The types of %s and %s do not support invocation.", this, argTypes
        ));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }
}
