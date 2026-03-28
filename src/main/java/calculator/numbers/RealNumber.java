package calculator.numbers;

import java.math.BigDecimal;

import calculator.Operation;
import visitor.Visitor;
import calculator.numbers.visitor.*;
import calculator.numbers.SpecialNumber;

/**
 * RealNumber is a concrete class that represents the real type numbers.
 *
 * @see BaseNumber
 */
public class RealNumber implements BaseNumber {

  /**
   * The value of the real number
   */
  private BigDecimal value;

  /**
   * A flag to determine if the number is a real number or a special value
   * (+INFINITY, -INFINITY, NaN)
   */
  private boolean special;
  /**
   * The special value of the number (+INFINITY,-INFINITY,NaN)
   */
  private SpecialNumber specialValue;

  public /* constructor */ RealNumber(BigDecimal value) {
    this.value = value;
    this.special = false;
    this.specialValue = null;
  }

  public /* constructor */ RealNumber(int value) {
    this.value = new BigDecimal(value);
    this.special = false;
    this.specialValue = null;
  }

  public /* constructor */ RealNumber(double value) {
    this.value = new BigDecimal(value);
    this.special = false;
    this.specialValue = null;
  }

  public /* constructor */ RealNumber(SpecialNumber specialValue) {
    this.value = null;
    this.special = true;
    this.specialValue = specialValue;
  }

  /**
   * getter method to obtain the value contained in the object
   *
   * @return The BigDecimal contained in the object
   */
  public BigDecimal getValue() {
    return value;
  }

  /**
   * getter method to obtain the special value contained in the object
   *
   * @return The BigDecimal contained in the object
   */
  public SpecialNumber getSpecialValue() {
    return specialValue;
  }

  /**
   * getter method to obtain the special flag value
   */
  public boolean isSpecial() {
    return special;
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
    return this.value.intValue();
  }

  @Override
  public String toString() {
    String s = (!isSpecial()) ? value.toString() : specialValue.toString();
    return s;
  }

}
