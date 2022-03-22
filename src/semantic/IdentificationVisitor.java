package semantic;

import ast.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.types.ErrorType;

public class IdentificationVisitor extends AbstractVisitor<Void, Void> {
    private SymbolTable symbolTable = new SymbolTable();

    @Override
    public Void visit(Variable e, Void param) {
        Definition definition = symbolTable.find(e.getName());
        if (definition == null)
            new ErrorType(e.getLine(),e.getColumn(),"Variable not defined");
        e.setDefinition(definition);
        return null;
    }

    @Override
    public Void visit(FuncDefinition funcDefinition, Void param) {
        if (!symbolTable.insert(funcDefinition))
            new ErrorType(funcDefinition.getLine(), funcDefinition.getColumn(), "Definition already exists");

        symbolTable.set(); // create a new scope

        //traverse children
        funcDefinition.getParameters().forEach(p -> p.accept(this, null));
        funcDefinition.getBody().forEach(p -> p.accept(this, null));

        symbolTable.reset(); // remove scope
        return null;
    }

    @Override
    public Void visit(VarDefinition varDefinition, Void param) {
        if (!symbolTable.insert(varDefinition))
            new ErrorType(varDefinition.getLine(), varDefinition.getColumn(), "Definition already exists");

        return null;
    }
}
