package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;
import semantic.Visitor;

public class Logical extends AbstractExpression {

    private Expression left;
    private Expression right;
    private String operand;

    public Logical(int line, int column, Expression left, String operand, Expression right) {
        super(line, column);
        this.left = left;
        this.operand = operand;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.toString() + operand + right.toString();
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
