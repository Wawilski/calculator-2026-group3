package calculator;

import java.util.List;

/** This class represents the sine operation "sin".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Minus
 * @see Times
 * @see Divides
 * @see Sqrt
 * @see Cos
 * @see Tan
 * @see Ln
 * @see Log
 * @see Reciprocal
 * @see Exponential
 * @see PowerTwo
 * @see Power
 * @see Abs
 */
public final class Sin extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for sine.
   *
   * @param elist The list of Expressions to apply sine to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Sin(List<Expression>,Notation)
   */
  public /*constructor*/ Sin(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for sine,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply sine to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Sin(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Sin(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "sin";
	neutral = 1;
  }

  /**
   * The actual computation of the sine of an integer (in radians)
   * @param l The integer to compute the sine of
   * @param r Unused for unary operation
   * @return The integer that is the result of the sine
   */
  public int op(int l, int r)
    { return (int) Math.sin(l); }
}
