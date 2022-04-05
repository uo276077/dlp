package ast.definitions;

import ast.AbstractASTNode;
import ast.Definition;
import ast.Type;

public abstract class AbstractDefinition extends AbstractASTNode implements Definition {
    private int scope;
    private Type type;
    private int offset;

    public AbstractDefinition(int line, int column) {
        super(line, column);
    }

    @Override
    public int getScope() {
        return scope;
    }

    @Override
    public void setScope(int scope) {
        this.scope = scope;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {this.type = type;}

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public void setOffset(int offset) {
        this.offset = offset;
    }
}
