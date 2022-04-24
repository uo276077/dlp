package codegeneration;

/*
value[[ IntLiteral: expression -> INT_CONSTANT ]] =
		< pushi > INT_CONSTANT

value[[ CharLiteral: expression -> CHAR_CONSTANT ]] =
		< pushb > CHAR_CONSTANT


value[[ DoubleLiteral: expression -> REAL_CONSTANT ]] =
		< pushf > REAL_CONSTANT


value[[ Variable: expression -> ID ]] =
		address[[ expression ]]
		< load > expression.type.suffix()

value[[ Arithmetic: expression1 -> expression2 expression3 ]] =
		value[[expression2]]
		expression2.type.convertTo(expression1.type)

		value[[expression3]]
		expression3.type.convertTo(expression1.type)

		switch (expression1.operator) {
		case "+":
			< add > expression1.type.suffix()
			break;
		case "-":
			< sub > expression1.type.suffix()
			break;
		case "*":
			< mul > expression1.type.suffix()
			break;
		case "/":
			< div > expression1.type.suffix()
			break;
		default:
			//error
		}

value[[ Comparison: expression1 -> expression2 expression3 ]] =
		value[[expression2]] //type checking has checked that they are the same type(?)
		expression2.type.charToInt()
		value[[expression3]]
		expression3.type.charToInt()
		switch (expression1.operator) {
		case ">":
			< gt > expression2.type.suffix()
			break;
		case ">=":
			< ge > expression2.type.suffix()
			break;
		case "<":
			< lt > expression2.type.suffix()
			break;
		case "<=":
			< le > expression2.type.suffix()
			break;
		case "!=":
			< ne > expression2.type.suffix()
			break;
		case "==":
			< eq > expression2.type.suffix()
			break;
		default:
			//error
		}

value[[ Logical: expression1 -> expression2 expression3 ]] =
		value[[expression2]]
		value[[expression3]]

		switch (expression1.operator) {
		case "&&":
			< and >
			break;
		case "||":
			< or >
			break;
		default:
			//error
		}

value[[ Cast: expression1 -> type expression2 ]] =
		value[[expression2]]
		expression2.type.convertTo(type)

value[[ UnaryNot: expression1 -> expression2 ]] =
        value[[expression2]]
        < not >

value[[ UnaryMinus: expression1 -> expression2 ]] =
        < push >
        expression1.type.suffix() < 0 >
        value[[expression2]]
        < sub > expression1.type.suffix()

value[[ Modulus: expression1 -> expression2 expression3 ]] =
        value[[expression2]]
        value[[expression3]]
        < mod >

*/

import ast.expressions.*;
import ast.expressions.literals.CharLiteral;
import ast.expressions.literals.DoubleLiteral;
import ast.expressions.literals.IntLiteral;
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
    public Void visit(Variable variable, Void param) {

        variable.accept(address, null);
        cg.load(variable);

        return null;
    }


    @Override
    public Void visit(Arithmetic arith, Void param) {

        arith.getLeft().accept(this, null);
        cg.convert(arith.getLeft().getType(), arith.getType());

        arith.getRight().accept(this, null);
        cg.convert(arith.getRight().getType(), arith.getType());

        cg.arithmetic(arith.getOperator(), arith.getType());

        return null;
    }

    @Override
    public Void visit(Comparison comparison, Void param) {

        comparison.getOp1().accept(this, null);
        cg.charToInt(comparison.getOp1().getType());

        comparison.getOp2().accept(this, null);
        cg.charToInt(comparison.getOp2().getType());

        cg.compare(comparison.getOperand(), comparison.getType());

        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {

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
        return null;
    }

    @Override
    public Void visit(UnaryNot unaryNot, Void param) {

        unaryNot.getExpression().accept(this, null);
        cg.negate();

        return null;
    }
}
