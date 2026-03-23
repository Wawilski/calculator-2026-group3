package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/**
 * This class represents the arithmetic multiplication operation "*".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * 
 * @see Operation
 * @see Minus
 * @see Plus
 * @see Divides
 */
public final class Times extends Operation {
  /**
   * Class constructor specifying a number of Expressions to multiply.
   *
   * @param elist The list of Expressions to multiply
   * @throws IllegalConstruction If an empty list of expressions if passed as
   *                             parameter
   */
  public /* constructor */ Times(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "*";
    neutral = 1;
  }

  /**
   * The actual computation of the (binary) arithmetic multiplication of two
   * integers
   * 
   * @param l The first integer
   * @param r The second integer that should be multiplied with the first
   * @return The integer that is the result of the multiplication
   */
  public int op(int l, int r) {
    return (l * r);
  }

  /**
   * The actual computation of the arithmetic multiplication of two IntegerNumber
   * 
   * @param l The first IntegerNumber
   * @param r The second IntegerNumber that should be multiplied to the first
   * @return The IntegerNumber that is the result of the multiplication
   */
  public IntegerNumber op(IntegerNumber l, IntegerNumber r) {
    return new IntegerNumber(l.getValue() * r.getValue());
  }

  /**
   * The actual computation of the arithmetic multiplication of two RationalNumber
   * 
   * @param l The first RationalNumber
   * @param r The second RationalNumber that should be multiplied to the first
   * @return The RationalNumber that is the result of the multiplication
   */
  public RationalNumber op(RationalNumber l, RationalNumber r) {
    int numerator = l.getNumerator() * r.getNumerator();
    int denominator = l.getDenominator() * r.getDenominator();

    return new RationalNumber(numerator, denominator);
  }

  /**
   * The actual computation of the arithmetic multiplication of two RealNumber
   * 
   * @param l The first RealNumber
   * @param r The second RealNumber that should be multiplied to the first
   * @return The RealNumber that is the result of the multiplication
   */
  public RealNumber op(RealNumber l, RealNumber r) {

    RealNumber result;
    if (l.isSpecial() || r.isSpecial()) {
      result = specialOp(l, r);
    } else {

      BigDecimal lValue = l.getValue();
      BigDecimal rValue = r.getValue();

      int precision = Math.min(lValue.scale(), rValue.scale());
      BigDecimal value = lValue.multiply(rValue, MathContext.DECIMAL32);
      value.setScale(precision);
      result = new RealNumber(value);
    }
    return result;
  }

  /**
   * The actual computation of the arithmetic multiplication of two RealNumber
   * if one of them are special (INFINITY or NaN)
   * 
   * @param l The first RealNumber
   * @param r The second RealNumber that should be multiplied to the first
   * @return The RealNumber that is the result of the multiplication
   */
  @Override
  public RealNumber specialOp(RealNumber l, RealNumber r) {
    RealNumber result;

    if (l.sign() == 0 || r.sign() == 0) {
      result = new RealNumber(SpecialNumber.NaN);
    } else if (l.sign() == r.sign()) {
      result = new RealNumber(SpecialNumber.PositiveInfinity);
    } else {
      result = new RealNumber(SpecialNumber.NegativeInfinity);
    }

    return result;

  }

  /**
   * The actual computation of the arithmetic multiplication of two ComplexNumber
   * 
   * @param l The first ComplexNumber
   * @param r The second ComplexNumber that should be multiplied to the first
   * @return The ComplexNumber that is the result of the multiplication
   */
  @Override
  public ComplexNumber op(ComplexNumber l, ComplexNumber r) {
    ComplexNumber result;
    if (l.isNaN() || r.isNaN()) {
      result = new ComplexNumber();
    } else {
      BigDecimal lReal = l.getReal();
      BigDecimal rReal = r.getReal();
      BigDecimal lImaginary = l.getImaginary();
      BigDecimal rImaginary = r.getImaginary();

      BigDecimal realPart = lReal.multiply(rReal).subtract(lImaginary.multiply(rImaginary));
      BigDecimal imPart = lReal.multiply(rImaginary).add(rReal.multiply(lImaginary));

      result = new ComplexNumber(realPart, imPart);
    }
    return result;

  }

}
