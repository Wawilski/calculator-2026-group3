import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Main
 */
public class Main {

  public static void main(String[] args) {
    String input = "(1+2)";
    calculatorLexer lexer = new calculatorLexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    calculatorParser parser = new calculatorParser(tokens);

    ParseTreeWalker walker = new ParseTreeWalker();
    ExpressionListener expressionListener = new ExpressionListener();

    ParseTree tree = parser.equation();
    walker.walk(expressionListener, tree);
    System.out.println(tree.toStringTree(parser));
  }
}
