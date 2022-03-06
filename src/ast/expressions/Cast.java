package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Type;

public class Cast extends AbstractASTNode implements Expression {

    private Expression expression;
    private Type type;

    public Cast(int line, int column, Type type, Expression expression) {
        super(line, column);
        this.expression = expression;
        this.type = type;
    }

    @Override
    public String toString() {
        return '(' + type.toString() + ')' + expression.toString();
    }
}
