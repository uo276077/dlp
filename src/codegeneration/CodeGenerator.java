package codegeneration;

import ast.Expression;
import ast.Type;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.types.CharType;
import ast.types.DoubleType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CodeGenerator {
    private FileWriter outputFile;
    private int labels = 0;

    public String nextLabel(){
        return "label" + this.labels++;
    }

    public CodeGenerator(String fileName, String sourceName){
        try {
            this.outputFile = new FileWriter(fileName, true);
            writeLineNoTab(String.format("#source\t\"%s\"\n", sourceName));
        } catch (IOException e){
            //TODO
        }
    }

    private void writeLine(String line){
        try {
            outputFile.write("\t" + line + "\n");
        } catch (IOException e) {
            //TODO
        }
    }

    private void writeLineNoTab(String line) {
        try {
            outputFile.write("\n" + line + "\n");
        } catch (IOException e) {
            //TODO
        }
    }

    public void writeLineNumber(int line) {
        writeLineNoTab("#line\t" + line);
    }

    public void pushAddress(VarDefinition vardef) {
        if (vardef.getScope() > 0){
            writeLine("push bp");
            writeLine("pushi " + vardef.getOffset());
            writeLine("addi");
        } else
            writeLine("pusha " + vardef.getOffset());
    }

    public void pushByte(int value) {
        writeLine("pushb " + value);
    }

    public void pushFloat(double value) {
        writeLine("pushf " + value);
    }

    public void pushInt(int value) {
        writeLine("pushi " + value);
    }

    public void load(Variable variable) {
        writeLine("load" + variable.getType().suffix());
    }

    public void convert(Type from, Type to) {
        if (!from.equals(to)) {
            if (to instanceof CharType) convertToChar(from);
            else if (to instanceof DoubleType) convertToDouble(from);
            else writeLine(from.suffix() + "2" + to.suffix());
        }
    }

    private void convertToDouble(Type from) {
        if (from instanceof CharType)
            writeLine("b2i");
        writeLine("i2f");
    }

    private void convertToChar(Type from) {
        if (from instanceof DoubleType)
            writeLine("f2i");
        writeLine("i2b");
    }

    public void arithmetic(String operator, Type type) {
        String op = "";
        switch (operator){
            case "+":
                op = "add"; //TODO chars?
                break;
            case "-":
                op = "sub";
                break;
            case "*":
                op = "mul";
                break;
            case "/":
                op = "div";
                break;
            default:
                //TODO
                break;
        }
        String suffix = type.suffix();
        if (suffix.equals("b"))
            suffix = "i";
        writeLine(op + suffix);
    }

    public void charToInt(Type type) {
        if (type instanceof CharType)
            writeLine("b2i");
    }

    public void compare(String operand, Type type) {
        String compare = "";
        switch (operand){
            case ">":
                compare = "gt";
                break;
            case ">=":
                compare = "ge";
                break;
            case "<":
                compare = "lt";
                break;
            case "<=":
			    compare = "le";
                break;
            case "!=":
                compare = "ne";
                break;
            case "==":
                compare = "eq";
                break;
            default:
                //TODO
        }
        String suffix = type.suffix();
        if (suffix.equals("b"))
            suffix = "i";
        writeLine(compare + suffix);
    }

    public void logical(String operand) {
        switch (operand){
            case "&&":
			    writeLine("and");
                break;
            case "||":
			    writeLine("or");
                break;
            default:
                //TODO
        }
    }

    public void modulus() {
        writeLine("modi");
    }

    public void negate() {
        writeLine("not");
    }

    public void read(Type type) {
        writeLine("in" + type.suffix());
        writeLine("store" + type.suffix());
    }

    public void write(Type type) {
        writeLine("out" + type.suffix());
    }

    public void store(Type type) {
        writeLine("store" + type.suffix());
    }

    public void commentVariable(VarDefinition varDefinition) {
        writeLine(String.format("' * %s %s (offset %d)",
                varDefinition.getType(), varDefinition.getName(), varDefinition.getOffset()));
    }

    public void newFunction(FuncDefinition funcDefinition) {
        writeLineNumber(funcDefinition.getLine());
        writeLineNoTab(String.format(" %s :", funcDefinition.getName()));
    }

    public void generateComment(String comment) {
        writeLine(String.format("' * %s", comment));
    }

    public void allocateMemory(int memory) {
        writeLine(String.format("enter %d", memory));
    }

    public void writeToFile() {
        try {
            outputFile.flush();
            outputFile.close();
        } catch (IOException e){}
    }

    public void ret(int returnBytes, int localVariablesBytes, int argumentsBytes) {
        writeLine(String.format("ret %d %d %d", returnBytes, localVariablesBytes, argumentsBytes));
    }

    public void finishProgram() {
        writeLineNoTab("call main");
        writeLineNoTab("halt");
    }

    public void generateLabel(String conditionLabel) {
        writeLineNoTab(" " + conditionLabel + ":");
    }

    public void jumpConditionally(String exitLabel, boolean zero) {
        if (zero)
            writeLine("jz " + exitLabel);
        else
            writeLine("jnz " + exitLabel);
    }

    public void jump(String conditionLabel) {
        writeLine("jmp " + conditionLabel);
    }
}
