package visitor;

import calculator.MyNumber;
import calculator.Operation;
import calculator.numbers.RealNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * PrettyPrintVisitor renders expressions in infix form with minimal parentheses
 * 
 * @author Oussama Hakik
 */
public class PrettyPrintVisitor extends Visitor {

  /**
   * RenderedNode is a helper class to keep track of the rendered text of a
   * sub-expression,a
   */
  private static final class RenderedNode {
    private final String text; // the rendered text of the sub-expression
    private final int precedence; // the precedence of the top-level operator in the sub-expression (0 for
                                  // numbers, 1 for + and -, 2 for * and /)
    private final boolean operation; // whether the sub-expression is an operation (true) or a number (false)

    private RenderedNode(String text, int precedence, boolean operation) {
      this.text = text;
      this.precedence = precedence;
      this.operation = operation;
    }
  }

  private final Deque<RenderedNode> renderedExpressions = new ArrayDeque<>();// stack to keep track of the rendered
                                                                             // sub-expressions

  @Override
  public void visit(MyNumber n) {
    renderedExpressions.push(new RenderedNode(Integer.toString(n.getValue()), 3, false));
  }

  @Override
  public void visit(IntegerNumber i) {
    renderedExpressions.push(new RenderedNode(i.toString(), 3, false));

  }

  @Override
  public void visit(RealNumber r) {
    renderedExpressions.push(new RenderedNode(r.toString(), 3, false));

  }

  @Override
  public void visit(RationalNumber r) {
    renderedExpressions.push(new RenderedNode(r.toString(), 3, false));
  }

  @Override
  public void visit(ComplexNumber c) {
    renderedExpressions.push(new RenderedNode(c.toString(), 3, false));
  }

  @Override
  public void visit(Operation o) {
    List<RenderedNode> args = new ArrayList<>();
    for (int i = 0; i < o.getArgs().size(); i++) {
      args.add(renderedExpressions.pop());
    }
    Collections.reverse(args);

    String symbol = o.getSymbol(); // get the symbol of the operation (e.g. "+", "*")
    int precedence = precedenceOf(symbol); // get the precedence of the operation (e.g. 1 for + and -, 2 for * and /)
    String text = renderOperation(symbol, precedence, args);// render the operation with the appropriate parentheses
                                                            // based on the precedence of the sub-expressions
    renderedExpressions.push(new RenderedNode(text, precedence, true));
  }

  public String getResult() {
    return renderedExpressions.isEmpty() ? "" : renderedExpressions.peek().text;
  }

  /**
   * Get the precedence of an operator based on its symbol
   * 
   * @param symbol The symbol of the operator (e.g. "+", "*")
   * @return The precedence of the operator (e.g. 1 for + and -, 2 for * and /, 0
   *         for unknown operators)
   */
  private static int precedenceOf(String symbol) {
    return switch (symbol) {
      case "+", "-" -> 1;
      case "*", "/" -> 2;
      default -> 0;
    };
  }

  /**
   * Render an operation with the appropriate parentheses based on the precedence
   * of its arguments
   * 
   * @param symbol     The symbol of the operator (e.g. "+", "*")
   * @param precedence The precedence of the operator (e.g. 1 for + and -, 2 for *
   *                   and /)
   * @param args       The list of rendered arguments of the operation
   * @return The rendered text of the operation with minimal parentheses
   */

  private static String renderOperation(String symbol, int precedence, List<RenderedNode> args) {
    if (args.isEmpty()) {
      return symbol + "()";
    }
    if (args.size() == 1) {
      return maybeParenthesize(symbol, precedence, args.get(0), 0);
    }

    List<String> renderedArgs = new ArrayList<>();
    for (int i = 0; i < args.size(); i++) {
      renderedArgs.add(maybeParenthesize(symbol, precedence, args.get(i), i));
    }
    return String.join(" " + symbol + " ", renderedArgs);
  }

  /**
   * Determine whether to parenthesize a child expression based on its precedence
   * relative to the parent operator
   * 
   * @param parentSymbol     The symbol of the parent operator (e.g. "+", "*")
   * @param parentPrecedence The precedence of the parent operator (e.g. 1 for +
   *                         and -, 2 for * and /)
   * @param child            The rendered node of the child expression
   * @param argIndex         The index of the child argument in the parent's
   *                         argument list (0-based)
   * @return The rendered text of the child expression, with parentheses if
   *         necessary
   */

  private static String maybeParenthesize(String parentSymbol, int parentPrecedence, RenderedNode child, int argIndex) {
    String text = child.text;
    if (!child.operation) {
      return text;
    }
    if (child.precedence < parentPrecedence) {
      return "(" + text + ")";
    }
    if (child.precedence > parentPrecedence) {
      return text;
    }
    if (parentSymbol.equals("-") || parentSymbol.equals("/")) {
      return argIndex == 0 ? text : "(" + text + ")";
    }
    return text;
  }
}
