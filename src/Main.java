import ast.Program;
import ast.errorhandler.ErrorHandler;
import codegeneration.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.CmmLexer;
import parser.CmmParser;
import semantic.IdentificationVisitor;
import semantic.TypeCheckingVisitor;
import semantic.Visitor;

import java.io.IOException;

public class Main {

    public static void main(String... args) throws IOException {
        if (args.length<2) {
            System.err.println("Please, pass me both the input and output file.");
            return;
        }

        String in = args[0];
        String out = args[1];

        // create a lexer that feeds off of input CharStream
        CharStream input = CharStreams.fromFileName(in);
        CmmLexer lexer = new CmmLexer(input);

        in = in.split("/")[2];

        // create a parser that feeds off the tokens buffer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CmmParser parser = new CmmParser(tokens);

        Program ast = parser.program().ast;

        Visitor visitor = new IdentificationVisitor();
        visitor.visit(ast, null);

        visitor = new TypeCheckingVisitor();
        visitor.visit(ast, null);

        visitor = new OffsetVisitor();
        visitor.visit(ast, null);

        if (ErrorHandler.getInstance().anyErrors())
            ErrorHandler.getInstance().showErrors(System.err);
        else {
            CodeGenerator cg = new CodeGenerator(out, in);
            AddressCGVisitor addressCGVisitor = new AddressCGVisitor(cg);
            ValueCGVisitor valueCGVisitor = new ValueCGVisitor(cg, addressCGVisitor);
            addressCGVisitor.setValueVisitor(valueCGVisitor);
            visitor = new ExecuteCGVisitor(cg, addressCGVisitor, valueCGVisitor);
            visitor.visit(ast, null);

        }
    }

}
