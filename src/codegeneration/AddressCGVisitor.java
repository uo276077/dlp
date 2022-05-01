package codegeneration;

/*
address[[FieldAccess: expression1 -> expression2 ID]] =
		address[[expression2]]
		<pushi> expression2.type.getField(ID).offset
		<addi>

address[[ Indexing: expression1 -> expression2 expression3 ]] =
		address[[ expression2 ]]
		value[[ expression3 ]]
		<pushi> expression1.type.numberOfBytes()
		<muli>
		<addi>

address[[ Variable: expression -> ID ]] =
		if (expression.definition.scope > 0) {
			<push bp>
			<pushi> expression.definition.offset
			<addi>
		} else
		    <pusha> expression.definition.offset
*/

import ast.definitions.VarDefinition;
import ast.expressions.FieldAccess;
import ast.expressions.Indexing;
import ast.expressions.Variable;
import ast.types.StructType;

public class AddressCGVisitor extends AbstractCGVisitor<Void, Void> {

    private ValueCGVisitor value;

    public void setValueVisitor(ValueCGVisitor value){
        this.value = value;
    }

    public AddressCGVisitor(CodeGenerator cg) {
        super(cg);
    }

    @Override
    public Void visit(Variable variable, Void param) {

        cg.pushAddress((VarDefinition)variable.getDefinition());

        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {

        fieldAccess.getStruct().accept(this, null);
        cg.pushInt(((StructType)fieldAccess.getStruct().getType()).getField(fieldAccess.getField()).getOffset());
        cg.addIntegers();

        return null;
    }

    @Override
    public Void visit(Indexing indexing, Void param) {

        indexing.getArray().accept(this, null);
        indexing.getIndex().accept(value, null);
        cg.pushInt(indexing.getType().numberOfBytes());
        cg.multiplyIntegers();
        cg.addIntegers();

        return null;
    }
}
