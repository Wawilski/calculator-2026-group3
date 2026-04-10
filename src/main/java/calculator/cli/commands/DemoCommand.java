package calculator.cli.commands;

import calculator.cli.CommandResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    try {
      Path path = Paths.get("src/main/resources/demo.txt");
      return CommandResult.ok(Files.readString(path));
    } catch (IOException e) {
      return CommandResult.systemError("File not found");
    }
  }
}
