package visitor;

import calculator.Operation;
import calculator.numbers.RealNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;

/**
 * Visitor design pattern
 */
public abstract class Visitor {

  /**
   * The Visitor can traverse an integer
   *
   * @param i The integer being visited
   */
  public abstract void visit(IntegerNumber i);

  /**
   * The Visitor can traverse a rational
   *
   * @param r The rational being visited
   */
  public abstract void visit(RationalNumber r);

  /**
   * The Visitor can traverse a real
   *
   * @param r The real being visited
   */
  public abstract void visit(RealNumber r);

  /**
   * The Visitor can traverse an complex number
   *
   * @param c The complex number being visited
   */
  public abstract void visit(ComplexNumber c);

  /**
   * The Visitor can traverse an operation (a subtype of Expression)
   *
   * @param o The operation being visited
   */
  public abstract void visit(Operation o);
}
