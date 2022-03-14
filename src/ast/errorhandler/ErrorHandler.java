package ast.errorhandler;

import ast.types.ErrorType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
    //singleton
    private static ErrorHandler errorHandler = new ErrorHandler();
    private List<ErrorType> errors = new ArrayList<>();

    private ErrorHandler(){
    }

    public static ErrorHandler getInstance(){
        if (errorHandler == null)
            errorHandler = new ErrorHandler();
        return errorHandler;
    }

    public boolean anyErrors(){
        return !errors.isEmpty();
    }

    public void showErrors(PrintStream out){
        for (ErrorType error: errors) {
            out.println(error);
        }
    }

    public void addError(ErrorType error){
        errors.add(error);
    }
}
