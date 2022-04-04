package semantic;

import ast.Type;
import ast.definitions.FuncDefinition;
import ast.expressions.*;
import ast.expressions.literals.CharLiteral;
import ast.expressions.literals.DoubleLiteral;
import ast.expressions.literals.IntLiteral;
import ast.statements.*;
import ast.types.*;

import java.util.List;
import java.util.stream.Collectors;

/*
P:
	Arithmetic: expression1 -> expression2 (+|-|*|/) expression3
R:
	expression1.type = expression2.type.arithmetic(expression3.type);

P:
	Cast:  expression1 -> type expression2
R:
	expression1.type = expression2.type.castTo(type);

P:
	Comparison: expression1 -> expression2 (>|>=|<|<=|!=|==) expression3
R:
	expression1.type = expression2.type.compare(expression3.type);

P:
	FieldAccess: expression1 -> expression2 ID
R:
	expression1.type = expression2.type.dot(ID);

P: (FunctionInvocation can also be a statement)
	FunctionInvocation: expression1 -> expression2 expression3*
R:
	List<Type> argTypes = expression3*.stream()
								.map(exp -> exp.getType())
								.toList(); // .collect(Collectors.toList);
	expression1.type = expression2.type.parenthesis(argTypes);

P:
	Indexing: expression1 -> expression2 expression3
R:
	expression1.type = expression2.type.squareBrackets(expression3.type);

P:
	Logical: expression1 -> expression2 ('&&'|'||') expression3
R:
	expression1.type = expression2.type.logical(expression3.type);

P:
	Modulus: expression1 -> expression2 expression3
R:
	expression1.type = expression2.type.modulus(expression3.type);

P:
	UnaryMinus: expression1 -> expression2
R:
	expression1.type = expression2.type.toUnaryMinus();

P:
	UnaryNot: expression1 -> expression2
R:
	expression1.type = expression2.type.negation();

P:
	Variable: expression -> definition ID
R:
	if (expression.definition == null)
		expression.type = new ErrorType(expression.getLine(),expression.getColumn(),"Variable not defined");
	else
		expression.type = expression.definition.type;

P:
    CharLiteral: expression -> ID
R:
    expression.type = new CharType();

P:
    DoubleLiteral: expression -> number
R:
    expression.type = new DoubleType();

P:
    IntLiteral: expression -> number
R:
    expression.type = new IntType();

P:
	Assignment: statement1 -> expression1 expression2
R:
	expression1.type.assign(expression2.type);

P:
	IfElse: statement1 -> expression statement2* statement3*
R:
	expression.type.asBoolean();

P:
	Read: statement -> expression
R:
	expression.type.readable();

P:
	While: statement1 -> expression statement2*
R:
	expression.type.asBoolean();

P:
	Write: statement -> expression
R:
	expression.type.writable();

P:
	FunctionInvocation: statement -> expression1 expression2*
R:
	List<Type> argTypes = expression2*.stream()
								.map(exp -> exp.getType())
								.toList(); // .collect(Collectors.toList);
	expression1.type.invoke(argTypes);

P:
	FuncDefinition: definition -> type statement*
R:
	statement.forEach(st -> st.returnType = type.returnType);

P:
	Return: statement -> expression
R:
	statement.returnType.returnable(expression);

     */
public class TypeCheckingVisitor extends AbstractVisitor<Void, Void> {


    // ----------------------- EXPRESSIONS ------------------------------


    @Override
    public Void visit(Arithmetic arith, Void param) {
        arith.getLeft().accept(this, param);
        arith.getRight().accept(this, param);
        arith.setLvalue(false);

        arith.setType(arith.getRight().getType().arithmetic(arith.getLeft().getType(),
                arith.getLine(), arith.getColumn()));
        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {
        cast.getExpression().accept(this,param);
        cast.getCastType().accept(this, param);
        cast.setLvalue(false);

        cast.setType(cast.getExpression().getType().castTo(cast.getCastType(),
                cast.getLine(), cast.getColumn()));
        return null;
    }

    @Override
    public Void visit(Comparison comparison, Void param) {
        comparison.getOp1().accept(this,param);
        comparison.getOp2().accept(this,param);
        comparison.setLvalue(false);

        comparison.setType(comparison.getOp1().getType().compare(comparison.getOp2().getType(),
                comparison.getLine(), comparison.getColumn()));
        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {
        fieldAccess.getStruct().accept(this, param);
        fieldAccess.setLvalue(true);

        fieldAccess.setType(fieldAccess.getStruct().getType().dot(fieldAccess.getField(),
                fieldAccess.getLine(), fieldAccess.getColumn()));
        return null;
    }

    //TODO
    @Override
    public Void visit(FunctionInvocation invocation, Void param) {
        invocation.getParameters().forEach(p -> p.accept(this, param));
        invocation.getName().accept(this, param);
        invocation.setLvalue(false);

        List<Type> argTypes = invocation.getParameters()
                                        .stream()
                                        .map(exp -> exp.getType())
                                        .collect(Collectors.toList());

        invocation.setType( invocation.getName().getType().parenthesis(argTypes, invocation.getLine(), invocation.getColumn()) );
        //invocation.getName().getType().invoke(argTypes, invocation.getLine(), invocation.getColumn()); TODO
        return null;
    }

    @Override
    public Void visit(Indexing indexing, Void param) {
        indexing.getArray().accept(this, param);
        indexing.getIndex().accept(this, param);
        indexing.setLvalue(true);

        indexing.setType(indexing.getArray().getType().squareBrackets(indexing.getIndex().getType(),
                indexing.getLine(), indexing.getColumn()));
        return null;
    }

    @Override
    public Void visit(Logical logical, Void param) {
        logical.getOp1().accept(this, param);
        logical.getOp2().accept(this, param);
        logical.setLvalue(false);

        logical.setType(logical.getOp1().getType().logical(logical.getOp2().getType(),
                logical.getLine(), logical.getColumn()));
        return null;
    }

    @Override
    public Void visit(Modulus modulus, Void param) {
        modulus.getOp1().accept(this, param);
        modulus.getOp2().accept(this, param);
        modulus.setLvalue(false);

        modulus.setType(modulus.getOp1().getType().modulus(modulus.getOp2().getType(),
                modulus.getLine(), modulus.getColumn()));
        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {
        unaryMinus.getExpression().accept(this, param);
        unaryMinus.setLvalue(false);

        unaryMinus.setType(unaryMinus.getExpression().getType().toUnaryMinus(unaryMinus.getLine(), unaryMinus.getColumn()));
        return null;
    }

    @Override
    public Void visit(UnaryNot unaryNot, Void param) {
        unaryNot.getExpression().accept(this, param);
        unaryNot.setLvalue(false);

        unaryNot.setType(unaryNot.getExpression().getType().negation(unaryNot.getLine(), unaryNot.getColumn()));
        return null;
    }

    @Override
    public Void visit(Variable variable, Void param){
        variable.setLvalue(true);

        if (variable.getDefinition() == null)
            variable.setType(new ErrorType(variable.getLine(), variable.getColumn(),
                    String.format("Variable %s not defined.", variable.getName())));
        else
            variable.setType(variable.getDefinition().getType());
        return null;
    }

    @Override
    public Void visit(CharLiteral charLiteral, Void param) {
        charLiteral.setLvalue(false);
        charLiteral.setType(new CharType(charLiteral.getLine(), charLiteral.getColumn()));
        return null;
    }

    @Override
    public Void visit(DoubleLiteral doubleLiteral, Void param) {
        doubleLiteral.setLvalue(false);
        doubleLiteral.setType(new DoubleType(doubleLiteral.getLine(), doubleLiteral.getColumn()));
        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Void param) {
        intLiteral.setLvalue(false);
        intLiteral.setType(new IntType(intLiteral.getLine(), intLiteral.getColumn()));
        return null;
    }

    // ----------------------- STATEMENTS ------------------------------

    @Override
    public Void visit(Assignment assign, Void param){
        assign.getLeft().accept(this, param);
        assign.getRight().accept(this, param);
        if (!assign.getLeft().getLvalue())
            new ErrorType(assign.getLeft().getLine(),assign.getLeft().getColumn(),
                    "L-value required");

        assign.getLeft().getType().assign(assign.getRight().getType(), assign.getLine(), assign.getColumn());
        return null;
    }

    @Override
    public Void visit(IfElse ifElse, Void param) {
        ifElse.getCondition().accept(this, param);
        ifElse.getIfBody().forEach(s -> s.accept(this, param));
        ifElse.getElseBody().forEach(s -> s.accept(this, param));

        ifElse.getCondition().getType().asBoolean(ifElse.getCondition().getLine(), ifElse.getCondition().getColumn());

        ifElse.getIfBody().forEach(st -> st.setReturnType(ifElse.getReturnType()));
        ifElse.getElseBody().forEach(st -> st.setReturnType(ifElse.getReturnType()));
        return null;
    }

    @Override
    public Void visit(Read readSt, Void param) {
        readSt.getArgument().accept(this, param);
        if (!readSt.getArgument().getLvalue())
            new ErrorType(readSt.getArgument().getLine(),readSt.getArgument().getColumn(),
                    "L-value required");

        readSt.getArgument().getType().readable(readSt.getArgument().getLine(), readSt.getArgument().getColumn());
        return null;
    }

    @Override
    public Void visit(While whileSt, Void param) {
        whileSt.getCondition().accept(this, param);
        whileSt.getBody().forEach(s -> s.accept(this, null));

        whileSt.getCondition().getType().asBoolean(whileSt.getCondition().getLine(), whileSt.getCondition().getColumn());
        whileSt.getBody().forEach(st -> st.setReturnType(whileSt.getReturnType()));
        return null;
    }

    @Override
    public Void visit(Write writeSt, Void param) {
        writeSt.getArgument().accept(this, param);

        writeSt.getArgument().getType().writable(writeSt.getArgument().getLine(), writeSt.getArgument().getColumn());
        return null;
    }

    @Override
    public Void visit(FuncDefinition funcDefinition, Void param) {
        funcDefinition.getBody().forEach(st -> st.setReturnType(((FunctionType)funcDefinition.getType()).getReturnType()));//TODO

        funcDefinition.getType().accept(this, param);
        funcDefinition.getBody().forEach(b -> b.accept(this, param));
        return null;
    }

    @Override
    public Void visit(Return returnSt, Void param) {
        returnSt.getReturnExpression().accept(this, param);

        returnSt.getReturnType().returnable(returnSt.getReturnExpression().getType(),
                returnSt.getReturnExpression().getLine(), returnSt.getReturnExpression().getColumn());
        return null;
    }
}