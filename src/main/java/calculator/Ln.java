package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Natural logarithm function: ln(x). */
public final class Ln extends UnaryFunction {

  public Ln(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "ln";
    neutral = 0;
  }

  @Override
  public int function(int value) {
    return (int) Math.log(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    if (value.getValue() == 0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (value.getValue() < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log(value.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    double ratio = ((double) value.getNumerator()) / value.getDenominator();
    if (ratio == 0.0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (ratio < 0.0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log(ratio));
  }

  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      if (value.getSpecialValue() == SpecialNumber.PositiveInfinity) {
        return new RealNumber(SpecialNumber.PositiveInfinity);
      }
      return new RealNumber(SpecialNumber.NaN);
    }
    int cmp = value.getValue().compareTo(BigDecimal.ZERO);
    if (cmp == 0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (cmp < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log(value.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexNumber();
  }
}
