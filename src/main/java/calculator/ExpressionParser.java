
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

  public Expression parse(String s) {

    CharStream input = CharStreams.fromString(s);

    calculatorLexer lexer = new calculatorLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    calculatorParser parser = new calculatorParser(tokens);
    ParseTree tree = parser.equation();

    ParserVisitor visitor = new ParserVisitor();

    return visitor.visit(tree);

  }
}
