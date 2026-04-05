package calculator.cli;

public class ExitCommand implements CliCommand{


    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String description() {
        return "Quitter le CLI";
    }

    @Override
    public CommandResult execute(CliContext context, String rawInput) {
        context.stop();
        return CommandResult.ok("Thank you for using the calculator CLI. Goodbye!");
    }
}
