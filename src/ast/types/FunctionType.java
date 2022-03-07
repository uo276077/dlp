package ast.types;

import ast.AbstractASTNode;
import ast.Definition;
import ast.Expression;
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
        if (parameters != null)
            this.parameters = new ArrayList<>(parameters);
        else
            this.parameters = new ArrayList<>(); //empty if no parameters
    }

    @Override
    public String toString() {
        return returnType + "(" + parametersToString() + ")";
    }

    private String parametersToString() {
        String res = "";
        String sep = "";
        for (Definition p: parameters) {
            res += sep + p.toString();
            if (sep.isBlank())
                sep = ", ";
        }
        return res;
    }
}
