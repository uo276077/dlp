package ast.expressions.literals;

import ast.AbstractASTNode;
import ast.Expression;
import ast.expressions.AbstractExpression;
import ast.types.CharType;
import semantic.Visitor;

public class CharLiteral extends AbstractExpression {

    private char value;

    public CharLiteral(int line, int column, char value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
