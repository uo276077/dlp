package ast.expressions;

import ast.AbstractASTNode;
import ast.Definition;
import ast.Expression;
import semantic.Visitor;

public class Variable extends AbstractExpression {

    private String name;
    private Definition definition;

    public Variable(int line, int column, String name) {
        super(line, column);
		this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    public Definition getDefinition() {
        return definition;
    }

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }
}
