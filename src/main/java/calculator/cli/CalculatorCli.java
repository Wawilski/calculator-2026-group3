package calculator.cli;

import calculator.Calculator;
import calculator.ExpressionParser;
import calculator.cli.commands.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CalculatorCli {

  private Calculator calculator = new Calculator();
  private ExpressionParser parser = new ExpressionParser();
  private OuputWriter output;
  private InputReader input;

  public CalculatorCli() {
    this(new Calculator(), new ConsoleInputReader(new Scanner(System.in)), new ConsoleOutputWriter());

  }

  public CalculatorCli(Calculator calculator, InputReader input, OuputWriter output) {
    this.calculator = calculator;
    this.input = input;
    this.output = output;
  }

  public void greet() {

    try {
      File file = new File("src/main/resources/greetings.txt");
      Scanner greeter = new Scanner(file);
      while (greeter.hasNextLine()) {
        output.println(greeter.nextLine());
      }
      greeter.close();
    } catch (IOException e) {
      System.out.println("File not found");
    }
  }

  public void run() {
    CliContext context = new CliContext(calculator);

    CommandRegistry registry = new CommandRegistry();
    registry.register(new ExitCommand());
    registry.register(new DemoCommand());
    registry.register(new PrecisionCommand());
    registry.register(new HelpCommand(registry));

    greet();

    while (context.isRunning()) {
      output.print("calculator> ");
      String rawline = input.readLine();
      String line = rawline.trim();
      if (line.isEmpty())
        continue;

      String commandName = rawline.split("\\s+")[0];

      CliCommand command = registry.find(commandName).map(cmd -> {
        return cmd;
      }).orElse(new InputCommand(parser));

      CommandResult result = command.execute(context, line);
      output.println(result.message());

    }
  }

  public static void main(String[] args) {
    new CalculatorCli().run();
  }
}
