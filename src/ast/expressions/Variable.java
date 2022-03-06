package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;

public class Variable extends AbstractASTNode implements Expression {

    private String name;

    public Variable(int line, int column, String name) {
        super(line, column);
		this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
