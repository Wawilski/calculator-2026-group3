package calculator.cli;

import calculator.Calculator;

import java.util.Scanner;

public class CalculatorCli {

    private Calculator calculator = new Calculator();
    private OuputWriter output;
    private InputReader input;

    public CalculatorCli(){
        this(new Calculator(), new ConsoleInputReader(new Scanner(System.in)), new ConsoleOutputWriter());

    }


    public CalculatorCli(Calculator calculator,InputReader input, OuputWriter output){
        this.calculator = calculator;
        this.input = input;
        this.output = output;
    }

    public void run(){
        Scanner scanner =  new Scanner(System.in);
        CliContext context = new CliContext(calculator);
        CommandRegistry registry = new CommandRegistry();
        registry.register(new ExitCommand());
        registry.register(new DemoCommand());
        registry.register(new HelpCommand(registry));
        output.println("Welcome to calculator cli, type 'help' to see available commands");
        while (context.isRunning()){
            output.print("calculator> ");
            String line = input.readLine().trim();
            if (line.isEmpty()) continue;

            String commandName = line.split("\\s+")[0];
            CommandResult result = registry.find(commandName)
                    .map(command -> {
                    try {
                        return command.execute(context, line);
                    }
                    catch (Exception e){
                        return CommandResult.systemError("Error executing command : " + e.getMessage());
                    }
        })             .orElse(CommandResult.userError("Unknown command : " + commandName + ". " + "Type 'help' ."));

        if (result.message() != null){
            output.println(result.message());
        }
    }}

    public static void main(String[] args) {
        new CalculatorCli().run();
    }
}
