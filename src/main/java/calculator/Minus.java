package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/**
 * This class represents the arithmetic operation "-".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * 
 * @see Operation
 * @see Plus
 * @see Times
 * @see Divides
 */
public final class Minus extends Operation {

  /**
   * Class constructor specifying a number of Expressions to subtract.
   *
   * @param elist The list of Expressions to subtract
   * @throws IllegalConstruction If an empty list of expressions if passed as
   *                             parameter
   */
  public /* constructor */ Minus(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "-";
    neutral = 0;
  }

  /**
   * The actual computation of the (binary) arithmetic subtraction of two integers
   * 
   * @param l The first integer
   * @param r The second integer that should be subtracted from the first
   * @return The integer that is the result of the subtraction
   */
  public int op(int l, int r) {
    return (l - r);
  }

  /**
   * The actual computation of the arithmetic subtraction of two IntegerNumber
   * 
   * @param l The first IntegerNumber
   * @param r The second IntegerNumber that should be subtracted from the first
   * @return The IntegerNumber that is the result of the subtraction
   */
  public IntegerNumber op(IntegerNumber l, IntegerNumber r) {
    return new IntegerNumber(l.getValue() - r.getValue());
  }

  /**
   * The actual computation of the arithmetic subtraction of two RationalNumber
   * 
   * @param l The first RationalNumber
   * @param r The second RationalNumber that should be subtracted to the first
   * @return The RationalNumber that is the result of the subtraction
   */
  public RationalNumber op(RationalNumber l, RationalNumber r) {
    int numerator = l.getNumerator() * r.getDenominator() - r.getNumerator() * l.getDenominator();
    int denominator = l.getDenominator() * r.getDenominator();

    return new RationalNumber(numerator, denominator);
  }

  /**
   * The actual computation of the arithmetic subtraction of two RealNumber
   * 
   * @param l The first RealNumber
   * @param r The second RealNumber that should be subtracted from the first
   * @return The RealNumber that is the result of the subtraction
   */
  public RealNumber op(RealNumber l, RealNumber r) {

    RealNumber result;
    if (l.isSpecial() || r.isSpecial()) {
      result = specialOp(l, r);
    } else {

      BigDecimal lValue = l.getValue();
      BigDecimal rValue = r.getValue();

      int precision = Math.min(lValue.scale(), rValue.scale());
      BigDecimal value = lValue.subtract(rValue, MathContext.DECIMAL32);
      value.setScale(precision);
      result = new RealNumber(value);
    }
    return result;
  }

  /**
   * The actual computation of the arithmetic subtraction of two RealNumber
   * if one of them are special (INFINITY or NaN)
   * 
   * @param l The first RealNumber
   * @param r The second RealNumber that should be subtracted from the first
   * @return The RealNumber that is the result of the subtraction
   */
  @Override
  public RealNumber specialOp(RealNumber l, RealNumber r) {
    RealNumber result;

    if (l.getSpecialValue().equals(r.getSpecialValue()) || l.getSpecialValue() == SpecialNumber.NaN
        || r.getSpecialValue() == SpecialNumber.NaN) {
      result = new RealNumber(SpecialNumber.NaN, true);
    } else if (r.getSpecialValue() == SpecialNumber.PositiveInfinity) {
      result = new RealNumber(SpecialNumber.NegativeInfinity, true);
    } else if (r.getSpecialValue() == SpecialNumber.NegativeInfinity) {
      result = new RealNumber(SpecialNumber.PositiveInfinity, true);
    } else {
      result = new RealNumber(l.getSpecialValue(), true);
    }
    return result;

  }
}
