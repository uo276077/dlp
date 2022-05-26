package codegeneration;

/*
execute[[Program : program -> varDefinition* funcDefinition]] =
    <' * Global variables:>
    varDefinition.forEach(vd -> execute[[vd]])
    funcDefinitionforEach(fd -> execute[[fd]])

execute[[ Read: statement -> expression ]] =
		address[[expression]]
		< in > expression.type.suffix()
		< store > expression.type.suffix()

execute[[ Write: statement -> expression ]] =
		value[[expression]]
		< out > expression.type.suffix()

execute[[ Assignment: statement -> expression1 expression2 ]] =
		address[[expression1]]
		value[[expression2]]
		< store > expression1.type.suffix()

execute[[ FuncDefinition: funcdefinition -> type ID vardefinition* statement* ]] =
                           ID < : >
                           < ' * Parameters: >
                           execute[[type]]
                           < ' * Local variables: >
                           int bytesReturn =
                           int bytesLocal =
                           int bytesParam =
                           vardefinition.forEach(vd -> execute[[vd]])(bytesReturn, bytesLocal, bytesParam)
                           if (vardefinition.size() > 0)
                                      < enter > - vardefinition.get(vardefinition.size()-1).offset

execute[[VarDefinition: vardefinition -> type ID]] =
                   < ' * > type.toString() ID < ( offset > vardefinition.offset < ) >

execute[[While: statement1 -> expression statement2*]] =
		String conditionLabel = cg.nextLabel(),
			exitLabel = cg.nextLabel();
		conditionLabel <:>
		value[[expression]]
		<jz > exitLabel
		statement2*.forEach(stmt -> execute[[stmt]])
		<jmp > conditionLabel
		exitLabel <:>

execute[[IfElse: statement1 -> expression statement2* statement3*]] =
		String elseLabel = cg.nextLabel(),
				exitLabel = cg.nextLabel();
		value[[expression]]
		<jz > elseLabel
		statement2*.forEach(stmt -> execute[[stmt]])
		<jmp > exitLabel
		elseLabel <:>
		statement3*.forEach(stmt -> execute[[stmt]])
		exitLabel <:>

execute[[FunctionType: type1 -> type2 definition*]] =
        definition*.forEach(vd -> execute[[vd]])

//Invocation as a statement -> pop if not procedure (returnType not VoidType)
execute[[FunctionInvocation: statement -> expression1 expression2*]] =
				expression2*.forEach(arg -> value[[arg]])
				<call > expression1.name
				if ( !(expression1.type.returnType instanceOf VoidType) )
					<pop> expression1.type.returnType.suffix()

execute[[Return: statement -> expression]](int bytesReturn, int bytesLocal, int bytesParam) =
				value[[expression]]
				<ret > bytesReturn <, > bytesLocal <, > bytesParam
 */

import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.FunctionInvocation;
import ast.statements.*;
import ast.types.FunctionType;
import ast.types.VoidType;
import semantic.Visitor;

import java.util.List;

public class ExecuteCGVisitor extends AbstractCGVisitor<ExecuteCGDTO,Void> {
    private Visitor<Void,Void> address;
    private Visitor<Void,Void> value;

    public ExecuteCGVisitor(CodeGenerator cg, Visitor address, Visitor value) {
        super(cg);
        this.address = address;
        this.value = value;
    }

    @Override
    public Void visit(Program program, ExecuteCGDTO param) {

        cg.generateComment("Global variables:");

        program.getGlobalVariables().forEach(def -> def.accept(this, null));

        cg.finishProgram();

        program.getFuncDefinitions().forEach(def -> def.accept(this, null));

        cg.writeToFile();
        return null;
    }

    @Override
    public Void visit(FuncDefinition funcDefinition, ExecuteCGDTO param) {

        cg.newFunction(funcDefinition);

        cg.generateComment("Parameters:");
        funcDefinition.getType().accept(this, null);

        cg.generateComment("Local variables:");
        List<VarDefinition> vardefs = funcDefinition.getVarDefinitions();
        vardefs.forEach(vd -> vd.accept(this, null));

        int bytesLocal = vardefs.size() > 0
                        ? - vardefs.get(vardefs.size()-1).getOffset()
                        : 0;

        cg.allocateMemory(bytesLocal);

        int bytesReturn = ((FunctionType)funcDefinition.getType()).getReturnType().numberOfBytes();
        int bytesParams = funcDefinition.getType().numberOfBytes();

        ExecuteCGDTO paramDTO = new ExecuteCGDTO(bytesReturn, bytesLocal, bytesParams);

        funcDefinition
                .getBody()
                .stream()
                .filter(s -> !(s instanceof VarDefinition))
                .forEach(st -> st.accept(this, paramDTO));

        //check if it has a return already, if not -> we add a ret
        if (bytesReturn == 0)
            //bytes to return, bytes local variables, bytes arguments
            cg.ret(bytesReturn,
                    bytesLocal,
                    bytesParams);

        return null;
    }

    @Override
    public Void visit(VarDefinition varDefinition, ExecuteCGDTO param) {

        cg.commentVariable(varDefinition);

        return null;
    }


    @Override
    public Void visit(Assignment assign, ExecuteCGDTO param) {

        cg.writeLineNumber(assign.getLine());
        cg.generateComment("Assignment");

        assign.getLeft().accept(address, null);
        assign.getRight().accept(value, null);

        cg.store(assign.getLeft().getType());
        return null;
    }

    @Override
    public Void visit(Read readSt, ExecuteCGDTO param) {

        cg.writeLineNumber(readSt.getLine());

        readSt.getArgument().accept(address, null);
        cg.read(readSt.getArgument().getType());

        return null;
    }

    @Override
    public Void visit(Write writeSt, ExecuteCGDTO param) {

        cg.writeLineNumber(writeSt.getLine());
        cg.generateComment("Write");

        writeSt.getArgument().accept(value, null);
        cg.write(writeSt.getArgument().getType());

        return null;
    }

    @Override
    public Void visit(FunctionType functionType, ExecuteCGDTO param) {

        functionType.getParameters().forEach(vd -> vd.accept(this, null));

        return null;
    }

    @Override
    public Void visit(IfElse ifElse, ExecuteCGDTO param) {

        cg.writeLineNumber(ifElse.getLine());
        cg.generateComment("If statement");

        String elseLabel = cg.nextLabel(),
                exitLabel = cg.nextLabel();

        ifElse.getCondition().accept(value, null);
        cg.jumpConditionally(elseLabel, true);
        cg.generateComment("Body of the if branch");
        ifElse.getIfBody().forEach(stmt -> stmt.accept(this, param));
        cg.jump(exitLabel);

        cg.generateLabel(elseLabel);
        cg.generateComment("Body of the else branch");
        ifElse.getElseBody().forEach(stmt -> stmt.accept(this, param));

        cg.generateLabel(exitLabel);

        return null;
    }

    @Override
    public Void visit(While whileSt, ExecuteCGDTO param) {

        cg.writeLineNumber(whileSt.getLine());
        cg.generateComment("While");

        String conditionLabel = cg.nextLabel(),
                exitLabel = cg.nextLabel();

        cg.generateLabel(conditionLabel);

        whileSt.getCondition().accept(value, null);
        cg.jumpConditionally(exitLabel, true);

        cg.generateComment("Body of the while statement");
        whileSt.getBody().forEach(stmt -> stmt.accept(this, param));
        cg.jump(conditionLabel);

        cg.generateLabel(exitLabel);

        return null;
    }

    @Override
    public Void visit(FunctionInvocation invocation, ExecuteCGDTO param) {

        cg.writeLineNumber(invocation.getLine());

        invocation.getParameters().forEach(exp -> exp.accept(value, null));

        cg.callFunction(invocation.getName().getName());

        if (!( ((FunctionType)invocation.getName().getType()).getReturnType() instanceof VoidType))
            cg.pop(((FunctionType)invocation.getName().getType()).getReturnType().suffix());

        return null;
    }

    @Override
    public Void visit(Return returnSt, ExecuteCGDTO param) {

        cg.writeLineNumber(returnSt.getLine());
        cg.generateComment("Return");

        returnSt.getReturnExpression().accept(value, null);

        cg.ret(param.bytesReturn, param.bytesLocal, param.bytesParam);

        return null;
    }
}
