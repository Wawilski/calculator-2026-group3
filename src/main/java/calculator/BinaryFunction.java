package calculator;

import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;

/**
 * Binary mathematical function.
 */
public abstract class BinaryFunction extends Function {

  protected BinaryFunction(List<Expression> elist) throws IllegalConstruction {
    super(elist, 2);
  }

  public abstract int function(int left, int right);

  public abstract BaseNumber function(IntegerNumber left, IntegerNumber right);

  public abstract BaseNumber function(RationalNumber left, RationalNumber right);

  public abstract BaseNumber function(RealNumber left, RealNumber right);

  public abstract BaseNumber function(ComplexNumber left, ComplexNumber right);
}
