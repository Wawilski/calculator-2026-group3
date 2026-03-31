package calculator;

import java.util.List;

/** This class represents the base 10 logarithm operation "log".
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
 * @see Reciprocal
 * @see Exponential
 * @see PowerTwo
 * @see Power
 * @see Abs
 */
public final class Log extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for base 10 logarithm.
   *
   * @param elist The list of Expressions to apply base 10 logarithm to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Log(List<Expression>,Notation)
   */
  public /*constructor*/ Log(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for base 10 logarithm,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply base 10 logarithm to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Log(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Log(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "log";
	neutral = 1;
  }

  /**
   * The actual computation of the base 10 logarithm of an integer
   * @param l The integer to compute the base 10 logarithm of
   * @param r Unused for unary operation
   * @return The integer that is the result of the base 10 logarithm
   */
  public int op(int l, int r)
    { return (int) Math.log10(l); }
}
