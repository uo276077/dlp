package ast.definitions;

import ast.AbstractASTNode;
import ast.Definition;
import ast.Statement;
import ast.Type;
import semantic.Visitor;

public class VarDefinition extends AbstractASTNode implements Definition, Statement {

    private int offset;
    private String name;
    private Type type;

    public VarDefinition(int line, int column, String name, Type type){
        super(line, column);
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
