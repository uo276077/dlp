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

    public FuncDefinition(int line, int column, Type type, String name, List<Statement> body){
        super(line, column);
        this.name = name;
        this.type = type;
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
        return type + " " + name + "("  + ") {" + bodyToString() + "}\n";
    }

    private String bodyToString() {
        String res = "";
        String sep = "\n";
        for (Statement p: body) {
            res += sep + p.toString() + ";";
        }
        return res;
    }


    public List<Statement> getBody() {
        return body;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
