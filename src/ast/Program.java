package ast;

import java.util.ArrayList;
import java.util.List;

public class Program extends AbstractASTNode{

    private List<Definition> definitions;

    public Program(int line, int column, List<Definition> defs){
        super(line, column);
        this.definitions = new ArrayList<Definition>(defs);
    }
}
