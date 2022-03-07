package ast.statements;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;


public class Read extends AbstractASTNode implements Statement {

    private Expression argument;

    public Read(int line, int column, Expression argument) {
        super(line, column);
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "read " + argument + ";";
    }
}
