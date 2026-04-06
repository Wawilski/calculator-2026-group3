package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.NumberType;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;
import calculator.numbers.visitor.TypeCaster;

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
   * <p>
   * Rational operands are cast to real values and delegated to the real overload.
   */
  @Override
  public BaseNumber op(RationalNumber l, RationalNumber r) {
    TypeCaster caster = new TypeCaster(NumberType.REAL);
    l.accept(caster);
    RealNumber left = (RealNumber) caster.getResult();
    r.accept(caster);
    RealNumber right = (RealNumber) caster.getResult();
    return op(left, right);
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
    return new RealNumber(Double.toString(value));
  }

  /**
   * Compute x ** y for complex operands.
   * Use exponential to compute it
   * l**r = e**(l*ln(r))
   */
  @Override
  public BaseNumber op(ComplexNumber l, ComplexNumber r) {
    // is not defined if l == 0
    if (l.isNaN() || r.isNaN() ||
        (l.getReal().compareTo(BigDecimal.ZERO) == 0 && l.getReal().compareTo(BigDecimal.ZERO) == 0)) {
      return new ComplexNumber();
    }
    double a = l.getReal().doubleValue();
    double b = l.getImaginary().doubleValue();
    double c = r.getReal().doubleValue();
    double d = r.getImaginary().doubleValue();

    double modulus = Math.sqrt(a * a + b * b);
    double arg = Math.atan2(b, a);
    double lnZ_re = Math.log(modulus);
    double lnZ_im = arg;

    double v_re = c * lnZ_re - d * lnZ_im;
    double v_im = c * lnZ_im + d * lnZ_re;

    double expX = Math.exp(v_re);
    BigDecimal finalRe = BigDecimal.valueOf(expX * Math.cos(v_im));
    BigDecimal finalIm = BigDecimal.valueOf(expX * Math.sin(v_im));

    return new ComplexNumber(finalRe, finalIm);
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
