package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class Logical extends AbstractExpression {

    private Expression op1;
    private Expression op2;
    private String operand;

    public Logical(int line, int column, Expression left, String operand, Expression right) {
        super(line, column);
        this.op1 = left;
        this.operand = operand;
        this.op2 = right;
    }

    @Override
    public String toString() {
        return op1.toString() + operand + op2.toString();
    }

    public Expression getOp1() {
        return op1;
    }

    public Expression getOp2() {
        return op2;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
