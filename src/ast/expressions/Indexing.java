package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;
import semantic.Visitor;

public class Indexing extends AbstractExpression {

    private Expression index;
    private Expression array;

    public Indexing(int line, int column, Expression array, Expression index) {
        super(line, column);
        this.array = array;
        this.index = index;
    }

    @Override
    public String toString() {
        return array.toString() + '[' + index.toString() + ']';
    }

    public Expression getIndex() {
        return index;
    }

    public Expression getArray() {
        return array;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
