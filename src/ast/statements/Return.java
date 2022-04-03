package ast.statements;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;
import semantic.Visitor;

public class Return extends AbstractStatement {

    private Expression returnExpression;

    public Return(int line, int column, Expression returnExpression) {
        super(line, column);
        this.returnExpression = returnExpression;
    }

    public Expression getReturnExpression() {
        return returnExpression;
    }

    @Override
    public String toString() {
        return "return " + returnExpression + ";";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
