package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;

import java.util.ArrayList;
import java.util.List;

public class FunctionInvocation extends AbstractASTNode implements Statement, Expression {

    private List<Expression> parameters;
    private Variable name;

    public FunctionInvocation(int line, int column, Variable name, List<Expression> parameters) {
        super(line, column);
        if (parameters != null)
            this.parameters = new ArrayList<>(parameters);
        else
            this.parameters = new ArrayList<>(); //empty to avoid nullPointerException
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toString() + '(' + parametersToString(parameters) + ')';
    }

    private String parametersToString(List<Expression> parameters) {
        String res = "";
        String sep = "";
        for (Expression p: parameters) {
            res += sep + p.toString();
            if (sep.isBlank())
                sep = ", ";
        }
        return res;
    }
}
