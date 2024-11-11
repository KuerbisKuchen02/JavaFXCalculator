package de.menger.calculator.abstractCalc;


public abstract class Lexer {

	public static final char EOF = (char)-1;
	private final String input;
	private int p = 0;
	protected char c;
	protected char lc;
	
	public Lexer(String input) {
		this.input = input;
		lc = EOF;
		c = input.charAt(p);
	}
		
	protected void consume(){
		p++;
		lc = c;
		if(p >= input.length()) {
			c = EOF;
		} else {
			c = input.charAt(p);
		}
	}
	
	public abstract Token nextToken();
}
