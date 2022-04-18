package ast;

public interface Definition extends ASTNode{
    String getName();

    int getScope();
    void setScope(int scope);

    Type getType();
    void setType(Type type);

}
