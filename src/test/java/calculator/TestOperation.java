package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

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
		assertEquals(2, o.countDepth());
	}

	@Test
	void testCountOps() {
		assertEquals(3, o.countOps());
	}

	@Test
	void testCountNbs() {
		assertEquals(Integer.valueOf(6), o.countNbs());
	}

	@Test
	void testDeepCompositeCountDepth() throws IllegalConstruction {
		Operation deep = buildDepthThreeExpression();
		assertEquals(3, deep.countDepth());
	}

	@Test
	void testDeepCompositeCountOps() throws IllegalConstruction {
		Operation deep = buildDepthThreeExpression();
		assertEquals(5, deep.countOps());
	}

	@Test
	void testDeepCompositeCountNbs() throws IllegalConstruction {
		Operation deep = buildDepthThreeExpression();
		assertEquals(6, deep.countNbs());
	}

	private Operation buildDepthThreeExpression() throws IllegalConstruction {
		Operation left = new Times(Arrays.asList(
				new Plus(Arrays.asList(new MyNumber(1), new MyNumber(2))),
				new Minus(Arrays.asList(new MyNumber(3), new MyNumber(4)))
		));
		Operation right = new Plus(Arrays.asList(new MyNumber(5), new MyNumber(6)));
		return new Divides(Arrays.asList(left, right));
	}

}
