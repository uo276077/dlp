package ast.types;

import ast.Type;

public class StructField {

    private String name;
    private Type type;

    public StructField(Type type, String name){
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
