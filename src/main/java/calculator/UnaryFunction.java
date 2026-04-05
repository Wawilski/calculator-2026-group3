package calculator;

import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;

/**
 * Unary mathematical function.
 *
 * <p>This class defines the contract for one-argument functions and
 * type-specific evaluation entry points.
 */
public abstract class UnaryFunction extends Function {

  /**
   * Build a unary function.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  protected UnaryFunction(List<Expression> elist) throws IllegalConstruction {
    super(elist, 1);
  }

  /**
   * Evaluate the unary function for primitive integer values.
   *
   * @param value integer input
   * @return primitive integer result
   */
  public abstract int function(int value);

  /**
   * Evaluate the unary function for {@link IntegerNumber}.
   *
   * @param value integer input
   * @return evaluated number
   */
  public abstract BaseNumber function(IntegerNumber value);

  /**
   * Evaluate the unary function for {@link RationalNumber}.
   *
   * @param value rational input
   * @return evaluated number
   */
  public abstract BaseNumber function(RationalNumber value);

  /**
   * Evaluate the unary function for {@link RealNumber}.
   *
   * @param value real input
   * @return evaluated number
   */
  public abstract BaseNumber function(RealNumber value);

  /**
   * Evaluate the unary function for {@link ComplexNumber}.
   *
   * @param value complex input
   * @return evaluated number
   */
  public abstract BaseNumber function(ComplexNumber value);
}
