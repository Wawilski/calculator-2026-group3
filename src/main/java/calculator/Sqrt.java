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

  public Sqrt(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "sqrt";
    neutral = 1;
  }

  @Override
  public int function(int value) {
    return (int) Math.sqrt(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    if (value.getValue() < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sqrt(value.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    double ratio = ((double) value.getNumerator()) / value.getDenominator();
    if (ratio < 0.0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sqrt(ratio));
  }

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
