package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import semantic.Visitor;

import java.util.ArrayList;
import java.util.List;

public class StructType extends AbstractASTNode implements Type {

    private List<StructField> fields = new ArrayList<>();

    public StructType(int line, int column, List<StructField> fields) {
        super(line, column);
        for (StructField sf: fields) {
            if(this.fields.contains(sf))
                new ErrorType(sf.getLine(), sf.getColumn(), "The field \"" + sf +
                        "\" is duplicated in the struct");
            else
                this.fields.add(sf);
        }
    }

    public List<StructField> getFields() {
        return fields;
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

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
