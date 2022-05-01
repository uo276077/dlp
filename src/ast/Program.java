package ast;

import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import semantic.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Program extends AbstractASTNode{

    private List<Definition> definitions;

    public Program(int line, int column, List<Definition> defs){
        super(line, column);
        this.definitions = new ArrayList<Definition>(defs);
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    public List<Definition> getGlobalVariables() {
        List<Definition> vardefs = new ArrayList<>();

        for (Definition def: definitions) {
            if (def instanceof VarDefinition)
                vardefs.add(def);
        }
        
        return vardefs;
    }

    public List<Definition> getFuncDefinitions() {
        List<Definition> funcdefs = new ArrayList<>();

        for (Definition def: definitions) {
            if (def instanceof FuncDefinition)
                funcdefs.add(def);
        }

        return funcdefs;
    }
}
