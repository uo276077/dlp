package ast.definitions;

import ast.AbstractASTNode;
import ast.Definition;
import ast.Statement;
import ast.Type;
import semantic.Visitor;

public class VarDefinition extends AbstractDefinition implements Statement {

    private int offset;
    private String name;
    private Type returnType;

    public VarDefinition(int line, int column, String name, Type type){
        super(line, column);
        this.name = name;
        setType(type);
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return getType() + " " + name;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public void setReturnType(Type type) {
        this.returnType = type;
    }

    @Override
    public Type getReturnType() {
        return returnType;
    }
}
