import ast.Program;
import ast.errorhandler.ErrorHandler;
import codegeneration.*;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
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
        if (args.length<1) {
            System.err.println("Please, pass me the input file.");
            return;
        }

        // create a lexer that feeds off of input CharStream
        CharStream input = CharStreams.fromFileName(args[0]);
        CmmLexer lexer = new CmmLexer(input);

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
//            // * The AST is shown
//            IntrospectorModel model=new IntrospectorModel(
//                    "Program", ast);
//            new IntrospectorTree("Introspector", model);
            CodeGenerator cg = new CodeGenerator("output.txt", args[0]);
            AddressCGVisitor addressCGVisitor = new AddressCGVisitor(cg);
            visitor = new ExecuteCGVisitor(cg, addressCGVisitor, new ValueCGVisitor(cg, addressCGVisitor));
            visitor.visit(ast, null);
            cg.writeToFile();
        }
    }

}
