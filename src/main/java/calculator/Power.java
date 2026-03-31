package calculator;

import java.util.List;

/** This class represents the power operation "x^y".
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
 * @see Ln
 * @see Log
 * @see Reciprocal
 * @see Exponential
 * @see PowerTwo
 * @see Abs
 */
public final class Power extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for power.
   *
   * @param elist The list of Expressions to apply power to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Power(List<Expression>,Notation)
   */
  public /*constructor*/ Power(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for power,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply power to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Power(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Power(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "^";
	neutral = 1;
  }

  /**
   * The actual computation of the power of two integers (l^r)
   * @param l The base integer
   * @param r The exponent integer
   * @return The integer that is the result of l to the power of r
   */
  public int op(int l, int r)
    { return (int) Math.pow(l, r); }
}
