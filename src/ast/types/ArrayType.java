package ast.types;

import ast.AbstractASTNode;
import ast.Type;

public class ArrayType extends AbstractASTNode implements Type {

    private Type type;
    private int size;

    private ArrayType(int line, int column, Type type, int size) {
        super(line, column);
        this.type = type;
        this.size = size;
    }

    public ArrayType(ArrayType oldType) {
        this(oldType.getLine(), oldType.getColumn(), oldType.getType(), oldType.getSize());
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType(){return type;}
    public int getSize(){return size;}


    public static ArrayType createArray(int line, int column, Type type, int size){

        Type oldType = type;
        ArrayType newArray = new ArrayType(line, column, type, size); //create new node
        ArrayType returnArray = newArray;
        if (type instanceof ArrayType){
            returnArray = (ArrayType) type;
            while (((ArrayType)oldType).getType() instanceof ArrayType) { //we need to keep a reference of the last array
                oldType = ((ArrayType) oldType).getType(); //we get last array node
            }
            (newArray).setType(((ArrayType) oldType).getType()); //link new node to old node's type
            ((ArrayType)oldType).setType(newArray); //link old node to new node
        }

        return returnArray;
    }

    @Override
    public String toString() {
        return "[" + size + "]"+ type.toString();
    }
}
