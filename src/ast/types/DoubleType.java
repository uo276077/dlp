package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import semantic.Visitor;

public class DoubleType extends AbstractASTNode implements Type {

    public DoubleType(int line, int column) {
        super(line, column);
    }

    @Override
    public String toString() {
        return "double ";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
