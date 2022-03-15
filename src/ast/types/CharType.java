package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import semantic.Visitor;

public class CharType extends AbstractASTNode implements Type {

    public CharType(int line, int column) {
        super(line, column);
    }

    @Override
    public String toString() {
        return "char ";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
