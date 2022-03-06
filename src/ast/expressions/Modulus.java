package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;

public class Modulus extends AbstractASTNode implements Expression {

    private Expression left;
    private Expression right;

    public Modulus(int line, int column, Expression left, Expression right) {
        super(line, column);
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.toString() + '%' + right.toString();
    }
}
