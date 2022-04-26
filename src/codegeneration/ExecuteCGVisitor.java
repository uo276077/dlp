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
                           vardefinition.forEach(vd -> execute[[vd]])
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
 */

import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.statements.*;
import ast.types.FunctionType;
import semantic.Visitor;

import java.util.List;

public class ExecuteCGVisitor extends AbstractCGVisitor<Void,Void> {
    private Visitor<Void,Void> address;
    private Visitor<Void,Void> value;

    public ExecuteCGVisitor(CodeGenerator cg, Visitor address, Visitor value) {
        super(cg);
        this.address = address;
        this.value = value;
    }

    @Override
    public Void visit(Program program, Void param) {

        cg.generateComment("Global variables:");

        cg.finishProgram(); //TODO

        program.getDefinitions().forEach(def -> def.accept(this, null));


        cg.writeToFile();
        return null;
    }

    @Override
    public Void visit(FuncDefinition funcDefinition, Void param) {

        cg.newFunction(funcDefinition);

        cg.generateComment("Parameters:");
        funcDefinition.getType().accept(this, null);

        cg.generateComment("Local variables:");
        List<VarDefinition> vardefs = funcDefinition.getVarDefinitions();
        vardefs.forEach(vd -> vd.accept(this, null));

        int bytesLocal = vardefs.size() > 0
                        ? - vardefs.get(vardefs.size()-1).getOffset()
                        : 0;
        if (bytesLocal > 0)
            cg.allocateMemory(bytesLocal);

        int bytesReturn = funcDefinition.getBody().get(0).getReturnType().numberOfBytes();
        int bytesParams = funcDefinition.getType().numberOfBytes(); //TODO

        funcDefinition.getBody().stream().filter(s -> !(s instanceof VarDefinition)).forEach(st -> st.accept(this, null));

        //check if it has a return already, if not -> we add a ret
        if (bytesReturn == 0)
            //bytes to return, bytes local variables, bytes arguments
            cg.ret(bytesReturn,
                    bytesLocal,
                    bytesParams);

        return null;
    }

    @Override
    public Void visit(VarDefinition varDefinition, Void param) {

        cg.commentVariable(varDefinition);

        return null;
    }


    @Override
    public Void visit(Assignment assign, Void param) {

        cg.writeLineNumber(assign.getLine());

        assign.getLeft().accept(address, null);
        assign.getRight().accept(value, null);

        cg.store(assign.getLeft().getType());
        return null;
    }

    @Override
    public Void visit(Read readSt, Void param) {

        cg.writeLineNumber(readSt.getLine());

        readSt.getArgument().accept(address, null);
        cg.read(readSt.getArgument().getType());

        return null;
    }

    @Override
    public Void visit(Write writeSt, Void param) {

        cg.writeLineNumber(writeSt.getLine());

        writeSt.getArgument().accept(value, null);
        cg.write(writeSt.getArgument().getType());

        return null;
    }

    @Override
    public Void visit(FunctionType functionType, Void param) {
        //TODO
        return null;
    }

    @Override
    public Void visit(IfElse ifElse, Void param) {

        String elseLabel = cg.nextLabel(),
                exitLabel = cg.nextLabel();

        ifElse.getCondition().accept(value, null);
        cg.jumpConditionally(elseLabel, true);
        ifElse.getIfBody().forEach(stmt -> stmt.accept(this, null));
        cg.jump(exitLabel);

        ifElse.getElseBody().forEach(stmt -> stmt.accept(this, null));

        cg.generateLabel(exitLabel);

        return null;
    }

    @Override
    public Void visit(While whileSt, Void param) {

        String conditionLabel = cg.nextLabel(),
                exitLabel = cg.nextLabel();

        cg.generateLabel(conditionLabel);

        whileSt.getCondition().accept(value, null);
        cg.jumpConditionally(exitLabel, true);

        whileSt.getBody().forEach(stmt -> stmt.accept(this, null));
        cg.jump(conditionLabel);

        cg.generateLabel(exitLabel);

        return null;
    }
}
