package calculator;

/** This class represents the constant pi "π".
 * This class extends MyNumber to represent a constant mathematical value.
 * @see MyNumber
 * @see Expression
 */
public final class Pi extends MyNumber
{
  /**
   * Class constructor for the constant pi.
   * The value is set to an integer representation of pi (3).
   */
  public /*constructor*/ Pi() {
	super((int) Math.PI);
  }
}
