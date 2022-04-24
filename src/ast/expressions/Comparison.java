package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class Comparison extends AbstractExpression {

    private Expression op1;
    private Expression op2;

    public Expression getOp1() {
        return op1;
    }

    public Expression getOp2() {
        return op2;
    }

    private String operand;

    public Comparison(int line, int column, Expression left, String operand, Expression right) {
        super(line, column);
        this.op1 = left;
        this.op2 = right;
        this.operand = operand;
    }

    public String getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return op1.toString() + operand + op2.toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
