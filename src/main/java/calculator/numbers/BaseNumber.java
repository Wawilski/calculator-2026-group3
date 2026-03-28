package calculator.numbers;

import calculator.numbers.visitor.*;

import calculator.Expression;
import calculator.Operation;

public interface BaseNumber extends Expression {

  public void accept(TypeVisitor visitor);

  public BaseNumber op(Operation o, BaseNumber rightHand);

  public BaseNumber negate();
}
