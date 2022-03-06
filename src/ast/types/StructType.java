package ast.types;

import ast.AbstractASTNode;
import ast.Type;

import java.util.ArrayList;
import java.util.List;

public class StructType extends AbstractASTNode implements Type {

    private List<StructField> fields;

    public StructType(int line, int column, List<StructField> fields) {
        super(line, column);
        this.fields = new ArrayList<>(fields);
    }
}
