package ast.types;

import ast.AbstractASTNode;
import ast.Type;

public class CharType extends AbstractASTNode implements Type {

    public CharType(int line, int column) {
        super(line, column);
    }
}