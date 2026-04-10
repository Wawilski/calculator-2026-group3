
package calculator;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import visitor.ParserVisitor;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

/**
 * Parser
 */
public class ExpressionParser {
  /**
   * Defines if the angle unit is considered in degrees (true) or in radiant
   * (false)
   */
  public static boolean isAngleUnitDegree = false;

  public Expression parse(String s) {

    CharStream input = CharStreams.fromString(s);

    SyntaxErrorDetector listener = new SyntaxErrorDetector();
    calculatorLexer lexer = new calculatorLexer(input);

    lexer.removeErrorListeners();
    lexer.addErrorListener(listener);

    CommonTokenStream tokens = new CommonTokenStream(lexer);

    calculatorParser parser = new calculatorParser(tokens);

    parser.removeErrorListeners();
    parser.addErrorListener(listener);

    ParseTree tree = parser.equation();

    ParserVisitor visitor = new ParserVisitor();
    Expression e;

    if (listener.getErrors() == 0) {
      e = visitor.visit(tree);
    } else {
      throw new InvalidTokenException("Invalid mathematical expression");
    }

    return e;

  }
}
