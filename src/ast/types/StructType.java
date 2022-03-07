package ast.types;

import ast.AbstractASTNode;
import ast.Statement;
import ast.Type;

import java.util.ArrayList;
import java.util.List;

public class StructType extends AbstractASTNode implements Type {

    private List<StructField> fields;

    public StructType(int line, int column, List<StructField> fields) {
        super(line, column);
        this.fields = new ArrayList<>(fields);
    }

    @Override
    public String toString() {
        return "struct {" + fieldsToString() + "}";
    }

    private String fieldsToString() {
        String res = "";
        String sep = "\n";
        for (StructField f: fields) {
            res += sep + f.toString() + ";";
        }
        return res;
    }
}
