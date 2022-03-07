package ast.expressions.literals;

import ast.AbstractASTNode;
import ast.Expression;
import ast.types.IntType;

public class IntLiteral extends AbstractASTNode implements Expression {

    private int value;

    public IntLiteral(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
