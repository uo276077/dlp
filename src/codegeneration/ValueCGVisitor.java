package codegeneration;

/*
value[[ IntLiteral: expression -> INT_CONSTANT ]] =
		<pushi> INT_CONSTANT

value[[ CharLiteral: expression -> CHAR_CONSTANT ]] =
		<pushb> CHAR_CONSTANT


value[[ DoubleLiteral: expression -> REAL_CONSTANT ]] =
		<pushf> REAL_CONSTANT

value[[ TrueLiteral: expression -> TRUE_CONSTANT ]] =
        <pushi> 1

value[[ FalseLiteral: expression -> FALSE_CONSTANT ]] =
        <pushi> 0

value[[ Variable: expression -> ID ]] =
		address[[ expression ]]
		<load> expression.type.suffix()

value[[ Arithmetic: expression1 -> expression2 expression3 ]] =
        Type superType = expression2.type.superType(expression3.type);
		value[[expression2]]
		expression2.type.convertTo(superType)

		value[[expression3]]
		expression3.type.convertTo(superType)

		switch (expression1.operator) {
		case "+":
			<add> superType.suffix()
			break;
		case "-":
			<sub> superType.suffix()
			break;
		case "*":
			<mul> superType.suffix()
			break;
		case "/":
			<div> superType.suffix()
			break;
		default:
			//error
		}

value[[ Comparison: expression1 -> expression2 expression3 ]] =
        Type superType = expression2.type.superType(expression3.type);
		value[[expression2]] //type checking has checked that they are the same type(?)
		expression2.type.convertTo(superType)
		value[[expression3]]
		expression3.type.convertTo(superType)
		// (superType: new responsibility, take two types and return the "greater" one)
		switch (expression1.operator) {
		case ">":
			<gt> superType.suffix()
			break;
		case ">=":
			<ge> superType.suffix()
			break;
		case "<":
			<lt> superType.suffix()
			break;
		case "<=":
			<le> superType.suffix()
			break;
		case "!=":
			<ne> superType.suffix()
			break;
		case "==":
			<eq> superType.suffix()
			break;
		default:
			//error
		}

value[[ Logical: expression1 -> expression2 expression3 ]] =
		value[[expression2]]
		value[[expression3]]

		switch (expression1.operator) {
		case "&&":
			<and>
			break;
		case "||":
			<or>
			break;
		default:
			//error
		}

value[[ Cast: expression1 -> type expression2 ]] =
		value[[expression2]]
		expression2.type.convertTo(type)

value[[ UnaryNot: expression1 -> expression2 ]] =
        value[[expression2]]
        <not>

value[[ UnaryMinus: expression1 -> expression2 ]] =
        <push> expression1.type.suffix() < 0>
        value[[expression2]]
        <sub> expression1.type.suffix()

value[[ Modulus: expression1 -> expression2 expression3 ]] =
        value[[expression2]]
        value[[expression3]]
        <mod>

value[[Indexing: expression1 -> expression2 expression3]] =
		address[[expression1]]
		<load> expression1.type.suffix()

value[[FieldAccess: expression1 -> expression2 ID]] =
		address[[expression1]]
		<load> expression1.type.suffix()

//Invocation as an expression
value[[FunctionInvocation: expression1 -> expression2 expression3*]] =
				expression3*.forEach(arg -> value[[arg]])
				<call > expression2.name
*/

import ast.Type;
import ast.expressions.*;
import ast.expressions.literals.*;
import semantic.Visitor;

public class ValueCGVisitor extends AbstractCGVisitor<Void, Void> {

    private Visitor<Void,Void> address;

    public ValueCGVisitor(CodeGenerator cg, Visitor<Void,Void> address) {
        super(cg);
        this.address = address;
    }

    @Override
    public Void visit(CharLiteral charLiteral, Void param) {

        cg.pushByte((int) charLiteral.getValue());

        return null;
    }

    @Override
    public Void visit(DoubleLiteral doubleLiteral, Void param) {

        cg.pushFloat(doubleLiteral.getValue());

        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Void param) {

        cg.pushInt(intLiteral.getValue());

        return null;
    }

    @Override
    public Void visit(TrueLiteral trueLiteral, Void param) {

        cg.pushInt(1);

        return null;
    }

    @Override
    public Void visit(FalseLiteral falseLiteral, Void param) {

        cg.pushInt(0);

        return null;
    }

    @Override
    public Void visit(Variable variable, Void param) {

        variable.accept(address, null);
        cg.load(variable.getType());

        return null;
    }


    @Override
    public Void visit(Arithmetic arith, Void param) {

        // (superType: new responsibility, take two types and return the "greater" one)
        Type superType = arith.getType().superType(arith.getLeft().getType());

        arith.getLeft().accept(this, null);
        cg.convert(arith.getLeft().getType(), superType);

        arith.getRight().accept(this, null);
        cg.convert(arith.getRight().getType(), superType);

        cg.arithmetic(arith.getOperator(), superType);

        return null;
    }

    @Override
    public Void visit(Comparison comparison, Void param) {

        //
        Type superType = comparison.getOp1().getType().superType(comparison.getOp2().getType());

        comparison.getOp1().accept(this, null);
        cg.convert(comparison.getOp1().getType(), superType);

        comparison.getOp2().accept(this, null);
        cg.convert(comparison.getOp2().getType(), superType);

        cg.compare(comparison.getOperand(), superType);

        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {

        cast.getExpression().accept(this, null);
        cg.convert(cast.getExpression().getType(), cast.getCastType());

        return null;
    }

    @Override
    public Void visit(Logical logical, Void param) {

        logical.getOp1().accept(this, null);
        logical.getOp2().accept(this, null);

        cg.logical(logical.getOperand());

        return null;
    }

    @Override
    public Void visit(Modulus modulus, Void param) {

        modulus.getOp1().accept(this, null);
        modulus.getOp2().accept(this, null);

        cg.modulus();

        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {

        cg.push(unaryMinus.getType().suffix(), 0);
        unaryMinus.getExpression().accept(this, null);
        cg.subtract(unaryMinus.getType());

        return null;
    }

    @Override
    public Void visit(UnaryNot unaryNot, Void param) {

        unaryNot.getExpression().accept(this, null);
        cg.negate();

        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {

        fieldAccess.accept(address, null);
        cg.load(fieldAccess.getType());

        return null;
    }

    @Override
    public Void visit(Indexing indexing, Void param) {

        indexing.accept(address, null);
        cg.load(indexing.getType());

        return null;
    }

    @Override
    public Void visit(FunctionInvocation invocation, Void param) {

        invocation.getParameters().forEach(exp -> exp.accept(this, null));

        cg.callFunction(invocation.getName().getName());

        return null;
    }
}
