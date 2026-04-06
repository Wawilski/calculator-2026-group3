package calculator.functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import calculator.Expression;
import calculator.Operation;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.numbers.BaseNumber;
import visitor.StringifyVisitor;
import visitor.Visitor;

/**
 * Function is an abstract class that represents mathematical functions,
 * which are a special kind of Expressions, just like operations and numbers
 * are.
 *
 * @see Expression
 * @see Operation
 * @see BaseNumber
 */
public abstract class Function implements Expression {
  /**
   * The list of expressions passed as arguments to the function.
   */
  private final List<Expression> args;

  /**
   * The symbol used to represent the function (e.g. "sin", "sqrt", "^").
   */
  protected String symbol;

  /**
   * Neutral value used when evaluating an empty argument list.
   */
  protected int neutral;

  /**
   * Number of arguments expected by the function (1 for unary, 2 for binary).
   */
  private final int arity;

  /**
   * Construct a function from a list of arguments.
   *
   * @param elist list of arguments
   * @throws IllegalConstruction if the argument list is null
   */
  protected Function(List<Expression> elist, int expectedArity) throws IllegalConstruction {
    if (elist == null) {
      throw new IllegalConstruction();
    }
    if (expectedArity < 1) {
      throw new IllegalConstruction();
    }
    if (!elist.isEmpty() && elist.size() != expectedArity) {
      throw new IllegalConstruction();
    }
    args = new ArrayList<>(elist);
    arity = expectedArity;
  }

  /**
   * @return read-only view of arguments
   */
  public List<Expression> getArgs() {
    return Collections.unmodifiableList(args);
  }

  /**
   * @return function symbol
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * @return neutral value
   */
  public int getNeutral() {
    return neutral;
  }

  /**
   * @return expected arity of the function
   */
  public int getArity() {
    return arity;
  }

  @Override
  public void accept(Visitor v) {
    for (Expression a : args) {
      a.accept(v);
    }
    v.visit(this);
  }

  @Override
  public final String toString() {
    StringifyVisitor visitor = new StringifyVisitor(Notation.INFIX);
    accept(visitor);
    return visitor.getResult();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (this == o) {
      return true;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    Function other = (Function) o;
    return this.args.equals(other.getArgs());
  }

  @Override
  public int hashCode() {
    int result = 5;
    int prime = 31;
    result = prime * result + neutral;
    result = prime * result + arity;
    result = prime * result + symbol.hashCode();
    result = prime * result + args.hashCode();
    return result;
  }
}
