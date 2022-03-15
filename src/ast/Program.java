package ast;

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
}
