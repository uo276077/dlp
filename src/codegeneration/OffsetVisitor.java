package codegeneration;

import ast.Statement;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.types.FunctionType;
import ast.types.StructField;
import ast.types.StructType;
import semantic.AbstractVisitor;

/*
Global vars
//int sumGlobalBytes=0 in the beginning of the visitor
P
	VarDefinition: varDefinition -> type ID
R
	if (varDefinition.scope == 0){
		varDefinition.offset = sumGlobalBytes;
		sumGlobalBytes += type.numberOfBytes();
	}

Local vars
P
	FuncDefinition: funcDefinition -> type ID varDefinition* statement*
R
	int sumLocalBytes = 0;
	for (VarDefinition vd : varDefinition*) {
		sumLocalBytes += vd.type.numberOfBytes();
		vd.offset = sumLocalBytes;
	}

Params
P
	FunctionType: type1 -> type2 varDefinition*
R
	int sumParamBytes = 0;
	for (int i=varDefinition*.size()-1; i>-1; i--){
		VarDefinition vd = varDefinition*.get(i);
		vd.offset = 4 + sumParamBytes;
		sumParamBytes += vd.type.numberOfBytes();
	}

Fields
P
	StructType: type -> structField*
R
	int sumFieldBytes = 0;
	for(StructField sf : structField*){
		sf.offset = sumFieldBytes;
		sumFieldBytes += sf.type.numberOfBytes();
	}
*/
public class OffsetVisitor extends AbstractVisitor<Void, Void> {
    private int sumGlobalBytes = 0;

    @Override
    public Void visit(FuncDefinition funcDefinition, Void param) {
        funcDefinition.getType().accept(this, param);
        funcDefinition.getBody().forEach(b -> b.accept(this, param));

        int sumLocalBytes = 0;
        VarDefinition vd = null;
        for (Statement st: funcDefinition.getBody()) {
            //if (st instanceof VarDefinition vd) in new versions of java
            if (st instanceof VarDefinition){
                vd = (VarDefinition) st;
                sumLocalBytes += vd.getType().numberOfBytes();
                vd.setOffset(- sumLocalBytes);
            }
        }

        return null;
    }

    @Override
    public Void visit(VarDefinition varDefinition, Void param) {
        varDefinition.getType().accept(this, param);

        if (varDefinition.getScope() == 0){
            varDefinition.setOffset(sumGlobalBytes);
            sumGlobalBytes += varDefinition.getType().numberOfBytes();
        }

        return null;
    }

    @Override
    public Void visit(FunctionType functionType, Void param) {
        functionType.getReturnType().accept(this, param);
        functionType.getParameters().forEach(p -> p.accept(this, param));

        int sumParamBytes = 0;
        VarDefinition vd = null;
        for (int i=functionType.getParameters().size()-1; i>-1; i--){
            vd = (VarDefinition) functionType.getParameters().get(i);
            vd.setOffset(4 + sumParamBytes);
            sumParamBytes += vd.getType().numberOfBytes();
        }

        return null;
    }

    @Override
    public Void visit(StructType structType, Void param) {
        structType.getFields().forEach(field -> field.accept(this, param));

        int sumFieldBytes = 0;
        for (StructField sf: structType.getFields()) {
            sf.setOffset(sumFieldBytes);
            sumFieldBytes += sf.getType().numberOfBytes();
        }

        return null;
    }
}
