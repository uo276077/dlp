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
import semantic.Visitor;

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
        return super.visit(program, param);
    }

    @Override
    public Void visit(FuncDefinition funcDefinition, Void param) {
        return super.visit(funcDefinition, param);
    }

    @Override
    public Void visit(VarDefinition varDefinition, Void param) {
        return super.visit(varDefinition, param);
    }

    /*
    * address[[expression1]]
		value[[expression2]]
		< store > expression1.type.suffix()*/
    @Override
    public Void visit(Assignment assign, Void param) {

        assign.getLeft().accept(address, null);
        assign.getRight().accept(value, null);
        //TODO

        return null;
    }

    @Override
    public Void visit(Read readSt, Void param) {

        readSt.getArgument().accept(address, null);
        cg.read(readSt.getArgument().getType());

        return null;
    }

    @Override
    public Void visit(Write writeSt, Void param) {

        writeSt.getArgument().accept(value, null);
        cg.write(writeSt.getArgument().getType());

        return null;
    }
}
