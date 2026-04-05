package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Arc sine function: asin(x). */
public final class Asin extends UnaryFunction {

  /**
   * Build an arc-sine function with one argument.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Asin(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "asin";
    neutral = 0;
  }

  @Override
  public int function(int value) {
    return (int) Math.asin(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    return new RealNumber(Math.asin(value.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    double ratio = new RationalMath().toDouble(value);
    return new RealNumber(Math.asin(ratio));
  }

  /**
   * Compute asin(x) for real numbers.
   *
   * <p>Returns NaN for special values and for out-of-domain values |x| &gt; 1.
   */
  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      return new RealNumber(SpecialNumber.NaN);
    }

    BigDecimal realValue = value.getValue();
    if (realValue.abs().compareTo(BigDecimal.ONE) > 0) {
      return new RealNumber(SpecialNumber.NaN);
    }

    return new RealNumber(Math.asin(realValue.doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexMath().asin(value);
  }
}