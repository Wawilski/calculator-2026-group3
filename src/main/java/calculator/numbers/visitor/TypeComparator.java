
package calculator.numbers.visitor;

import calculator.numbers.*;

/**
 * TypeComparator
 */
public class TypeComparator extends TypeVisitor {

  private NumberType castType = NumberType.INTEGER;

  public NumberType getCastType() {
    return castType;
  }

  @Override
  public void visit(IntegerNumber i) {
    if (castType == NumberType.INTEGER) {
      castType = NumberType.INTEGER;
    }
  }

  @Override
  public void visit(RationalNumber r) {
    if (castType == NumberType.INTEGER) {
      castType = NumberType.RATIONAL;
    }
  }

  @Override
  public void visit(RealNumber r) {
    if (castType == NumberType.INTEGER || castType == NumberType.RATIONAL) {
      castType = NumberType.REAL;
    }

  }

  @Override
  public void visit(ComplexNumber r) {
    castType = NumberType.COMPLEX;
  }

}
