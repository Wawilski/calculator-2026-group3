
package calculator;

/**
 * Exception that will be used when an incorrectly constructed arithmetic
 * expression is encountered.
 */
public class InvalidTokenException extends RuntimeException {

  public InvalidTokenException(String message) {
    super(message);
  }

}
