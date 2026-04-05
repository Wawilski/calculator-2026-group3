package calculator.cli;

public class HelpCommand implements CliCommand{

    private final CommandRegistry registry;

    public HelpCommand(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "this is the available command";
    }

    @Override
    public CommandResult execute(CliContext context, String rawInput) {
        StringBuilder sb = new StringBuilder("Available commands : \n");
        for (CliCommand command : registry.all()) {
            sb.append("- ").append(command.name()).append(": ").append(command.description()).append('\n');

        }
        return CommandResult.ok(sb.toString().trim());
    }
}
