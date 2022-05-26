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
            this.outputFile = new FileWriter(fileName);
            writeLineNoTab(String.format("#source\t\"%s\"\n", sourceName));
        } catch (IOException e){

        }
    }

    private void writeLine(String line){
        try {
            outputFile.write("\t" + line + "\n");
        } catch (IOException e) {

        }
    }

    private void writeLineNoTab(String line) {
        try {
            outputFile.write( line + "\n");
        } catch (IOException e) {

        }
    }

    public void writeLineNumber(int line) {
        writeLineNoTab("\n#line\t" + line);
    }

    public void pushAddress(VarDefinition vardef) {
        if (vardef.getScope() > 0){
            writeLine("push\tbp");
            pushInt(vardef.getOffset());
            writeLine("addi");
        } else
            writeLine("pusha\t" + vardef.getOffset());
    }

    public void pushByte(int value) {
        writeLine("pushb\t" + value);
    }

    public void pushFloat(double value) {
        writeLine("pushf\t" + value);
    }

    public void pushInt(int value) {
        writeLine("pushi\t" + value);
    }

    public void push(String suffix, int value) {
        writeLine(String.format("push%s\t%d", suffix, value));
    }

    public void load(Type type) {
        writeLine("load" + type.suffix());
    }

    public void convert(Type from, Type to) {
        String convert = from.convertTo(to);
        if (convert != null)
            writeLine(convert);
    }

    public void arithmetic(String operator, Type type) {
        String op = "";
        switch (operator){
            case "+":
                op = "add";
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
                return;
        }
        String suffix = type.suffix();
        writeLine(op + suffix);
    }

    public void subtract(Type type) {
        arithmetic("-", type);
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
                return;
        }
        String suffix = type.suffix();
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
                return;
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
        writeLineNoTab(String.format("\n %s :", funcDefinition.getName()));
    }

    public void generateComment(String comment) {
        writeLine(String.format("' * %s", comment));
    }

    public void allocateMemory(int memory) {
        writeLine(String.format("enter\t%d", memory));
    }

    public void writeToFile() {
        try {
            outputFile.flush();
            outputFile.close();
        } catch (IOException e){}
    }

    public void ret(int returnBytes, int localVariablesBytes, int argumentsBytes) {
        writeLine(String.format("ret\t\t%d, %d, %d", returnBytes, localVariablesBytes, argumentsBytes));
    }

    public void finishProgram() {
        writeLineNoTab("\n' Invocation to the main function");
        writeLineNoTab("call main");
        writeLineNoTab("halt");
    }

    public void generateLabel(String conditionLabel) {
        writeLineNoTab(" " + conditionLabel + ":");
    }

    public void jumpConditionally(String exitLabel, boolean zero) {
        if (zero)
            writeLine("jz\t\t" + exitLabel);
        else
            writeLine("jnz\t\t" + exitLabel);
    }

    public void jump(String conditionLabel) {
        writeLine("jmp\t\t" + conditionLabel);
    }

    public void addIntegers() {
        writeLine("addi");
    }

    public void multiplyIntegers() {
        writeLine("muli");
    }

    public void callFunction(String name) {
        writeLine("call\t" + name);
    }

    public void pop(String suffix) {
        writeLine("pop" + suffix);
    }
}
