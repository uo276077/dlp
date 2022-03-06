package ast.statements;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;

public class Return extends AbstractASTNode implements Statement {

    private Expression returnExpression;

    public Return(int line, int column, Expression returnExpression) {
        super(line, column);
        this.returnExpression = returnExpression;
    }
}
