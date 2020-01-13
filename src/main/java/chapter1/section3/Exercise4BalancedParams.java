package chapter1.section3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Exercise4BalancedParams {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");
	public static void main(String[] args) {
		In input = new In(Exercise4BalancedParams.class, "exerciseMatchBraces.txt");
		String[] expressions = input.readAllStrings();
		for (String expression : expressions) {
			validateExpression(expression);
		}
	}
	private static void validateExpression(String expression) {
		logger.info("Validating expression : " + expression);
		if (expression.length() % 2 != 0) {
			logger.error("  Expression is not valid because the length is not even.");
			return;
		}
		Stack<LeftBraceHolder> braceStack = new Stack<>();
		char[] expressionTokens = expression.toCharArray();
		LeftBraceHolder holder = null;
		for (int index = 0; index < expressionTokens.length; index++) {
			char token = expressionTokens[index];
			switch (token) {
			case '(':
			case '[':
			case '{':
				braceStack.push(new LeftBraceHolder(token, index));
				break;
			case ')':
				if (braceStack.isEmpty()) {
					logger.error("  Expression is not valid. The right brace '" + token + "' at index " + index + " does not have a matching left brace." );
					return;
				}
				holder = braceStack.pop();
				if (holder.getBraceCharacter() != '(') {
					logger.error("  Expression is not valid. The left brace '" + holder.getBraceCharacter() + " at index " + holder.getIndex() + " does not match the right brace '" + token + "' at index " + index );
					return;
				}
				break;
			case ']':
				holder = braceStack.pop();
				if (holder.getBraceCharacter() != '[') {
					logger.error("  Expression is not valid. The left brace '" + holder.getBraceCharacter() + " at index " + holder.getIndex() + " does not match the right brace '" + token + "' at index " + index );
					return;
				}
				break;
			case '}':
				holder = braceStack.pop();
				if (holder.getBraceCharacter() != '{') {
					logger.error("  Expression is not valid. The left brace '" + holder.getBraceCharacter() + " at index " + holder.getIndex() + " does not match the right brace '" + token + "' at index " + index );
					return;
				}
				break;
			default:
				//Ignore all other characters
			}
		}
		if (braceStack.isEmpty()) {
			logger.info("  Expression is balanced.");
		} else {
			holder = braceStack.pop();
			logger.error("  Expression is not valid. The left brace '" + holder.getBraceCharacter() + " at index " + holder.getIndex() + " does not have a matching, closing brace.");
		}
	}
	private static class LeftBraceHolder {
		private final char braceCharacter;
		private final int index;
		private LeftBraceHolder(char braceCharacter, int index) {
			this.braceCharacter = braceCharacter;
			this.index = index;
		}
		private char getBraceCharacter() {
			return braceCharacter;
		}
		private int getIndex() {
			return index;
		}
	}
}