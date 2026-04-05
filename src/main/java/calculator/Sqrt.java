package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Square root function: sqrt(x). */
public final class Sqrt extends UnaryFunction {

  /**
   * Build a square-root.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Sqrt(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "sqrt";
    neutral = 1;
  }

  @Override
  public int function(int value) {
    return (int) Math.sqrt(value);
  }

  /**
   * Compute sqrt(x) for integer inputs.
   *
   * <p>Negative inputs return NaN.
   */
  @Override
  public BaseNumber function(IntegerNumber value) {
    if (value.getValue() < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sqrt(value.getValue()));
  }

  /**
   * Compute sqrt(x) for rational inputs.
   *
   * <p>Negative inputs return NaN.
   */
  @Override
  public BaseNumber function(RationalNumber value) {
    double ratio = new RationalMath().toDouble(value);
    if (ratio < 0.0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sqrt(ratio));
  }

  /**
   * Compute sqrt(x) for real inputs with special-value handling.
   */
  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      if (value.getSpecialValue() == SpecialNumber.PositiveInfinity) {
        return new RealNumber(SpecialNumber.PositiveInfinity);
      }
      return new RealNumber(SpecialNumber.NaN);
    }
    if (value.getValue().compareTo(BigDecimal.ZERO) < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sqrt(value.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexNumber();
  }
}
