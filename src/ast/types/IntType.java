package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import semantic.Visitor;

public class IntType extends AbstractASTNode implements Type {

    public IntType(int line, int column) {
        super(line, column);
    }

    @Override
    public String toString() {
        return "int ";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
