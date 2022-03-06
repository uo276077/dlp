package ast.definitions;

import ast.AbstractASTNode;
import ast.Definition;
import ast.Statement;
import ast.Type;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;

public class FuncDefinition extends AbstractASTNode implements Definition {

    private String name;
    private Type type;
    private List<Statement> statements;

    public FuncDefinition(int line, int column, String name, Type type, List<Statement> statements){
        super(line, column);
        this.name = name;
        this.type = type;
        this.statements = new ArrayList<>(statements);
    }

    @Override
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
