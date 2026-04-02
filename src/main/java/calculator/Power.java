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

/** Power function: x^y. */
public final class Power extends BinaryFunction {

  public Power(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "^";
    neutral = 1;
  }

  @Override
  public int function(int l, int r) {
    return (int) Math.pow(l, r);
  }

  @Override
  public BaseNumber function(IntegerNumber l, IntegerNumber r) {
    return new IntegerNumber((int) Math.pow(l.getValue(), r.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber l, RationalNumber r) {
    double base = ((double) l.getNumerator()) / l.getDenominator();
    double exponent = ((double) r.getNumerator()) / r.getDenominator();
    return new RealNumber(Math.pow(base, exponent));
  }

  @Override
  public BaseNumber function(RealNumber l, RealNumber r) {
    if (l.isSpecial() || r.isSpecial()) {
      return specialFunction(l, r);
    }

    double base = l.getValue().doubleValue();
    double exponent = r.getValue().doubleValue();
    double value = Math.pow(base, exponent);

    if (Double.isNaN(value)) {
      return new RealNumber(SpecialNumber.NaN);
    }
    if (Double.isInfinite(value)) {
      return value > 0
          ? new RealNumber(SpecialNumber.PositiveInfinity)
          : new RealNumber(SpecialNumber.NegativeInfinity);
    }
    return new RealNumber(new BigDecimal(value, MathContext.DECIMAL32));
  }

  @Override
  public BaseNumber function(ComplexNumber l, ComplexNumber r) {
    if (l.isNaN() || r.isNaN()) {
      return new ComplexNumber();
    }

    if (r.getImaginary().compareTo(BigDecimal.ZERO) != 0 || l.getImaginary().compareTo(BigDecimal.ZERO) != 0) {
      return new ComplexNumber();
    }

    double value = Math.pow(l.getReal().doubleValue(), r.getReal().doubleValue());
    if (Double.isNaN(value) || Double.isInfinite(value)) {
      return new ComplexNumber();
    }
    return new ComplexNumber(new BigDecimal(value, MathContext.DECIMAL32), BigDecimal.ZERO);
  }

  private BaseNumber specialFunction(RealNumber l, RealNumber r) {
    if (l.isSpecial() && l.getSpecialValue() == SpecialNumber.NaN) {
      return new RealNumber(SpecialNumber.NaN);
    }
    if (r.isSpecial() && r.getSpecialValue() == SpecialNumber.NaN) {
      return new RealNumber(SpecialNumber.NaN);
    }

    if (r.isSpecial()) {
      return new RealNumber(SpecialNumber.NaN);
    }

    if (l.isSpecial()) {
      if (l.getSpecialValue() == SpecialNumber.PositiveInfinity) {
        return new RealNumber(SpecialNumber.PositiveInfinity);
      }
      return new RealNumber(SpecialNumber.NaN);
    }

    return new RealNumber(SpecialNumber.NaN);
  }
}
