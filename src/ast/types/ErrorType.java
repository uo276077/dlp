package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import ast.errorhandler.ErrorHandler;
import semantic.Visitor;

public class ErrorType extends AbstractASTNode implements Type {

    private String message;

    public ErrorType(int line, int column, String message){
        super(line, column);
        this.message = message;
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public String toString() {
        return "ERROR at line: " + getLine() + " and column: " + getColumn() + ": " + message;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}