package de.menger.calculator.calc;

public abstract class Parser {
	protected Lexer input;
	protected Token lookahead;
	
	public Parser(Lexer input) {
		this.input = input;
		consume();
	}
	
	public void match(TokenType x) {
		if(lookahead.type == x) {
			consume();
		} else {
			throw new RuntimeException("Parsing exception. Expected " + x + " but was " + lookahead.type);
		}
	}
	
	public void consume() {
		this.lookahead = input.nextToken();
	}
}
