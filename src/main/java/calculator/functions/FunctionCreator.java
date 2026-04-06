package calculator.functions;

import java.util.List;

import calculator.Expression;
import calculator.IllegalConstruction;

/**
 * FunctionCreator implements the simple Factory design pattern to create a
 * function given a certain function name
 *
 * @see Function
 */
public class FunctionCreator {

  public static Function createFunction(String fct, List<Expression> args) throws IllegalConstruction {
    Function function;
    try {
      switch (fct) {
        case "abs":
          function = new Abs(args);
          break;
        case "cos":
          function = new Cos(args);
          break;
        case "sin":
          function = new Sin(args);
          break;
        case "tan":
          function = new Tan(args);
          break;
        case "acos":
          function = new Acos(args);
          break;
        case "asin":
          function = new Asin(args);
          break;
        case "atan":
          function = new Atan(args);
          break;
        case "log":
          function = new Log(args);
          break;
        case "ln":
          function = new Ln(args);
          break;
        case "sqrt":
          function = new Sqrt(args);
          break;
        default:
          throw new IllegalConstruction();
      }

      return function;
    } catch (IllegalConstruction i) {
      throw i;
    }
  }
}
