package calculator.cli;

import calculator.cli.commands.*;

import java.util.*;

public class CommandRegistry {

  private final Map<String, CliCommand> commands = new LinkedHashMap<>();

  public void register(CliCommand command) {
    commands.put(command.name().toLowerCase(), command);
  }

  public Optional<CliCommand> find(String name) {
    return Optional.ofNullable(commands.get(name.toLowerCase()));
  }

  public Collection<CliCommand> all() {
    return commands.values();
  }
}
