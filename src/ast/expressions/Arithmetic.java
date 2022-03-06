package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;

public class Arithmetic extends AbstractASTNode implements Expression {

    private Expression left;
    private Expression right;
    private String operand;

    public Arithmetic(int line, int column, Expression left, String operand, Expression right) {
        super(line, column);
        this.left = left;
		this.operand = operand;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.toString() + operand + right.toString();
    }
}
