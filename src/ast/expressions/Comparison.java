package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;

public class Comparison extends AbstractASTNode implements Expression {

    private Expression left;
    private Expression right;
    private String operand;

    public Comparison(int line, int column, Expression left, String operand, Expression right) {
        super(line, column);
        this.left = left;
        this.right = right;
        this.operand = operand;
    }

    @Override
    public String toString() {
        return left.toString() + operand + right.toString();
    }
}
