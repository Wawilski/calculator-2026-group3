package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/**
 * This class represents the arithmetic division operation "/".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * 
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 */
public final class Divides extends Operation {

  /**
   * Class constructor specifying a number of Expressions to divide.
   *
   * @param elist The list of Expressions to divide
   * @throws IllegalConstruction If an empty list of expressions if passed as
   *                             parameter
   */
  public /* constructor */ Divides(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "/";
    neutral = 1;
  }

  /**
   * The actual computation of the (binary) arithmetic division of two integers
   * 
   * @param l The first integer
   * @param r The second integer that should divide the first
   * @return The integer that is the result of the division
   */
  public int op(int l, int r) {
    if (r == 0)
      throw new ArithmeticException("Division by zero is not allowed.");
    return (l / r);
  }

  /**
   * The actual computation of the arithmetic division of two IntegerNumber
   * 
   * @param l The first IntegerNumber
   * @param r The second IntegerNumber that should be divided to the first
   * @return The RationalNumber representing the division
   */
  public RationalNumber op(IntegerNumber l, IntegerNumber r) {
    int rightHand = r.getValue();
    if (rightHand == 0)
      throw new ArithmeticException("Division by zero is not allowed.");
    return new RationalNumber(l.getValue(), rightHand);
  }

  /**
   * The actual computation of the arithmetic division of two RationalNumber
   * 
   * @param l The first RationalNumber
   * @param r The second RationalNumber that should be divided to the first
   * @return The RationalNumber that is the result of the division
   */
  public RationalNumber op(RationalNumber l, RationalNumber r) {
    int numerator = l.getNumerator() * r.getDenominator();
    int denominator = l.getDenominator() * r.getNumerator();

    if (denominator == 0)
      throw new ArithmeticException("Division by zero is not allowed.");
    return new RationalNumber(numerator, denominator);
  }

  /**
   * The actual computation of the arithmetic division of two RealNumber
   * 
   * @param l The first RealNumber
   * @param r The second RealNumber that should be divided to the first
   * @return The RealNumber that is the result of the division
   */
  public RealNumber op(RealNumber l, RealNumber r) {

    RealNumber result;

    if (l.isSpecial() || r.isSpecial() || r.equals(new RealNumber(0))) {
      result = specialOp(l, r);
    } else {
      BigDecimal lValue = l.getValue();
      BigDecimal rValue = r.getValue();
      BigDecimal value = lValue.divide(rValue, RealNumber.getScale(), RoundingMode.CEILING);
      result = new RealNumber(value);
    }
    return result;
  }

  /**
   * The actual computation of the arithmetic division of two RealNumber
   * if one of them are special (INFINITY or NaN) or if the divisor is 0
   * 
   * @param l The first RealNumber
   * @param r The second RealNumber that should be divided to the first
   * @return The RealNumber that is the result of the division
   */
  @Override
  public RealNumber specialOp(RealNumber l, RealNumber r) {
    RealNumber result;
    SpecialNumber lSpecialValue = l.getSpecialValue();
    SpecialNumber rSpecialValue = r.getSpecialValue();
    int lSign = l.sign();
    int rSign = r.sign();

    if (lSpecialValue == SpecialNumber.NaN
        || rSpecialValue == SpecialNumber.NaN
        || (l.isSpecial() && r.isSpecial())
        || (l.sign() == 0 && r.sign() == 0)) {
      result = new RealNumber(SpecialNumber.NaN);
    } else if ((lSign > 0 && rSign >= 0) || (lSign < 0 && rSign < 0)) {
      result = new RealNumber(SpecialNumber.PositiveInfinity);
    } else if ((lSign < 0 && rSign >= 0) || (lSign > 0 && rSign < 0)) {
      result = new RealNumber(SpecialNumber.NegativeInfinity);
    } else {
      result = new RealNumber(new BigDecimal(0));

    }
    return result;

  }

  @Override
  public ComplexNumber op(ComplexNumber l, ComplexNumber r) {
    ComplexNumber result;
    if (l.isNaN() || r.isNaN() || r.equals(new ComplexNumber(0, 0))) {
      result = new ComplexNumber();
    } else {

      BigDecimal lReal = l.getReal();
      BigDecimal rReal = r.getReal();
      BigDecimal lImaginary = l.getImaginary();
      BigDecimal rImaginary = r.getImaginary();

      BigDecimal mod = (lImaginary.pow(2)).multiply(rImaginary.pow(2));

      BigDecimal realPart = lReal.multiply(rReal).add(lImaginary.multiply(rImaginary));
      BigDecimal imPart = rReal.multiply(lImaginary).subtract(lReal.multiply(rImaginary));

      result = new ComplexNumber(realPart.divide(mod, RealNumber.getScale(), RoundingMode.CEILING),
          imPart.divide(mod, RealNumber.getScale(), RoundingMode.CEILING));
    }
    return result;
  }
}
