package calculator.numbers;

import calculator.numbers.visitor.*;

import calculator.Expression;
import calculator.Operation;

/**
 * BaseNumber is an interface that helps representing all type of numbers.
 * 
 * @see IntegerNumber
 * @see RationalNumber
 * @see RealNumber
 * @see ComplexNumber
 */
public interface BaseNumber extends Expression {

  /**
   * accept is a method needed to implement the visitor design pattern
   *
   * @param visitor The visitor object being passed as a parameter
   */
  public void accept(TypeVisitor visitor);

  /**
   * op is a method needed to apply an operation between a BaseNumber and another
   * 
   * @param o         The operation to apply
   * @param rightHand The right hand of the expression
   * @return The result of the operation
   */
  public BaseNumber op(Operation o, BaseNumber rightHand);

}
