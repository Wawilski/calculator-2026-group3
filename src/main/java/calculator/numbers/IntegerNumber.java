
package calculator.numbers;

import calculator.Operation;
import visitor.Visitor;
import calculator.numbers.visitor.*;

/**
 * IntegerNumber is a concrete class that represents the integer type numbers.
 *
 * @see BaseNumber
 */
public class IntegerNumber implements BaseNumber {

  private int value;

  /**
   * getter method to obtain the value contained in the object
   *
   * @return The integer number contained in the object
   */
  public int getValue() {
    return value;
  }

  /**
   * Constructor method
   *
   * @param v The integer value to be contained in the object
   */
  public /* constructor */ IntegerNumber(int v) {
    this.value = v;
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
   * into another type of number (RealNumber, RationnalNumber, ComplexNumber) or
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
   * apply an operation between this and another IntegerNumber
   *
   * @param o         The operation to apply
   * @param rightHand The other hand of the operation
   *
   */
  public BaseNumber op(Operation o, BaseNumber rightHand) {
    return o.op(this, (IntegerNumber) rightHand);
  }

  /**
   * two IntegerNumber expressions are equal if the values they contain are equal
   *
   * @param o The object to compare to
   * @return A boolean representing the result of the equality test
   */
  @Override
  public boolean equals(Object o) {
    // No object should be equal to null (not including this check can result in an
    // exception if a IntegerNumber is tested against null)
    if (o == null)
      return false;

    // If the object is compared to itself then return true
    if (o == this) {
      return true;
    }

    // If the object is of another type then return false
    if (!(o instanceof IntegerNumber)) {
      return false;
    }
    return this.value == ((IntegerNumber) o).getValue();

    // Used == since the contained value is a primitive value
    // If it had been a Java object, .equals() would be needed
  }

  /**
   * The method hashCode needs to be overridden it the equals method is
   * overridden;
   * otherwise there may be problems when you use your object in hashed
   * collections
   * such as HashMap, HashSet, LinkedHashSet.
   *
   * @return The result of computing the hash.
   */
  @Override
  public int hashCode() {
    return value;
  }

  @Override
  public String toString() {
    return ((Integer) value).toString();
  }
}
