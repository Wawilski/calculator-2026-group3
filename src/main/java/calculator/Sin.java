package calculator;

import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Sine function: sin(x). */
public final class Sin extends UnaryFunction {

  /**
   * Build a sine function.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Sin(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "sin";
    neutral = 0;
  }

  @Override
  public int function(int value) {
    return (int) Math.sin(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    return new RealNumber(Math.sin(value.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    double ratio = new RationalMath().toDouble(value);
    return new RealNumber(Math.sin(ratio));
  }

  /**
   * Compute sin(x) for real values.
   *
   * <p>Special values are mapped to NaN.
   */
  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sin(value.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexMath().sin(value);
  }
}
