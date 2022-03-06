package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import ast.definitions.VarDefinition;

import java.util.ArrayList;
import java.util.List;

public class FunctionType extends AbstractASTNode implements Type {

    private Type returnType;
    private List<VarDefinition> parameters;

    public FunctionType(int line, int column, Type returnType, List<VarDefinition> parameters) {
        super(line, column);
        this.returnType = returnType;
        this.parameters = new ArrayList<>(parameters);
    }
}
