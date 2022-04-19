package codegeneration;

/*
execute[[ Program ]]

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

execute[[ VarDefinition: definition -> type ID ]] =
		if (definition.scope == 0)//global

execute[[ FuncDefinition ]]
 */

public class ExecuteCGVisitor extends AbstractCGVisitor {
}
