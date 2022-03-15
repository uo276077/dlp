package ast.types;

import ast.AbstractASTNode;
import ast.Definition;
import ast.Type;
import ast.definitions.VarDefinition;
import semantic.Visitor;

import java.util.ArrayList;
import java.util.List;

public class FunctionType extends AbstractASTNode implements Type {

    private Type returnType;
    private List<Definition> parameters;

    public FunctionType(int line, int column, Type returnType, List<Definition> parameters) {
        super(line, column);
        this.returnType = returnType;
        if (parameters != null)
            this.parameters = new ArrayList<>(parameters);
        else
            this.parameters = new ArrayList<>(); //empty if no parameters
    }

    public Type getReturnType() {
        return returnType;
    }

    public List<Definition> getParameters() {
        return parameters;
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

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
