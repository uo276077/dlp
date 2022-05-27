package ast.expressions.literals;

import ast.expressions.AbstractExpression;
import semantic.Visitor;

public class FalseLiteral extends AbstractExpression {

    private boolean value;

    public FalseLiteral(int line, int column) {
        super(line, column);
        value = false;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "false";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
