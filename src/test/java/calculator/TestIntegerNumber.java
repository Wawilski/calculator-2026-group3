package calculator;

import calculator.numbers.*;
//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class TestIntegerNumber {

  private final int value = 8;
  private IntegerNumber number;

  @BeforeEach
  void setUp() {
    number = new IntegerNumber(value);
  }

  @Test
  void testEquals() {
    // Two distinct IntegerNumber, constructed separately (using a different
    // constructor) but containing the same value should be equal
    assertEquals(new IntegerNumber(value), number);
    // Two IntegerNumbers containing a distinct value should not be equal:
    int otherValue = 7;
    assertNotEquals(new IntegerNumber(otherValue), number);
    assertEquals(number, number); // Identity check (for coverage, as this should always be true)
    assertNotEquals(number, value); // number is of type IntegerNumber, while value is of type int, so not equal
    try {
      assertNotEquals(new Times(new ArrayList<>()), number);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testToString() {
    assertEquals(Integer.toString(value), number.toString());
  }

}
