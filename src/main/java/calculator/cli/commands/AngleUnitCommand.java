
package calculator.cli.commands;

import calculator.ExpressionParser;
import calculator.cli.CliContext;
import calculator.cli.CommandResult;

/**
 * AngleUnitCommand
 */
public class AngleUnitCommand implements CliCommand {

  @Override
  public String name() {
    return "degree";
  }

  @Override
  public String description() {
    return "Set if angle unit should be in degree or radiant: true -> degree, false -> radians";
  }

  @Override
  public CommandResult execute(CliContext context, String rawInput) {
    CommandResult result;
    String[] degreeInput = rawInput.split("\\s+");

    if (degreeInput.length < 2) {
      result = CommandResult.userError("Too few arguments");
    } else if (degreeInput.length > 2) {
      result = CommandResult.userError("Too much arguments");
    } else if (!(degreeInput[1].equals("false") || degreeInput[1].equals("true"))) {
      result = CommandResult.userError("Incorrect input");
    } else {
      ExpressionParser.isAngleUnitDegree = Boolean.parseBoolean(degreeInput[1]);

      String value = (ExpressionParser.isAngleUnitDegree) ? "degree" : "randians";
      result = CommandResult.ok("Angle unit sets to " + value);
    }

    return result;
  }
}
