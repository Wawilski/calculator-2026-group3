package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Base-10 logarithm function: log(x). */
public final class Log extends Function {

  public Log(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "log";
    neutral = 0;
    arity = 1;
  }

  @Override
  public int function(int l, int r) {
    return (int) Math.log10(l);
  }

  @Override
  public BaseNumber function(IntegerNumber l, IntegerNumber r) {
    if (l.getValue() == 0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (l.getValue() < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log10(l.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber l, RationalNumber r) {
    double value = ((double) l.getNumerator()) / l.getDenominator();
    if (value == 0.0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (value < 0.0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log10(value));
  }

  @Override
  public BaseNumber function(RealNumber l, RealNumber r) {
    if (l.isSpecial()) {
      if (l.getSpecialValue() == SpecialNumber.PositiveInfinity) {
        return new RealNumber(SpecialNumber.PositiveInfinity);
      }
      return new RealNumber(SpecialNumber.NaN);
    }
    int cmp = l.getValue().compareTo(BigDecimal.ZERO);
    if (cmp == 0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (cmp < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log10(l.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber l, ComplexNumber r) {
    return new ComplexNumber();
  }
}
