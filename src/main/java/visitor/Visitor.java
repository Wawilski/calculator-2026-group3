package visitor;

import calculator.MyNumber;
import calculator.Operation;
import calculator.numbers.RealNumber;
import calculator.numbers.IntegerNumber;

/**
 * Visitor design pattern
 */
public abstract class Visitor {

  /**
   * The Visitor can traverse a number (a subtype of Expression)
   *
   * @param n The number being visited
   */
  public abstract void visit(MyNumber n);

  /**
   * The Visitor can traverse a real
   *
   * @param r The real being visited
   */
  public abstract void visit(RealNumber r);

  /**
   * The Visitor can traverse an integer
   *
   * @param n The integer being visited
   */
  public abstract void visit(IntegerNumber r);

  /**
   * The Visitor can traverse an operation (a subtype of Expression)
   *
   * @param o The operation being visited
   */
  public abstract void visit(Operation o);
}
