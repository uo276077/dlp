package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Type;
import semantic.Visitor;

public class Cast extends AbstractExpression {

    private Expression expression;
    private Type castType;

    public Expression getExpression() {
        return expression;
    }

    public Type getCastType() {
        return castType;
    }

    public Cast(int line, int column, Type castType, Expression expression) {
        super(line, column);
        this.expression = expression;
        this.castType = castType;
    }

    @Override
    public String toString() {
        return '(' + castType.toString() + ')' + expression.toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
