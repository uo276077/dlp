package ast.statements;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;

import java.util.ArrayList;
import java.util.List;

public class While extends AbstractASTNode implements Statement {

    private Expression condition;
    private List<Statement> body;

    public While(int line, int column, Expression condition, List<Statement> body) {
        super(line, column);
        this.condition = condition;
        this.body = new ArrayList<>(body);
    }

    @Override
    public String toString() {
        return "while (" + condition + ") {" + bodyToString() + "}";
    }

    private String bodyToString() {
        String res = "";
        String sep = "\n";
        for (Statement p: body) {
            res += sep + p.toString() + ";";
        }
        return res;
    }
}
