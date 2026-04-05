package calculator.numbers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import calculator.BinaryFunction;
import calculator.Function;
import calculator.Operation;
import calculator.UnaryFunction;
import calculator.numbers.visitor.TypeVisitor;
import lombok.Getter;
import visitor.Visitor;

/**
 * This class represents the real numbers.
 * The class implements the inteface BaseNumber.
 * 
 * @see BaseNumber
 * @see IntegerNumber
 * @see RationalNumber
 * @see ComplexNumber
 */
@Getter
public class RealNumber implements BaseNumber {

  /**
   * The value of the real number
   * -- GETTER --
   *  getter method to obtain the value contained in the object
   *
   * @return The BigDecimal contained in the object

   */
  private BigDecimal value;

  /**
   * A flag to determine if the number is a real number or a special value
   * (+INFINITY, -INFINITY, NaN)
   * -- GETTER --
   *  getter method to obtain the special flag value
   *
   * @return If the real is a Special one

   */
  private boolean special;
  /**
   * The special value of the number (+INFINITY,-INFINITY,NaN)
   * -- GETTER --
   *  getter method to obtain the special value contained in the object
   *
   * @return The BigDecimal contained in the object

   */
  private SpecialNumber specialValue;

  /**
   * The scale of all the BigDecimal concerning the real and complex numbers
   * -- GETTER --
   *  getter method to obtain the scale of the BigDecimal values
   *
   * @return The class attribute scale

   */
  @Getter
  private static int scale = 16;

  /**
   * class constructor which specify the value of the real number as a
   * BigDecimal
   *
   *
   * @param value the BigDecimal value representing the real number
   */
  public /* constructor */ RealNumber(BigDecimal value) {
    this.value = value.setScale(scale, RoundingMode.CEILING);
    this.special = false;
    this.specialValue = null;
  }

  /**
   * class constructor which specify the value of the real number as an integer
   *
   *
   * @param value the integer value representing the real number
   */
  public /* constructor */ RealNumber(int value) {
    this.value = (new BigDecimal(value, MathContext.UNLIMITED)).setScale(scale, RoundingMode.CEILING);
    this.special = false;
    this.specialValue = null;
  }

  /**
   * class constructor which specify the value of the real number as a double
   *
   *
   * @param value the double value representing the real number
   */
  public /* constructor */ RealNumber(double value) {
    this.value = (new BigDecimal(value, MathContext.UNLIMITED)).setScale(scale, RoundingMode.CEILING);
    this.special = false;
    this.specialValue = null;
  }

  /**
   * class constructor which specify the value of the real number with a String
   *
   *
   * @param value the String value representing the real number
   */

  public /* constructor */ RealNumber(String value) {
    this.value = (new BigDecimal(value, MathContext.UNLIMITED)).setScale(scale, RoundingMode.CEILING);
    this.special = false;
    this.specialValue = null;
  }

  /**
   * class constructor for a special real number (+INF, -INF, NaN)
   *
   *
   * @param specialValue the value of the special real number
   */
  public /* constructor */ RealNumber(SpecialNumber specialValue) {
    this.value = null;
    this.special = true;
    this.specialValue = specialValue;
  }

    /**
   * accept method to implement the visitor design pattern to traverse arithmetic
   * expressions.
   * Each number will pass itself to the visitor object to get processed by the
   * visitor.
   *
   * @param v The visitor object
   */
  public void accept(Visitor v) {
    v.visit(this);
  }

  /**
   * accept method to implement the visitor design pattern to cost the
   * IntegerNumber
   * into another type of number (ComplexNumber) or
   * compare
   * its type with another BaseNumber.
   * 
   * Each number will pass itself to the visitor object to get processed by the
   * visitor.
   *
   * @param v The visitor object
   * @see TypeVisitor
   */
  public void accept(TypeVisitor v) {
    v.visit(this);

  }

  /**
   * apply an operation between this and another RealNumber
   *
   * @param o         The operation to apply
   * @param rightHand The other hand of the operation
   *
   */
  public BaseNumber op(Operation o, BaseNumber rightHand) {
    return o.op(this, (RealNumber) rightHand);
  }

  @Override
  public BaseNumber function(Function f) {
    if (!(f instanceof UnaryFunction)) {
      throw new IllegalArgumentException("Expected a unary function.");
    }
    return ((UnaryFunction) f).function(this);
  }

  @Override
  public BaseNumber function(Function f, BaseNumber rightHand) {
    if (!(f instanceof BinaryFunction)) {
      throw new IllegalArgumentException("Expected a binary function.");
    }
    return ((BinaryFunction) f).function(this, (RealNumber) rightHand);
  }

  /**
   * determine the sign of the RealNumber
   *
   * @return 1 if positive, -1 if negative, 0 in the other cases
   */
  public int sign() {
    int sign;
    if (!this.special) {
      sign = this.value.compareTo(BigDecimal.ZERO);
    } else if (this.specialValue == SpecialNumber.PositiveInfinity) {
      sign = 1;
    } else if (this.specialValue == SpecialNumber.NegativeInfinity) {
      sign = -1;
    } else {
      sign = 0;
    }

    return sign;
  }

  public static void setScale(int scale) {
    RealNumber.scale = scale;
  }

  @Override
  public BaseNumber negate() {
    RealNumber result;
    if (this.special) {
      result = negateSpecial();
    } else {
      result = new RealNumber(this.value.negate());
    }
    return result;
  }

  public RealNumber negateSpecial() {
    RealNumber result;
    if (this.specialValue == SpecialNumber.PositiveInfinity) {
      result = new RealNumber(SpecialNumber.NegativeInfinity);
    } else if (this.specialValue == SpecialNumber.NegativeInfinity) {
      result = new RealNumber(SpecialNumber.PositiveInfinity);
    } else {
      result = new RealNumber(SpecialNumber.NaN);
    }
    return result;
  }

  /**
   * Two RealNumber expressions are equal if the values they contain are equal
   *
   * @param o The object to compare to
   * @return A boolean representing the result of the equality test
   */
  @Override
  public boolean equals(Object o) {
    // No object should be equal to null (not including this check can result in an
    // exception if a RealNumber is tested against null)
    if (o == null)
      return false;

    // If the object is compared to itself then return true
    if (o == this) {
      return true;
    }

    // If the object is of another type then return false
    if (!(o instanceof RealNumber)) {
      return false;
    }

    if (this.isSpecial()) {
      return this.specialValue.equals(((RealNumber) o).getSpecialValue());
    }

    return this.value.compareTo(((RealNumber) o).getValue()) == 0;
  }

  /**
   * The method hashCode needs to be overridden if the equals method is
   * overridden;
   * otherwise there may be problems when you use your object in hashed
   * collections
   * such as HashMap, HashSet, LinkedHashSet.
   *
   * @return The result of computing the hash.
   */
  @Override
  public int hashCode() {
    return this.value.hashCode();
  }

  @Override
  public String toString() {
    String s = (!isSpecial()) ? value.toString() : specialValue.toString();
    return s;
  }

}
