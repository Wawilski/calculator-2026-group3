package calculator.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.rmi.registry.Registry;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import calculator.Calculator;
import calculator.ExpressionParser;
import calculator.cli.commands.AngleUnitCommand;
import calculator.cli.commands.DemoCommand;
import calculator.cli.commands.ExitCommand;
import calculator.cli.commands.HelpCommand;
import calculator.cli.commands.InputCommand;
import calculator.cli.commands.PrecisionCommand;
import calculator.numbers.RealNumber;

/**
 * TestCommands
 */
public class TestCommands {

  private CliContext context = new CliContext(new Calculator());

  @AfterEach
  void setUp() {
    RealNumber.setScale(16);
    ExpressionParser.isAngleUnitDegree = false;
  }

  @Test
  void testPrecisionCommand() {
    PrecisionCommand precisionCmd = new PrecisionCommand();

    assertEquals("To choose the significant amount of decimal number",
        precisionCmd.description());

    precisionCmd.execute(context, "set-precision 5");
    assertEquals(RealNumber.getScale(), 5);
    precisionCmd.execute(context, "set-precision 3");
    assertEquals(RealNumber.getScale(), 3);

    assertEquals(CommandResult.userError("Too few arguments"),
        precisionCmd.execute(context, "set-precision"));
    assertEquals(CommandResult.userError("Too much arguments"),
        precisionCmd.execute(context, "set-precision this is a long input"));
    assertEquals(CommandResult.userError("Incorrect scale value"),
        precisionCmd.execute(context, "set-precision wrong"));
  }

  @Test
  void testAngleUnitCommand() {
    AngleUnitCommand angleCmd = new AngleUnitCommand();

    assertEquals("Set if angle unit should be in degree or radiant: true -> degree, false -> radians",
        angleCmd.description());

    angleCmd.execute(context, "degree true");
    assertTrue(ExpressionParser.isAngleUnitDegree);
    angleCmd.execute(context, "degree false");
    assertFalse(ExpressionParser.isAngleUnitDegree);

    assertEquals(CommandResult.userError("Too few arguments"),
        angleCmd.execute(context, "degree"));
    assertEquals(CommandResult.userError("Too much arguments"),
        angleCmd.execute(context, "degree this is a long input"));
    assertEquals(CommandResult.userError("Incorrect input"),
        angleCmd.execute(context, "degree wrong"));

  }

  @Test
  void testHelpCommand() {

    CommandRegistry registry = new CommandRegistry();
    registry.register(new ExitCommand());
    registry.register(new DemoCommand());

    String demo = "Available commands : \n- exit: Quit CLI\n\n- demo: Start of demo...\n\n";
    HelpCommand helpCmd = new HelpCommand(registry);
    assertEquals("this is the available command", helpCmd.description());
    assertEquals(CommandResult.ok(demo.trim()), helpCmd.execute(context, "help"));
  }

  @Test
  void testDemoCommand() {
    DemoCommand demo = new DemoCommand();

    assertNotEquals(CommandResult.systemError("File not found"), demo.execute(context, "demo"));
  }

  @Test
  void testInputCommand() {
    InputCommand input = new InputCommand(new ExpressionParser());

    assertEquals("This is an input", input.description());
    assertEquals(CommandResult.ok("5"), input.execute(context, "2+3"));
  }

}
