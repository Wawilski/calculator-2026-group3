package calculator.cli.commands;

import calculator.cli.CommandResult;
import calculator.cli.CliContext;

public class DemoCommand implements CliCommand {

  @Override
  public String name() {
    return "demo";
  }

  @Override
  public String description() {
    return "Start of demo...";
  }

  @Override
  public CommandResult execute(CliContext context, String rawInput) {
    return CommandResult.ok("This is a simple demo...");
  }
}
