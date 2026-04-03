package visitor;

import calculator.Expression;
import calculator.Operation;
import calculator.numbers.RealNumber;
import calculator.numbers.visitor.TypeCaster;
import calculator.numbers.visitor.TypeComparator;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;

import java.util.ArrayList;

/**
 * Evaluation is a concrete visitor that serves to
 * compute and evaluate the results of arithmetic expressions.
 */
public class Evaluator extends Visitor {

  /** Default constructor of the class. Does not initialise anything. */
  public Evaluator() {
  }

  /** The result of the evaluation will be stored in this private variable */
  private BaseNumber computedValue;

  /**
   * getter method to obtain the result of the evaluation
   *
   * @return an Integer object containing the result of the evaluation
   */
  public BaseNumber getResult() {
    return computedValue;
  }

  public void visit(RealNumber r) {
    computedValue = r;
  }

  @Override
  public void visit(IntegerNumber i) {
    computedValue = i;
  }

  @Override
  public void visit(RationalNumber r) {
    computedValue = r;
  }

  @Override
  public void visit(ComplexNumber c) {
    computedValue = c;
  }

  /**
   * Use the visitor design pattern to visit an operation
   *
   * @param o The operation being visited
   */
  public void visit(Operation o) {
    ArrayList<BaseNumber> evaluatedArgs = new ArrayList<>();
    // first loop to recursively evaluate each subexpression
    for (Expression a : o.getArgs()) {
      a.accept(this);
      evaluatedArgs.add(computedValue);
    }
    if (evaluatedArgs.isEmpty()) {
      computedValue = new IntegerNumber(o.getNeutral());
      return;
    }
    // second loop to accumulate all the evaluated subresults
    BaseNumber temp = evaluatedArgs.get(0);
    int max = evaluatedArgs.size();
    TypeComparator comparator = new TypeComparator();
    temp.accept(comparator);

    for (int counter = 1; counter < max; counter++) {

      BaseNumber rightHand = evaluatedArgs.get(counter);
      rightHand.accept(comparator);

      rightHand.accept(comparator);

      TypeCaster caster = new TypeCaster(comparator.getCastType());

      temp.accept(caster);
      temp = caster.getResult();

      rightHand.accept(caster);
      rightHand = caster.getResult();

      temp = temp.op(o, rightHand);
      temp.accept(comparator);
    }
    // store the accumulated result
    computedValue = temp;
  }
}
