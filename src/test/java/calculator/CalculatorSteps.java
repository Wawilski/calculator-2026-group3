package calculator;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import visitor.CountingVisitor;

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

	@Given("^the nested expression \\((\\d+) \\+ (\\d+)\\) \\* \\((\\d+) - (\\d+)\\)$")
	public void givenTheNestedExpression(int a, int b, int c1, int d) {
		try {
			Expression left = new Plus(List.of(new MyNumber(a), new MyNumber(b)));
			Expression right = new Minus(List.of(new MyNumber(c1), new MyNumber(d)));
			op = new Times(List.of(left, right));
		} catch (IllegalConstruction _) {
			fail();
		}
	}

	@Then("^its (PREFIX|POSTFIX|INFIX) notation is (.*)$")
	public void thenItsNotationIs(String notation, String s) {
		assertEquals(s, c.format(op, Notation.valueOf(notation)));
	}

	@Then("^it has depth (\\d+), (\\d+) operations and (\\d+) numbers$")
	public void thenItHasDepthOperationsAndNumbers(int depth, int operations, int numbers) {
		CountingVisitor visitor = new CountingVisitor();
		op.accept(visitor);
		assertEquals(depth, visitor.getDepth());
		assertEquals(operations, visitor.getOpsCount());
		assertEquals(numbers, visitor.getNumbersCount());
	}

	@Then("its pretty notation is {string}")
	public void thenItsPrettyNotationIs(String expected) {
		assertEquals(expected, c.prettyFormat(op));
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

	@Then("a division by zero error is raised")
	public void operation_displays_an_error(){
		try{
			visitor.Evaluator a = new visitor.Evaluator();
			op.accept(a);
			fail("A division by zero error was expected");
		}catch (ArithmeticException e){
			assertNotNull(e,"An ArithmeticException was expected");
			assertEquals("Division by zero is not allowed.", e.getMessage());
		}
	}

}
