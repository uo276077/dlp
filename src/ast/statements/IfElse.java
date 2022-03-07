package ast.statements;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;

import java.util.ArrayList;
import java.util.List;

public class IfElse extends AbstractASTNode implements Statement {

    private List<Statement> ifBody;
    private List<Statement> elseBody;
    private Expression condition;

    public IfElse(int line, int column, Expression condition, List<Statement> ifBody) {
        super(line, column);
        this.condition = condition;
        this.ifBody = new ArrayList<>(ifBody);
    }

    public IfElse(int line, int column, Expression condition, List<Statement> ifBody, List<Statement> elseBody) {
        this(line, column, condition, ifBody);
        this.elseBody = new ArrayList<>(elseBody);
    }

    @Override
    public String toString() {
        return "if (" + condition.toString() + ") {" + bodyToString(ifBody) + "}"
                + (elseBody == null ? ""
                                      : "else {" + bodyToString(elseBody) + "}");
    }

    private String bodyToString(List<Statement> body) {
        String res = "";
        String sep = "\n";
        for (Statement p: body) {
            res += sep + p.toString() + ";";
        }
        return res;
    }
}
