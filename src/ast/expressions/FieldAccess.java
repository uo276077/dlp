package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;

public class FieldAccess extends AbstractASTNode implements Expression {

    private Expression struct;
    private String field;

    public FieldAccess(int line, int column, Expression struct, String field) {
        super(line, column);
        this.struct = struct;
        this.field = field;
    }

    @Override
    public String toString() {
        return struct.toString() + '.' + field.toString();
    }
}
