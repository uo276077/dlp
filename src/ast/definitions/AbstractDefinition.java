package ast.definitions;

import ast.AbstractASTNode;
import ast.Definition;

public abstract class AbstractDefinition extends AbstractASTNode implements Definition {
    private int scope;

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
}
