package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import visitor.CountingVisitor;

class TestOperation {

	private Operation o;
	private Operation o2;

	@BeforeEach
	void setUp() throws Exception {
		List<Expression> params1 = Arrays.asList(new MyNumber(3), new MyNumber(4), new MyNumber(5));
		List<Expression> params2 = Arrays.asList(new MyNumber(5), new MyNumber(4));
		List<Expression> params3 = Arrays.asList(new Plus(params1), new Minus(params2), new MyNumber(7));
		o = new Divides(params3);
		o2 = new Divides(params3);
	}

	@Test
	void testEquals() {
		assertEquals(o,o2);
	}

	@Test
	void testCountDepth() {
		CountingVisitor visitor = new CountingVisitor();
		o.accept(visitor);
		assertEquals(2, visitor.getDepth());
	}

	@Test
	void testCountOps() {
		CountingVisitor visitor = new CountingVisitor();
		o.accept(visitor);
		assertEquals(3, visitor.getOpsCount());
	}

	@Test
	void testCountNbs() {
		CountingVisitor visitor = new CountingVisitor();
		o.accept(visitor);
		assertEquals(Integer.valueOf(6), visitor.getNumbersCount());
	}

	@Test
	void testArgsAreImmutableFromOutside() {
		assertThrows(UnsupportedOperationException.class, () -> o.getArgs().add(new MyNumber(10)));
	}

}
