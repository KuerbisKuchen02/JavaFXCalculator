package de.menger.calculator.advancedCalc;

import de.menger.calculator.abstractCalc.Token;
import de.menger.calculator.abstractCalc.TokenType;
import de.menger.calculator.basicCalc.BasicLexer;

public class AdvancedLexer extends BasicLexer {

    public AdvancedLexer(String expression) {
        super(expression);
    }

    @Override
    protected Token _nextToken(char c) {
        switch (c) {
            case '!' -> {
                consume();
                return new Token(TokenType.FUNCTION, "!");
            }
            case 'p' -> {
                match("pi");
                return new Token(TokenType.CONSTANT, "pi");
            }
            case 'e' -> {
                consume();
                return new Token(TokenType.CONSTANT, "e");
            }
            case 'r' -> {
                match("root");
                return new Token(TokenType.OPERATOR, "root");
            }
            case 's' -> {
                match("sin");
                return new Token(TokenType.FUNCTION, "sin");
            }
            case 'c' -> {
                match("cos");
                return new Token(TokenType.FUNCTION, "cos");
            }
            case 't' -> {
                match("tan");
                return new Token(TokenType.FUNCTION, "tan");
            }
            case 'a' -> {
                consume();
                switch (this.c) {
                    case 's' -> {
                        match("sin");
                        return new Token(TokenType.FUNCTION, "asin");
                    }
                    case 'c' -> {
                        match("cos");
                        return new Token(TokenType.FUNCTION, "acos");
                    }
                    case 't' -> {
                        match("tan");
                        return new Token(TokenType.FUNCTION, "atan");
                    }
                    default -> throw new RuntimeException("Invalid char: " + this.c);
                }
            }
            case 'l' -> {
                consume();
                switch (this.c) {
                    case 'n' -> {
                        match("n");
                        return new Token(TokenType.FUNCTION, "ln");
                    }
                    case 'd' -> {
                        match("d");
                        return new Token(TokenType.FUNCTION, "ld");
                    }
                    case 'o' -> {
                        match("og");
                        return new Token(TokenType.OPERATOR, "log");
                    }
                    default -> throw new RuntimeException("Invalid char: " + this.c);
                }
            }
            default -> {
                return super._nextToken(c);
            }
        }
    }

    private void match(String keyword) {
        for (char c : keyword.toCharArray()) {
            if (this.c == c) consume();
            else throw new RuntimeException("Invalid char: " + c);
        }
    }
}
