package ast.statements;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;
import semantic.Visitor;


public class Read extends AbstractStatement {

    private Expression argument;

    public Read(int line, int column, Expression argument) {
        super(line, column);
        this.argument = argument;
    }

    public Expression getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "read " + argument + ";";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
