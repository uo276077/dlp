package ast;

import java.util.List;

public interface Type extends ASTNode {
    Type arithmetic(Type t, int line, int column);
    Type castTo(Type castType, int line, int column);
    Type compare(Type t, int line, int column);
    Type dot(String field, int line, int column);
    Type squareBrackets(Type t, int line, int column);
    Type logical(Type t, int line, int column);
    Type modulus(Type t, int line, int column);
    Type toUnaryMinus(int line, int column);
    Type negation(int line, int column);

    void assign(Type type, int line, int column);
    void asBoolean(int line, int column);
    void readable(int line, int column);
    void writable(int line, int column);
    void returnable(Type type, int line, int column);

    Type parenthesis(List<Type> argTypes, int line, int column);

    int numberOfBytes();

    String suffix();

    Type superType(Type type);
}
