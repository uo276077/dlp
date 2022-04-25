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
 */

import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.statements.Assignment;
import ast.statements.Read;
import ast.statements.Write;
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
        if (vardefs.size() > 0)
            cg.allocateMemory(vardefs);

        funcDefinition.getBody().stream().filter(s -> !(s instanceof VarDefinition)).forEach(st -> st.accept(this, null));

        //bytes to return, bytes local variables, bytes arguments
        cg.ret(funcDefinition.getBody().get(0).getReturnType().numberOfBytes(),
                vardefs.size()>0 ? Math.abs(vardefs.get(vardefs.size()-1).getOffset()) : 0,
                0); //TODO function type ??

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
}
