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
public final class Abs extends Function {

  public Abs(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "abs";
    neutral = 0;
    arity = 1;
  }

  @Override
  public int function(int l, int r) {
    return Math.abs(l);
  }

  @Override
  public BaseNumber function(IntegerNumber l, IntegerNumber r) {
    return new IntegerNumber(Math.abs(l.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber l, RationalNumber r) {
    return new RationalNumber(Math.abs(l.getNumerator()), Math.abs(l.getDenominator()));
  }

  @Override
  public BaseNumber function(RealNumber l, RealNumber r) {
    if (l.isSpecial()) {
      if (l.getSpecialValue() == SpecialNumber.NaN) {
        return new RealNumber(SpecialNumber.NaN);
      }
      return new RealNumber(SpecialNumber.PositiveInfinity);
    }
    return new RealNumber(l.getValue().abs(MathContext.DECIMAL32));
  }

  @Override
  public BaseNumber function(ComplexNumber l, ComplexNumber r) {
    if (l.isNaN()) {
      return new RealNumber(SpecialNumber.NaN);
    }
    BigDecimal modSquared = l.getReal().pow(2).add(l.getImaginary().pow(2));
    return new RealNumber(Math.sqrt(modSquared.doubleValue()));
  }
}
