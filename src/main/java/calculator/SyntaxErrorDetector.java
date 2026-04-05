package calculator;

import org.antlr.v4.runtime.*;

import lombok.Getter;

@Getter
public class SyntaxErrorDetector extends BaseErrorListener {

  /**
   * -- GETTER --
   * getter method to obtain the value contained in the object
   *
   * @return The integer number contained in the object
   */
  private int errors;

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
      String msg, RecognitionException e) {
    errors++;
  }

}
