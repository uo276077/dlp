package ast;

public interface Definition extends ASTNode{
    String getName();

    int getScope();
    void setScope(int scope);
}
