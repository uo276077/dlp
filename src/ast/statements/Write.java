package ast.statements;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;

public class Write extends AbstractASTNode implements Statement {

    private Expression argument;

    public Write(int line, int column, Expression argument) {
        super(line, column);
        this.argument = argument;
    }
}
