
package calculator.numbers.visitor;

import calculator.numbers.*;
import lombok.Getter;

/**
 * class helping cast between number
 */
@Getter
public class TypeComparator extends TypeVisitor {

  /**
   * Type to cast to, higher type in the current casting
   * with order Integer < Rational < Real < Complex
   * -- GETTER --
   *  getter method to obtain the casting type contained in the object
   *
   * @return cast type contained in the object

   */
  private NumberType castType = NumberType.INTEGER;

    /**
   * The Visitor can traverse an integer type number
   * and change the cast type to Integer if it is the higher type
   * (which is the case iff the cast type is already an integer)
   *
   * @param i the IntegerNumber visited
   */
  @Override
  public void visit(IntegerNumber i) {
    if (castType == NumberType.INTEGER) {
      castType = NumberType.INTEGER;
    }
  }

  /**
   * The Visitor can traverse an rational type number
   * and change the cast type to Rational if it is the higher type
   *
   * @param r the RationalNumber visited
   */
  @Override
  public void visit(RationalNumber r) {
    if (castType == NumberType.INTEGER) {
      castType = NumberType.RATIONAL;
    }
  }

  /**
   * The Visitor can traverse an real type number
   * and change the cast type to Real if it is the higher type
   *
   * @param r the RealNumber visited
   */
  @Override
  public void visit(RealNumber r) {
    if (castType == NumberType.INTEGER || castType == NumberType.RATIONAL) {
      castType = NumberType.REAL;
    }

  }

  /**
   * The Visitor can traverse an complex type number
   * and change the cast type to Complex if it is the higher type
   * (which is always the case)
   *
   * @param c the ComplexNumber visited
   */
  @Override
  public void visit(ComplexNumber c) {
    castType = NumberType.COMPLEX;
  }

}
