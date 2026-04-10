
package calculator.cli.commands;

import calculator.cli.CommandResult;
import calculator.Expression;
import calculator.ExpressionParser;
import calculator.InvalidTokenException;
import calculator.cli.CliContext;

public class InputCommand implements CliCommand {

  private final ExpressionParser parser;

  public InputCommand(ExpressionParser parser) {
    this.parser = parser;
  }

  @Override
  public String name() {
    return "input";
  }

  @Override
  public String description() {
    return "This is an input";
  }

  @Override
  public CommandResult execute(CliContext context, String rawInput) {
    String solution;

    try {
      Expression expression = parser.parse(rawInput);
      solution = context.calculator().eval(expression).toString();
    } catch (InvalidTokenException i) {
      solution = i.getMessage();
    } catch (Exception e) {
      solution = e.getMessage();
    }

    return CommandResult.ok(solution);
  }
}
