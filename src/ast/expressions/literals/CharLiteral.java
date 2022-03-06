package ast.expressions.literals;

import ast.AbstractASTNode;
import ast.Expression;
import ast.types.CharType;

public class CharLiteral extends AbstractASTNode implements Expression {

    private char value;

    public CharLiteral(int line, int column, char value) {
        super(line, column);
        this.value = value;
    }
}
