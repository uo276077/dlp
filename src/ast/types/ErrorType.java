package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import ast.errorhandler.ErrorHandler;

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
}