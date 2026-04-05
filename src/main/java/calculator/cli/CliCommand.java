package calculator.cli;

public interface CliCommand {

    String name();
    String description();
    CommandResult execute(CliContext context, String rawInput);
}
