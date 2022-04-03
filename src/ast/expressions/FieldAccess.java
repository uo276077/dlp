package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;
import semantic.Visitor;

public class FieldAccess extends AbstractExpression {

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

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    public Expression getStruct() {
        return struct;
    }

    public String getField() {
        return field;
    }
}
