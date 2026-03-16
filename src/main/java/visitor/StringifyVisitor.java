package visitor;

import calculator.MyNumber;
import calculator.Notation;
import calculator.Operation;
import calculator.numbers.RealNumber;
import calculator.numbers.IntegerNumber;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * StringifyVisitor renders arithmetic expressions in prefix, infix or postfix
 * notation.
 * 
 * @author Oussama Hakik
 */

public class StringifyVisitor extends Visitor {

  private final Notation notation; // the notation to be used for rendering the expression
  private final Deque<String> renderedExpressions = new ArrayDeque<>(); // stack to keep track of the rendered
                                                                        // sub-expressions

  /**
   * Class constructor specifying the notation to be used for rendering the
   * expression.
   *
   * @param notation The Notation to be used for rendering the expression
   */
  public StringifyVisitor(Notation notation) {
    this.notation = notation == null ? Notation.INFIX : notation;
  }

  /**
   * The Visitor can traverse a number (a subtype of Expression
   * 
   * @param n The number being visited
   */
  @Override
  public void visit(MyNumber n) {
    renderedExpressions.push(Integer.toString(n.getValue()));
  }

  @Override
  public void visit(IntegerNumber i) {
    renderedExpressions.push(i.toString());

  }

  @Override
  public void visit(RealNumber r) {
    renderedExpressions.push(r.toString());

  }

  /**
   * The Visitor can traverse an operation (a subtype of Expression)
   * 
   * @param o The operation being visited
   */
  @Override
  public void visit(Operation o) {
    List<String> args = new ArrayList<>();
    for (int i = 0; i < o.getArgs().size(); i++) {
      args.add(renderedExpressions.pop());
    }
    Collections.reverse(args); // reverse the order of the arguments to match the original order in the
                               // expression

    if (args.isEmpty()) {
      String emptyRendered = switch (notation) {
        case INFIX -> "( )";
        case PREFIX -> o.getSymbol() + " ()";
        case POSTFIX -> "() " + o.getSymbol();
      };
      renderedExpressions.push(emptyRendered);
      return;
    }

    String joined = String.join(", ", args); // join the arguments with a comma and a space for rendering in prefix and
                                             // postfix notation
    String symbol = o.getSymbol(); // get the symbol of the operation for rendering in infix notation
    String rendered = switch (notation) { // render the expression according to the specified notation
      case INFIX -> "( " + String.join(" " + symbol + " ", args) + " )";
      case PREFIX -> symbol + " (" + joined + ")";
      case POSTFIX -> "(" + joined + ") " + symbol;
    };
    renderedExpressions.push(rendered); // push the rendered expression back on the stack for further processing by the
                                        // parent operation
  }

  /**
   * Get the final rendered expression after visiting the entire expression tree
   * 
   * @return The rendered expression as a String
   */
  public String getResult() {
    return renderedExpressions.isEmpty() ? "" : renderedExpressions.peek();
  }
}
