package calculator;

import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Arc tangent function: atan(x). */
public final class Atan extends UnaryFunction {

  /**
   * Build an arc-tangent function with one argument.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Atan(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "atan";
    neutral = 0;
  }

  @Override
  public int function(int value) {
    return (int) Math.atan(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    return new RealNumber(Math.atan(value.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    double ratio = new RationalMath().toDouble(value);
    return new RealNumber(Math.atan(ratio));
  }

  /**
   * Compute atan(x) for real numbers.
   *
   * <p>Special values are mapped to the usual principal values:
   * +inf -&gt; pi/2, -inf -&gt; -pi/2, NaN -&gt; NaN.
   */
  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      if (value.getSpecialValue() == SpecialNumber.PositiveInfinity) {
        return new RealNumber(Math.PI / 2.0);
      }
      if (value.getSpecialValue() == SpecialNumber.NegativeInfinity) {
        return new RealNumber(-Math.PI / 2.0);
      }
      return new RealNumber(SpecialNumber.NaN);
    }

    return new RealNumber(Math.atan(value.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexMath().atan(value);
  }
}