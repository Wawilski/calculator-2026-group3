package calculator.cli.commands;

import calculator.cli.CommandResult;
import calculator.cli.CliContext;

public interface CliCommand {

  String name();

  String description();

  CommandResult execute(CliContext context, String rawInput);
}
