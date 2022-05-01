package ast.types;

import ast.Type;
import semantic.Visitor;

import java.util.ArrayList;
import java.util.List;

public class StructType extends AbstractType {

    private List<StructField> fields = new ArrayList<>();

    public StructType(int line, int column, List<StructField> fields) {
        super(line, column);
        for (StructField sf: fields) {
            if(this.fields.contains(sf))
                new ErrorType(sf.getLine(), sf.getColumn(), String.format(
                        "The field %s is duplicated in the struct.", sf.getName()
                ));
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
        String sep = " ";
        for (StructField f: fields) {
            res += f.toString() + ";" + sep;
        }
        return res;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type dot(String field, int line, int column) {
        for (StructField f: this.fields) {
            if (f.getName().equals(field))
                return f.getType();
        }
        return new ErrorType(line, column, String.format(
                "Field %s is not defined in the struct.", field
        ));
    }

    @Override
    public int numberOfBytes() {
        return fields.stream().mapToInt(sf -> sf.getType().numberOfBytes()).sum();
    }

    public StructField getField(String field) {
        for (StructField sf: fields) {
            if (sf.getName().equals(field))
                return sf;
        }
        return null;
    }
}
