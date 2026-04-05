
package calculator.cli.commands;

import calculator.cli.CommandResult;
import calculator.numbers.RealNumber;

import calculator.cli.CliContext;

public class PrecisionCommand implements CliCommand {

  @Override
  public String name() {
    return "set-precision";
  }

  @Override
  public String description() {
    return "To choose the significant amount of decimal number";
  }

  @Override
  public CommandResult execute(CliContext context, String rawInput) {
    String[] scale = rawInput.split("\\s+");
    CommandResult result;

    if (scale.length < 2) {
      result = CommandResult.userError("Too few arguments");
    } else if (scale.length > 2) {
      result = CommandResult.userError("Too much arguments");
    } else {
      try {
        int intScale = Integer.valueOf(scale[1]);
        if (intScale < 0) {
          throw new Exception();
        }
        RealNumber.setScale(Integer.valueOf(scale[1]));
        result = CommandResult.ok("Precision set to " + scale[1]);
      } catch (Exception e) {
        result = CommandResult.userError("Incorrect scale value");
      }
    }

    return result;
  }
}
