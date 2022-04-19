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
 */

public class ValueCGVisitor extends AbstractCGVisitor {
}
