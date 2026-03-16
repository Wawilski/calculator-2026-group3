
package calculator.numbers.visitor;

import java.math.BigDecimal;
import calculator.numbers.*;

/**
 * TypeCaster
 */
public class TypeCaster extends TypeVisitor {

  private NumberType type;

  private BaseNumber result;

  public TypeCaster(NumberType type) {
    this.type = type;
  }

  public BaseNumber getResult() {
    return result;
  }

  @Override
  public void visit(RealNumber r) {
    switch (type) {
      case REAL:
        result = r;
        break;
      default:
        break;
    }
  }

  @Override
  public void visit(IntegerNumber i) {
    switch (type) {
      case INTEGER:
        result = i;
        break;
      case REAL:
        result = new RealNumber(new BigDecimal(i.getValue()));
        break;
      default:
        break;
    }
  }

}
