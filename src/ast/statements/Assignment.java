package ast.statements;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;

public class Assignment extends AbstractASTNode implements Statement {

    private Expression left;
    private Expression right;

    public Assignment(int line, int column, Expression left, Expression right) {
        super(line, column);
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.toString() + " = " + right.toString() + ";";
    }
}
