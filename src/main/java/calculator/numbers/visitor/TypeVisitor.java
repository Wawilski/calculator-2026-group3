package calculator.numbers.visitor;

import calculator.numbers.IllegalCasting;
import calculator.numbers.IntegerNumber;

import calculator.numbers.RealNumber;

/**
 * e TypeVisitor
 */
public abstract class TypeVisitor {

  /**
   * The Visitor can traverse a real type number
   *
   * @param r the RealNumber visited
   */
  public abstract void visit(RealNumber r);

  /**
   * The Visitor can traverse an integer type number
   *
   * @param i the IntegerNumber visited
   */
  public abstract void visit(IntegerNumber i);

}
