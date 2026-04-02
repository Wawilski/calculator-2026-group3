package calculator;

import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;

/**
 * Unary mathematical function.
 */
public abstract class UnaryFunction extends Function {

  protected UnaryFunction(List<Expression> elist) throws IllegalConstruction {
    super(elist, 1);
  }

  public abstract int function(int value);

  public abstract BaseNumber function(IntegerNumber value);

  public abstract BaseNumber function(RationalNumber value);

  public abstract BaseNumber function(RealNumber value);

  public abstract BaseNumber function(ComplexNumber value);
}
