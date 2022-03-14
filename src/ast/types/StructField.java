package ast.types;

import ast.AbstractASTNode;
import ast.Type;

import java.util.Objects;

public class StructField extends AbstractASTNode {

    private String name;
    private Type type;

    public StructField(int line, int column, Type type, String name){
        super(line, column);
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return type + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructField that = (StructField) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}