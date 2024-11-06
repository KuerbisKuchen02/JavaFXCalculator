package de.menger.calculator.abstractCalc;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Parser {
	protected Lexer input;
	protected Token lookahead;
	
	public Parser(Lexer input) {
		this.input = input;
		consume();
	}

	public ArrayList<Token> shuntingYard() {
		Stack<Token> stack = new Stack<>();
		ArrayList<Token> postFixExp = new ArrayList<>();
		while (lookahead.getType() != TokenType.EOF) {
			if (lookahead.getType() == TokenType.OPERANT) {
				postFixExp.add(lookahead);
				match(TokenType.OPERANT);
			} else if (lookahead.getType() == TokenType.CONSTANT) {
				postFixExp.add(lookahead);
				match(TokenType.CONSTANT);
			} else if (lookahead.getType() == TokenType.FUNCTION) {
					stack.push(lookahead);
					match(TokenType.FUNCTION);
			} else if (lookahead.getType() == TokenType.OPERATOR) {
				while (!stack.isEmpty() && stack.peek().getType() != TokenType.LBRACK &&
						(getPrecedence(stack.peek()) > getPrecedence(lookahead)
								|| getPrecedence(lookahead) == getPrecedence(stack.peek()) && getAssociativity(lookahead) == 0)) {
					postFixExp.add(stack.pop());
				}
				stack.push(lookahead);
				match(TokenType.OPERATOR);
			} else if (lookahead.getType() == TokenType.LBRACK) {
				stack.push(lookahead);
				match(TokenType.LBRACK);
			} else if (lookahead.getType() == TokenType.RBRACK) {
				while (stack.peek().getType() != TokenType.LBRACK) {
					// if the stack runs out without finding a left parenthesis, then there are mismatched parentheses
					assert !stack.isEmpty();
					postFixExp.add(stack.pop());
				}
				assert stack.peek().getType() == TokenType.LBRACK;
				stack.pop();
				match(TokenType.RBRACK);
				if (stack.peek().getType() == TokenType.FUNCTION) {
					postFixExp.add(stack.pop());
				}
			}
		}
		while (!stack.isEmpty()) {
			// if a left parenthesis is left on the stack, then there are mismatched parentheses
			assert stack.peek().getType() != TokenType.LBRACK;
			postFixExp.add(stack.pop());
		}
		match(TokenType.EOF);
		System.out.println(postFixExp);
		return postFixExp;
	}

	protected abstract int getPrecedence(Token token);
	protected abstract int getAssociativity(Token token);

	private void match(TokenType x) {
		if(lookahead.type == x) {
			consume();
		} else {
			throw new RuntimeException("Parsing exception. Expected " + x + " but was " + lookahead.type);
		}
	}

	private void consume() {
		this.lookahead = input.nextToken();
	}
}
