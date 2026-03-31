package calculator;

import java.util.List;

/** This class represents the natural logarithm operation "ln".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Minus
 * @see Times
 * @see Divides
 * @see Sqrt
 * @see Sin
 * @see Cos
 * @see Tan
 * @see Log
 * @see Reciprocal
 * @see Exponential
 * @see PowerTwo
 * @see Power
 * @see Abs
 */
public final class Ln extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for natural logarithm.
   *
   * @param elist The list of Expressions to apply natural logarithm to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Ln(List<Expression>,Notation)
   */
  public /*constructor*/ Ln(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for natural logarithm,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply natural logarithm to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Ln(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Ln(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "ln";
	neutral = 1;
  }

  /**
   * The actual computation of the natural logarithm of an integer
   * @param l The integer to compute the natural logarithm of
   * @param r Unused for unary operation
   * @return The integer that is the result of the natural logarithm
   */
  public int op(int l, int r)
    { return (int) Math.log(l); }
}
