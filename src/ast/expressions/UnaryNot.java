package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;

public class UnaryNot extends AbstractASTNode implements Expression {

    private Expression expression;

    public UnaryNot(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return '!' + expression.toString();
    }

}
