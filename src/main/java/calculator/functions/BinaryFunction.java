package calculator.functions;

import java.util.List;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;

/**
 * Binary mathematical function.
 *
 * <p>
 * This class defines the contract for two-argument functions and
 * type-specific evaluation entry points.
 */
public abstract class BinaryFunction extends Function {

  /**
   * Build a binary function.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  protected BinaryFunction(List<Expression> elist) throws IllegalConstruction {
    super(elist, 2);
  }

  /**
   * Evaluate the binary function for primitive integer values.
   *
   * @param left  left operand
   * @param right right operand
   * @return primitive integer result
   */
  public abstract int function(int left, int right);

  /**
   * Evaluate the binary function for {@link IntegerNumber} operands.
   *
   * @param left  left operand
   * @param right right operand
   * @return evaluated number
   */
  public abstract BaseNumber function(IntegerNumber left, IntegerNumber right);

  /**
   * Evaluate the binary function for {@link RationalNumber} operands.
   *
   * @param left  left operand
   * @param right right operand
   * @return evaluated number
   */
  public abstract BaseNumber function(RationalNumber left, RationalNumber right);

  /**
   * Evaluate the binary function for {@link RealNumber} operands.
   *
   * @param left  left operand
   * @param right right operand
   * @return evaluated number
   */
  public abstract BaseNumber function(RealNumber left, RealNumber right);

  /**
   * Evaluate the binary function for {@link ComplexNumber} operands.
   *
   * @param left  left operand
   * @param right right operand
   * @return evaluated number
   */
  public abstract BaseNumber function(ComplexNumber left, ComplexNumber right);
}
