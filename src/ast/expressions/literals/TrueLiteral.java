package ast.expressions.literals;

import ast.expressions.AbstractExpression;
import semantic.Visitor;

public class TrueLiteral extends AbstractExpression {

    private boolean value;

    public TrueLiteral(int line, int column) {
        super(line, column);
        value = true;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "true";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
