package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Asin;
import calculator.functions.Function;
import calculator.functions.Sin;
import calculator.numbers.BaseNumber;
import calculator.numbers.IntegerNumber;
import visitor.CountingVisitor;

class TestFunction {

  private static final class DummyFunction extends Function {
    DummyFunction(List<Expression> elist) throws IllegalConstruction {
      super(elist, 1);
      symbol = "dummy";
      neutral = 42;
    }
  }

  private static final class ZeroArityFunction extends Function {
    ZeroArityFunction() throws IllegalConstruction {
      super(List.of(), 0);
      symbol = "zero";
      neutral = 0;
    }
  }

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testFunctionNotation() throws IllegalConstruction {
    Expression e = new Sin(List.of(new IntegerNumber(8)));

    assertEquals("sin(8)", calc.format(e, Notation.PREFIX));
    assertEquals("sin(8)", calc.format(e, Notation.INFIX));
    assertEquals("(8) sin", calc.format(e, Notation.POSTFIX));

    Expression asin = new Asin(List.of(new IntegerNumber(8)));
    assertEquals("asin(8)", calc.format(asin, Notation.PREFIX));
    assertEquals("asin(8)", calc.format(asin, Notation.INFIX));
    assertEquals("(8) asin", calc.format(asin, Notation.POSTFIX));
  }

  @Test
  void testFunctionCountedAsOperation() throws IllegalConstruction {
    Expression nested = new Plus(Arrays.asList(
        new Sin(List.of(new IntegerNumber(3))),
        new IntegerNumber(2)));

    CountingVisitor visitor = new CountingVisitor();
    nested.accept(visitor);

    assertEquals(2, visitor.getOpsCount());
  }

  @Test
  void testEmptyFunctionReturnsNeutral() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Sin(new ArrayList<>()));
    assertEquals(new IntegerNumber(0), result);
  }

  @Test
  void testFunctionBaseContractCoverage() throws IllegalConstruction {
    DummyFunction function = new DummyFunction(List.of(new IntegerNumber(7)));
    DummyFunction same = new DummyFunction(List.of(new IntegerNumber(7)));

    assertEquals(1, function.getArity());
    assertEquals("dummy", function.getSymbol());
    assertEquals(42, function.getNeutral());
    assertEquals(1, function.getArgs().size());
    assertTrue(function.toString().contains("dummy"));

    assertTrue(function.equals(same));
    assertFalse(function.equals(new Sin(List.of(new IntegerNumber(7)))));
    assertTrue(function.hashCode() != 0);
    assertThrows(UnsupportedOperationException.class, () -> function.getArgs().add(new IntegerNumber(9)));
  }

  @Test
  void testFunctionConstructorValidation() {
    assertThrows(IllegalConstruction.class, () -> new DummyFunction(null));
    assertThrows(IllegalConstruction.class,
        () -> new DummyFunction(Arrays.asList(new IntegerNumber(1), new IntegerNumber(2))));
    assertThrows(IllegalConstruction.class, ZeroArityFunction::new);
  }

  @Test
  void testFunctionEqualsNullAndSelf() throws IllegalConstruction {
    DummyFunction function = new DummyFunction(List.of(new IntegerNumber(7)));

    assertTrue(function.equals(function));
    assertFalse(function.equals(null));
  }
}