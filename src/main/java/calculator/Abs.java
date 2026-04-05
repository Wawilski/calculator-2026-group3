package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Absolute value function: abs(x). */
public final class Abs extends UnaryFunction {

  /**
   * Build an absolute-value.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Abs(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "abs";
    neutral = 0;
  }

  @Override
  public int function(int value) {
    return Math.abs(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    return new IntegerNumber(Math.abs(value.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    return new RationalNumber(Math.abs(value.getNumerator()), Math.abs(value.getDenominator()));
  }

  /**
   * Compute abs(x) for real values.
   *
   * <p>Special handling:
   * NaN -&gt; NaN, +/-inf -&gt; +inf.
   */
  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      if (value.getSpecialValue() == SpecialNumber.NaN) {
        return new RealNumber(SpecialNumber.NaN);
      }
      return new RealNumber(SpecialNumber.PositiveInfinity);
    }
    return new RealNumber(value.getValue().abs(MathContext.DECIMAL32));
  }

  /**
   * Compute the modulus for complex values: |a + bi| = sqrt(a^2 + b^2).
   *
   * @param value complex input value
   * @return real modulus, or NaN for NaN complex input
   */
  @Override
  public BaseNumber function(ComplexNumber value) {
    if (value.isNaN()) {
      return new RealNumber(SpecialNumber.NaN);
    }
    BigDecimal modSquared = value.getReal().pow(2).add(value.getImaginary().pow(2));
    return new RealNumber(Math.sqrt(modSquared.doubleValue()));
  }
}
