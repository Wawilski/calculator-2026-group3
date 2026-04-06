package calculator.numbers;

import calculator.Expression;
import calculator.functions.Function;
import calculator.Operation;
import calculator.numbers.visitor.TypeVisitor;

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

  /**
   * negate is a method needed to get the negation of a number
   * 
   * @return BaseNumber negation
   */
  public BaseNumber negate();

  /**
   * Apply a unary function to this number.
   *
   * @param f The function to apply
   * @return The result of the function
   */
  public BaseNumber function(Function f);

  /**
   * Apply a function to this number and another operand.
   *
   * @param f         The function to apply
   * @param rightHand The right-hand operand
   * @return The result of the function
   */
  public BaseNumber function(Function f, BaseNumber rightHand);

}
