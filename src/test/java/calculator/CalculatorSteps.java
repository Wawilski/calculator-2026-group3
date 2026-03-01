package calculator;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorSteps {

	private ArrayList<Expression> params;
	private Operation op;
	private Calculator c;

	@Before
    public void resetMemoryBeforeEachScenario() {
		params = null;
		op = null;
	}

	@Given("I initialise a calculator")
	public void givenIInitialiseACalculator() {
		c = new Calculator();
	}

	@Given("an integer operation {string}")
	public void givenAnIntegerOperation(String s) {
		// Write code here that turns the phrase above into concrete actions
		params = new ArrayList<>(); // create an empty set of parameters to be filled in
		try {
			switch (s) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				default		->	fail();
			}
		} catch (IllegalConstruction _) {
			fail();
		}
	}

	// The following example shows how to use a DataTable provided as input.
	// The example looks slightly complex, since DataTables can take as input
	//  tables in two dimensions, i.e. rows and lines. This is why the input
	//  is a list of lists.
	@Given("the following list of integer numbers")
	public void givenTheFollowingListOfNumbers(List<List<String>> numbers) {
		params = new ArrayList<>();
		// Since we only use one line of input, we use get(0) to take the first line of the list,
		// which is a list of strings, that we will manually convert to integers:
		numbers.get(0).forEach(n -> params.add(new MyNumber(Integer.parseInt(n))));
	    params.forEach(n -> System.out.println("value ="+ n));
		op = null;
	}

	// The string in the Given annotation shows how to use regular expressions...
	// In this example, the notation d+ is used to represent numbers, i.e. nonempty sequences of digits
	@Given("^the sum of two numbers (\\d+) and (\\d+)$")
	// The alternative, and in this case simpler, notation would be:
	// @Given("the sum of two numbers {int} and {int}")
	public void givenTheSum(int n1, int n2) {
		try {
			params = new ArrayList<>();
		    params.add(new MyNumber(n1));
		    params.add(new MyNumber(n2));
		    op = new Plus(params);}
		catch(IllegalConstruction _) { fail(); }
	}

	@Given("a composite expression with mixed default notations")
	public void givenACompositeExpressionWithMixedDefaultNotations() {
		try {
			Operation plus = new Plus(Arrays.asList(new MyNumber(3), new MyNumber(4), new MyNumber(5)));
			Operation minus = new Minus(Arrays.asList(new MyNumber(5), new MyNumber(4)));
			plus.notation = Notation.INFIX;
			minus.notation = Notation.POSTFIX;
			op = new Divides(Arrays.asList(plus, minus, new MyNumber(7)));
		}
		catch(IllegalConstruction _) { fail(); }
	}

	@Given("a depth three composite expression")
	public void givenADepthThreeCompositeExpression() {
		try {
			Operation left = new Times(Arrays.asList(
					new Plus(Arrays.asList(new MyNumber(1), new MyNumber(2))),
					new Minus(Arrays.asList(new MyNumber(3), new MyNumber(4)))
			));
			Operation right = new Plus(Arrays.asList(new MyNumber(5), new MyNumber(6)));
			op = new Divides(Arrays.asList(left, right));
		}
		catch(IllegalConstruction _) { fail(); }
	}

	@Given("a composite expression where each child has a different default notation")
	public void givenACompositeExpressionWhereEachChildHasADifferentDefaultNotation() {
		try {
			Operation plus = new Plus(Arrays.asList(new MyNumber(1), new MyNumber(2)));
			Operation minus = new Minus(Arrays.asList(new MyNumber(8), new MyNumber(3)));
			Operation times = new Times(Arrays.asList(new MyNumber(2), new MyNumber(3)));
			plus.notation = Notation.PREFIX;
			minus.notation = Notation.INFIX;
			times.notation = Notation.POSTFIX;
			op = new Divides(Arrays.asList(plus, minus, times));
		}
		catch(IllegalConstruction _) { fail(); }
	}

	@Then("^its (.*) notation is (.*)$")
	public void thenItsNotationIs(String notation, String s) {
		if (notation.equals("PREFIX")||notation.equals("POSTFIX")||notation.equals("INFIX")) {
			op.notation = Notation.valueOf(notation);
			assertEquals(s, op.toString());
		}
		else fail(notation + " is not a correct notation! ");
	}

	@When("^I provide a (.*) number (\\d+)$")
	public void whenIProvideANumber(String s, int val) {
		//add extra parameter to the operation
		params = new ArrayList<>();
		params.add(new MyNumber(val));
		op.addMoreParams(params);
	}

	@Then("^the (.*) is (\\d+)$")
	public void thenTheOperationIs(String s, int val) {
		try {
			switch (s) {
				case "sum"			->	op = new Plus(params);
				case "product"		->	op = new Times(params);
				case "quotient"		->	op = new Divides(params);
				case "difference"	->	op = new Minus(params);
				default -> fail();
			}
			assertEquals(val, c.eval(op));
		} catch (IllegalConstruction _) {
			fail();
		}
	}

	@Then("the operation evaluates to {int}")
	public void thenTheOperationEvaluatesTo(int val) {
		assertEquals(val, c.eval(op));
	}

	@Then("its depth is {int}")
	public void thenItsDepthIs(int expectedDepth) {
		assertEquals(expectedDepth, op.countDepth());
	}

	@Then("it contains {int} operations")
	public void thenItContainsOperations(int expectedOps) {
		assertEquals(expectedOps, op.countOps());
	}

	@Then("it contains {int} numbers")
	public void thenItContainsNumbers(int expectedNumbers) {
		assertEquals(expectedNumbers, op.countNbs());
	}

}
