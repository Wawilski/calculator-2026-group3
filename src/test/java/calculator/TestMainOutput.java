package calculator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * This test class checks that the main method of the calculator produces output
 * that contains the expected strings indicating that an expression has been evaluated.
 */
class TestMainOutput {

    @Test
    void testMainProducesCalculatorOutput() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            assertDoesNotThrow(() -> Main.main(new String[0]));
        } finally {
            System.setOut(originalOut);
        }
        String rendered = output.toString(StandardCharsets.UTF_8);
        assertTrue(rendered.contains("The result of evaluating expression"));
        assertTrue(rendered.contains("is:"));
    }
}
