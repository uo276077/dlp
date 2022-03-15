package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;
import semantic.Visitor;

public class Comparison extends AbstractExpression {

    private Expression left;
    private Expression right;

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

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

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return null;
    }
}
