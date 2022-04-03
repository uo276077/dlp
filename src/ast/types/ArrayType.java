package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import semantic.Visitor;

public class ArrayType extends AbstractType {

    private Type elementType;
    private int size;

    private ArrayType(int line, int column, Type type, int size) {
        super(line, column);
        this.elementType = type;
        this.size = size;
    }

    public ArrayType(ArrayType oldType) {
        this(oldType.getLine(), oldType.getColumn(), oldType.getElementType(), oldType.getSize());
    }

    public void setElementType(Type elementType) {
        this.elementType = elementType;
    }

    public Type getElementType(){return elementType;}
    public int getSize(){return size;}


    public static ArrayType createArray(int line, int column, Type type, int size){

        Type oldType = type;
        ArrayType newArray = new ArrayType(line, column, type, size); //create new node
        ArrayType returnArray = newArray;
        if (type instanceof ArrayType){
            returnArray = (ArrayType) type;
            while (((ArrayType)oldType).getElementType() instanceof ArrayType) { //we need to keep a reference of the last array
                oldType = ((ArrayType) oldType).getElementType(); //we get last array node
            }
            (newArray).setElementType(((ArrayType) oldType).getElementType()); //link new node to old node's type
            ((ArrayType)oldType).setElementType(newArray); //link old node to new node
        }

        return returnArray;
    }

    @Override
    public String toString() {
        return "[" + size + "]"+ elementType.toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type squareBrackets(Type t, int line, int column) {
        if (t instanceof IntType)
            return this.elementType;
        if (t instanceof ErrorType)
            return t;
        return new ErrorType(line, column, String.format(
                "The type of %s cannot be used as an index.", t));
    }

    @Override
    public void assign(Type type, int line, int column) {
        if (type instanceof ArrayType)
            return;
        if (type instanceof ErrorType)
            return;
        new ErrorType(line, column, String.format(
                "Cannot assign type %s to type %s.", type, this
        ));
    }
}
