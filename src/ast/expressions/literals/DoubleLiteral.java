package ast.expressions.literals;

import ast.AbstractASTNode;
import ast.Expression;
import ast.types.DoubleType;

public class DoubleLiteral extends AbstractASTNode implements Expression {

    private double value;

    public DoubleLiteral(int line, int column, double value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
