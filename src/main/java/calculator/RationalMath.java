package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

import calculator.numbers.RationalNumber;

/** Utility methods for rational-number conversions. */
public final class RationalMath {

  /** Utility class constructor. */
  public RationalMath() {
    // Intentionally empty.
  }

  /**
   * Convert a rational number to double using BigDecimal division first.
   *
   * <p>This limits precision loss compared to direct primitive division before
   * converting to double for use with {@code Math.*} functions.
   *
   * @param value rational number to convert
   * @return decimal approximation as double
   */
  public double toDouble(RationalNumber value) {
    BigDecimal numerator = BigDecimal.valueOf(value.getNumerator());
    BigDecimal denominator = BigDecimal.valueOf(value.getDenominator());
    return numerator.divide(denominator, MathContext.DECIMAL64).doubleValue();
  }
}