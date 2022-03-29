package semantic;

import ast.expressions.*;
import ast.expressions.literals.CharLiteral;
import ast.expressions.literals.DoubleLiteral;
import ast.expressions.literals.IntLiteral;
import ast.statements.*;
import ast.types.*;

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
	Return: statement -> expression
R:
	statement.returnType.returnable(expression);

     */
public class TypeCheckingVisitor extends AbstractVisitor<Void, Void> {



    @Override
    public Void visit(Variable variable, Void param){
        variable.setLvalue(true);
        return null;
    }

    @Override
    public Void visit(Assignment assign, Void param){
        assign.getLeft().accept(this, param);
        assign.getRight().accept(this, param);
        if (!assign.getLeft().getLvalue())
            new ErrorType(assign.getLeft().getLine(),assign.getLeft().getColumn(),
                    "L-value required");
        return null;
    }

    @Override
    public Void visit(Arithmetic arith, Void param) {
        arith.getLeft().accept(this, param);
        arith.getRight().accept(this, param);
        arith.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {
        cast.getExpression().accept(this,param);
        cast.getCastType().accept(this, param);
        cast.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(Comparison comparison, Void param) {
        comparison.getLeft().accept(this,param);
        comparison.getRight().accept(this,param);
        comparison.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {
        fieldAccess.getStruct().accept(this, param);
        fieldAccess.setLvalue(true);
        return null;
    }

    @Override
    public Void visit(FunctionInvocation invocation, Void param) {
        invocation.getParameters().forEach(p -> p.accept(this, param));
        invocation.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(Indexing indexing, Void param) {
        indexing.getArray().accept(this, param);
        indexing.getIndex().accept(this, param);
        indexing.setLvalue(true);
        return null;
    }

    @Override
    public Void visit(Logical logical, Void param) {
        logical.getLeft().accept(this, param);
        logical.getRight().accept(this, param);
        logical.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(Modulus modulus, Void param) {
        modulus.getLeft().accept(this, param);
        modulus.getRight().accept(this, param);
        modulus.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {
        unaryMinus.getExpression().accept(this, param);
        unaryMinus.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(UnaryNot unaryNot, Void param) {
        unaryNot.getExpression().accept(this, param);
        unaryNot.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(CharLiteral charLiteral, Void param) {
        charLiteral.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(DoubleLiteral doubleLiteral, Void param) {
        doubleLiteral.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Void param) {
        intLiteral.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(Read readSt, Void param) {
        readSt.getArgument().accept(this, param);
        if (!readSt.getArgument().getLvalue())
            new ErrorType(readSt.getArgument().getLine(),readSt.getArgument().getColumn(),
                    "L-value required");
        return null;
    }

}
