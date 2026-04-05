package visitor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import calculator.Function;
import calculator.Notation;
import calculator.Operation;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;

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

  @Override
  public void visit(IntegerNumber i) {
    renderedExpressions.push(i.toString());

  }

  @Override
  public void visit(RationalNumber r) {
    String rendered = switch (notation) {
      case INFIX -> "( " + r.getNumerator() + " / " + r.getDenominator() + " )";
      case PREFIX -> "/ ( " + Integer.toString(r.getNumerator()) + ", " + Integer.toString(r.getDenominator()) + " )";
      case POSTFIX -> "( " + Integer.toString(r.getNumerator()) + ", " + Integer.toString(r.getDenominator()) + " ) /";
    };

    renderedExpressions.push(rendered);
  }

  @Override
  public void visit(RealNumber r) {
    renderedExpressions.push(r.toString());
  }

  @Override
  public void visit(ComplexNumber c) {
    if (c.isNaN()) {
      renderedExpressions.push(c.toString());
    } else {
      String rendered = switch (notation) {
        case INFIX -> "( " + c.getReal().toString() + " + " + c.getImaginary().toString() + "i" + " )";
        case PREFIX -> "+ ( " + c.getReal().toString() + ", * ( " + c.getImaginary().toString() + " , i ) )";
        case POSTFIX -> "( " + c.getReal().toString() + ", ( " + c.getImaginary().toString() + " , i ) * ) +";
      };
      renderedExpressions.push(rendered);
    }
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

  @Override
  public void visit(Function f) {
    List<String> args = new ArrayList<>();
    for (int i = 0; i < f.getArgs().size(); i++) {
      args.add(renderedExpressions.pop());
    }
    Collections.reverse(args);

    if (args.isEmpty()) {
      renderedExpressions.push(f.getSymbol() + "()");
      return;
    }

    String joined = String.join(", ", args);
    String rendered = switch (notation) {
      case INFIX, PREFIX -> f.getSymbol() + "(" + joined + ")";
      case POSTFIX -> "(" + joined + ") " + f.getSymbol();
    };
    renderedExpressions.push(rendered);
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
