package ast.types;

import ast.AbstractASTNode;
import ast.Definition;
import ast.Type;
import ast.definitions.VarDefinition;
import semantic.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FunctionType extends AbstractType {

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

    @Override
    public Type parenthesis(List<Type> argTypes, int line, int column) {
        if (this.parameters.stream().map(p -> p.getType()).collect(Collectors.toList()).equals(argTypes))
            return this.returnType;

        Optional<Type> error = argTypes.stream().filter(p -> p instanceof ErrorType).findFirst();
        if (error.isPresent()) return error.get();

        return new ErrorType(line, column, String.format(
                "The types of %s do not match the parameters for invocation of function.", argTypes
        ));
    }

}
