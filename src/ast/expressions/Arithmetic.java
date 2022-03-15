package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;
import semantic.Visitor;

public class Arithmetic extends AbstractExpression {

    private Expression left;
    private Expression right;
    private String operand;

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

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

    public static Expression createArithmeticOperation(int line, int column, Expression left, String operand,
                                                       Expression right){
        if (operand.equals("%"))
            return new Modulus(line, column, left, right);
        return new Arithmetic(line, column, left, operand, right);
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
