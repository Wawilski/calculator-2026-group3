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

/** Power operation: x ** y. */
public final class Power extends Operation {

  /**
   * Build a power operation.
   *
   * @param elist operation argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Power(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "**";
    neutral = 1;
  }

  @Override
  public int op(int l, int r) {
    return (int) Math.pow(l, r);
  }

  @Override
  public BaseNumber op(IntegerNumber l, IntegerNumber r) {
    return new IntegerNumber((int) Math.pow(l.getValue(), r.getValue()));
  }

  /**
   * Compute x ** y for rational operands.
   *
   * <p>Rational values are converted through {@link RationalMath#toDouble(RationalNumber)}.
   */
  @Override
  public BaseNumber op(RationalNumber l, RationalNumber r) {
    double base = new RationalMath().toDouble(l);
    double exponent = new RationalMath().toDouble(r);
    return new RealNumber(Math.pow(base, exponent));
  }

  /**
   * Compute x ** y for real operands with special-value handling.
   */
  @Override
  public BaseNumber op(RealNumber l, RealNumber r) {
    if (l.isSpecial() || r.isSpecial()) {
      return specialOp(l, r);
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
    return new RealNumber(new BigDecimal(Double.toString(value), MathContext.DECIMAL32));
  }

  /**
   * Compute x ** y for complex operands.
   *
   * <p>Current implementation handles only purely real complex values; otherwise
   * it returns NaN complex.
   */
  @Override
  public BaseNumber op(ComplexNumber l, ComplexNumber r) {
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
    return new ComplexNumber(new BigDecimal(Double.toString(value), MathContext.DECIMAL32), BigDecimal.ZERO);
  }

  /**
   * Handle special real values for power.
   *
   * @return evaluated special real result
   */
  @Override
  public RealNumber specialOp(RealNumber l, RealNumber r) {
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
