package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.IntegerNumber;
import calculator.numbers.RealNumber;

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

  public IntegerNumber op(IntegerNumber l, IntegerNumber r) {
    return new IntegerNumber(l.getValue() * r.getValue());
  }

  public RealNumber op(RealNumber l, RealNumber r) {

    RealNumber result;
    if (l.isSpecial() || r.isSpecial()) {
      result = specialOp(l, r);
    } else {

      BigDecimal lValue = l.getValue();
      BigDecimal rValue = r.getValue();

      int precision = Math.min(lValue.scale(), rValue.scale());
      BigDecimal value = lValue.multiply(rValue);
      value.setScale(precision);
      result = new RealNumber(value);
    }
    return result;
  }

  @Override
  public RealNumber specialOp(RealNumber l, RealNumber r) {
    RealNumber result;

    if (l.sign() == 0 || r.sign() == 0) {
      result = new RealNumber(RealNumber.SpecialNumbers.NaN, true);
    } else if (l.sign() == r.sign()) {
      result = new RealNumber(RealNumber.SpecialNumbers.PositiveInfinity, true);
    } else {
      result = new RealNumber(RealNumber.SpecialNumbers.NegativeInfinity, true);
    }

    return result;

  }
}
