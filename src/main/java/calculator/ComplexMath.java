package calculator;

import calculator.numbers.ComplexNumber;

/** Utility methods for elementary complex-valued functions. */
public final class ComplexMath {

  public final double HALF_PI = Math.PI / 2.0;

  public final class ComplexValue {
    public final double re;
    public final double im;

    public ComplexValue(double re, double im) {
      this.re = re;
      this.im = im;
    }
  }

  public ComplexMath() {
  }

  /**
   * Compute complex sine.
   *
   * <p>Formula: sin(a + bi) = sin(a)cosh(b) + i cos(a)sinh(b).
   *
   * @param z input complex number
   * @return sin(z), or NaN complex on invalid input
   */
  public ComplexNumber sin(ComplexNumber z) {
    ComplexValue c = from(z);
    if (c == null) {
      return new ComplexNumber();
    }
    double re = Math.sin(c.re) * Math.cosh(c.im);
    double im = Math.cos(c.re) * Math.sinh(c.im);
    return to(re, im);
  }

  /**
   * Compute complex cosine.
   *
   * <p>Formula: cos(a + bi) = cos(a)cosh(b) - i sin(a)sinh(b).
   *
   * @param z input complex number
   * @return cos(z), or NaN complex on invalid input
   */
  public ComplexNumber cos(ComplexNumber z) {
    ComplexValue c = from(z);
    if (c == null) {
      return new ComplexNumber();
    }
    double re = Math.cos(c.re) * Math.cosh(c.im);
    double im = -Math.sin(c.re) * Math.sinh(c.im);
    return to(re, im);
  }

  /**
   * Compute complex tangent.
   *
   * <p>Formula:
   * tan(a + bi) = sin(2a)/(cos(2a)+cosh(2b)) + i sinh(2b)/(cos(2a)+cosh(2b)).
   *
   * @param z input complex number
   * @return tan(z), or NaN complex on invalid input
   */
  public ComplexNumber tan(ComplexNumber z) {
    ComplexValue c = from(z);
    if (c == null) {
      return new ComplexNumber();
    }
    double denominator = Math.cos(2.0 * c.re) + Math.cosh(2.0 * c.im);
    if (denominator == 0.0) {
      return new ComplexNumber();
    }
    double re = Math.sin(2.0 * c.re) / denominator;
    double im = Math.sinh(2.0 * c.im) / denominator;
    return to(re, im);
  }

  /**
   * Compute complex arc-sine.
   *
   * <p>Formula: asin(z) = -i * log(iz + sqrt(1 - z^2)).
   *
   * @param z input complex number
   * @return asin(z), or NaN complex on invalid input
   */
  public ComplexNumber asin(ComplexNumber z) {
    ComplexValue c = from(z);
    if (c == null) {
      return new ComplexNumber();
    }
    ComplexValue iz = new ComplexValue(-c.im, c.re);
    ComplexValue oneMinusZSquared = sub(new ComplexValue(1.0, 0.0), mul(c, c));
    ComplexValue root = sqrt(oneMinusZSquared);
    ComplexValue logArg = add(iz, root);
    ComplexValue logged = log(logArg);
    // -i * (x + iy) = y - ix
    return to(logged.im, -logged.re);
  }

  /**
   * Compute complex arc-cosine.
   *
   * <p>Identity: acos(z) = pi/2 - asin(z).
   *
   * @param z input complex number
   * @return acos(z), or NaN complex on invalid input
   */
  public ComplexNumber acos(ComplexNumber z) {
    ComplexNumber asinValue = asin(z);
    if (asinValue.isNaN()) {
      return asinValue;
    }
    ComplexValue c = from(asinValue);
    if (c == null) {
      return new ComplexNumber();
    }
    return to(HALF_PI - c.re, -c.im);
  }

  /**
   * Compute complex arc-tangent.
   *
   * <p>Formula: atan(z) = (i/2) * (log(1 - iz) - log(1 + iz)).
   *
   * @param z input complex number
   * @return atan(z), or NaN complex on invalid input
   */
  public ComplexNumber atan(ComplexNumber z) {
    ComplexValue c = from(z);
    if (c == null) {
      return new ComplexNumber();
    }
    ComplexValue iz = new ComplexValue(-c.im, c.re);
    ComplexValue oneMinusIz = sub(new ComplexValue(1.0, 0.0), iz);
    ComplexValue onePlusIz = add(new ComplexValue(1.0, 0.0), iz);
    ComplexValue leftLog = log(oneMinusIz);
    ComplexValue rightLog = log(onePlusIz);
    ComplexValue diff = sub(leftLog, rightLog);
    // (i / 2) * (x + iy) = (-y/2) + i(x/2)
    return to(-0.5 * diff.im, 0.5 * diff.re);
  }

  /** @return a + b */
  public ComplexValue add(ComplexValue a, ComplexValue b) {
    return new ComplexValue(a.re + b.re, a.im + b.im);
  }

  /** @return a - b */
  public ComplexValue sub(ComplexValue a, ComplexValue b) {
    return new ComplexValue(a.re - b.re, a.im - b.im);
  }

  /** @return a * b */
  public ComplexValue mul(ComplexValue a, ComplexValue b) {
    return new ComplexValue(a.re * b.re - a.im * b.im, a.re * b.im + a.im * b.re);
  }

  /**
   * Principal square root for a complex number.
   *
   * @param a complex value
   * @return sqrt(a) on the principal branch
   */
  public ComplexValue sqrt(ComplexValue a) {
    double modulus = Math.hypot(a.re, a.im);
    double re = Math.sqrt((modulus + a.re) / 2.0);
    double im = Math.copySign(Math.sqrt((modulus - a.re) / 2.0), a.im);
    return new ComplexValue(re, im);
  }

  /**
   * Principal natural logarithm for a complex number.
   *
   * @param a complex value
   * @return log(a) on the principal branch
   */
  public ComplexValue log(ComplexValue a) {
    double modulus = Math.hypot(a.re, a.im);
    double re = Math.log(modulus);
    double im = Math.atan2(a.im, a.re);
    return new ComplexValue(re, im);
  }

  /**
   * Convert domain model complex number to internal representation.
   *
   * @param z domain model value
   * @return internal value, or null if input is null/NaN
   */
  public ComplexValue from(ComplexNumber z) {
    if (z == null || z.isNaN()) {
      return null;
    }
    return new ComplexValue(z.getReal().doubleValue(), z.getImaginary().doubleValue());
  }

  /**
   * Convert primitive parts to domain model complex number.
   *
   * @param re real part
   * @param im imaginary part
   * @return complex number, or NaN complex if parts are not finite
   */
  public ComplexNumber to(double re, double im) {
    if (Double.isNaN(re) || Double.isNaN(im) || Double.isInfinite(re) || Double.isInfinite(im)) {
      return new ComplexNumber();
    }
    return new ComplexNumber(re, im);
  }
}