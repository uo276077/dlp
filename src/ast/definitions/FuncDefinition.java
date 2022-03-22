package ast.definitions;

import ast.*;
import semantic.Visitor;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;

public class FuncDefinition extends AbstractDefinition {

    private String name;
    private Type type;
    private List<Statement> body;
    private List<Definition> parameters;

    public FuncDefinition(int line, int column, Type type, String name, List<Definition> parameters, List<Statement> body){
        super(line, column);
        this.name = name;
        this.type = type;
        if (parameters != null)
            this.parameters = new ArrayList<>(parameters);
        else
            this.parameters = new ArrayList<>(); //empty if no parameters
        this.body = new ArrayList<>(body);
    }

    @Override
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " " + name + "(" + parametersToString() + ") {" + bodyToString() + "}\n";
    }

    private String bodyToString() {
        String res = "";
        String sep = "\n";
        for (Statement p: body) {
            res += sep + p.toString() + ";";
        }
        return res;
    }

    private String parametersToString() {
        String res = "";
        String sep = "";
        for (Definition p: parameters) {
            res += sep + p.toString();
            if (sep.isBlank())
                sep = ", ";
        }
        return res;
    }

    public List<Statement> getBody() {
        return body;
    }

    public List<Definition> getParameters() {
        return parameters;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
