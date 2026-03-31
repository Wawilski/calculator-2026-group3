package calculator;

import java.util.List;

/** This class represents the exponential operation "e^x".
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
 * @see PowerTwo
 * @see Power
 * @see Abs
 */
public final class Exponential extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for exponential.
   *
   * @param elist The list of Expressions to apply exponential to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Exponential(List<Expression>,Notation)
   */
  public /*constructor*/ Exponential(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for exponential,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply exponential to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Exponential(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Exponential(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "e^";
	neutral = 1;
  }

  /**
   * The actual computation of the exponential (e^x) of an integer
   * @param l The integer to compute the exponential of
   * @param r Unused for unary operation
   * @return The integer that is the result of the exponential
   */
  public int op(int l, int r)
    { return (int) Math.exp(l); }
}
