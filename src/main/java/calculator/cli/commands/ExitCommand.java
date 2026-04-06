package calculator.cli.commands;

import calculator.cli.CommandResult;
import calculator.cli.CliContext;

public class ExitCommand implements CliCommand {

  @Override
  public String name() {
    return "exit";
  }

  @Override
  public String description() {
    return "Quit CLI";
  }

  @Override
  public CommandResult execute(CliContext context, String rawInput) {
    context.stop();
    return CommandResult.ok("Thank you for using the calculator CLI. Goodbye!");
  }
}
