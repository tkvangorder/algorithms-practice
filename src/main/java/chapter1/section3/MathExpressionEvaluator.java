package chapter1.section3;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 * This class will take in a set of mathematical expressions in "infix" format and evaluate each
 * expression using operator precedence and also allow for Parentheses. Each token must be separated with a
 * space as this class focuses on the algorithm and not parsing robustness.
 *
 * This class uses Dijkstra's shunting algorithm via two stacks.
 */
public class MathExpressionEvaluator {

	private static Map<String, Operator> tokenToOperatorMap = null;
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {

		//Read in a list of expressions from the file, one expression for each line.
		In input = new In(Exercise4BalancedParams.class, "mathExpressions.txt");
		String[] expressions = input.readAllLines();
		for (String expression : expressions) {
			validateExpression(expression);
		}
	}

	private static void validateExpression(String expression) {
		Stack<Operator> operatorStack = new Stack<>();
		Stack<Double> operandStack = new Stack<>();
		logger.info("Evaulating the expression : " + expression);

		String tokens[] = expression.split(" ");

		for (String token : tokens) {
			Operator operator = tokenToOperator(token);
			if (operator == null) {
				//Token is not an operator, parse it to a number and push it on the operand stack.
				try {
					operandStack.push(Double.parseDouble(token));
				} catch (NumberFormatException exception) {
					logger.error(" - The expression is not valid because the operand [" + token + "] could not be parsed to a number.");
					return;
				}
				continue;
			}
			while (true) {
				if (operatorStack.isEmpty() || operator == Operator.LEFT_PAREN || operator.getPrecedence() > operatorStack.peek().getPrecedence()) {
					//if the operator stack is empty or the current precedence is greater than the last operator on the stack, push the operator,
					//we are done.
					operatorStack.push(operator);
					break;
				}
				//pop the previous operation.
				Operator previous = operatorStack.pop();

				if (previous == Operator.LEFT_PAREN) {
					//left paren, means we can stop.
					break;
				}

				Double rightOperand = operandStack.isEmpty()?null:operandStack.pop();
				Double leftOperand = operandStack.isEmpty()?null:operandStack.pop();
				if (rightOperand == null || leftOperand == null) {
					logger.error(" - The expression is not valid.");
					return;
				}
				operandStack.push(evaluate(leftOperand, rightOperand, previous));
			}
		}

		//Now evaluate what is left in the operator/operand stacks.
		while (!operatorStack.isEmpty()) {
			Operator operator = operatorStack.pop();
			Double rightOperand = operandStack.isEmpty()?null:operandStack.pop();
			Double leftOperand = operandStack.isEmpty()?null:operandStack.pop();
			if (rightOperand == null || leftOperand == null) {
				logger.error(" - The expression is not valid.");
				return;
			}
			operandStack.push(evaluate(leftOperand, rightOperand, operator));
		}

		logger.info(" - Final Expression Result = " + operandStack.pop());
	}

	/**
	 * This method will simply apply the operator to the left and right operand and return the result.
	 * This method will throw an "operator not supported" if an operator has been added to the enumeration but has
	 * not been implemented in the eval.
	 *
	 * @param leftOperand left side operand.
	 * @param rightOperand right side operand.
	 * @param operator The operator to perform.
	 * @return The result of the sub-expression.
	 */
	private static Double evaluate(Double leftOperand, Double rightOperand, Operator operator) {

		Double result = null;
		switch (operator) {
		case ADD:
			result = leftOperand + rightOperand;
			break;
		case SUBTRACT:
			result = leftOperand - rightOperand;
			break;
		case DIVIDE:
			result = leftOperand / rightOperand;
			break;
		case MULTIPLY:
			result = leftOperand * rightOperand;
			break;
		default:
			throw new IllegalArgumentException("The operator '" + operator.getToken() + "' is not supported.");
		}
		logger.info(" - Evaluating subexpression : [" + leftOperand + " " + operator.token + " " + rightOperand + "] = " + result);
		return result;
	}

	private static Operator tokenToOperator(String token) {
		if (tokenToOperatorMap == null) {
			tokenToOperatorMap = new HashMap<>(Operator.values().length);
			for (Operator operator : Operator.values()) {
				tokenToOperatorMap.put(operator.getToken(), operator);
			}
		}
		return tokenToOperatorMap.get(token);
	}

	/**
	 * An enumeration to represent the valid operators.
	 */
	private static enum Operator {

		//We always force a left paren onto the operator stack.
		LEFT_PAREN("(", 0),
		//We always force a right paren to evaluate previous expressions up to the left paren.
		RIGHT_PAREN(")", 0),
		ADD("+", 1),
		SUBTRACT("-", 1),
		MULTIPLY("*", 2),
		DIVIDE("/", 2);

		private final String token;
		private final int precedence;
		private Operator(String token, int precedence) {
			this.token = token;
			this.precedence = precedence;
		}

		public String getToken() {
			return token;
		}
		public int getPrecedence() {
			return precedence;
		}
	}
}
