package de.menger.calculator.basicCalc;

import de.menger.calculator.calc.Lexer;
import de.menger.calculator.calc.Token;
import de.menger.calculator.calc.TokenType;

public class ExpressionLexer extends Lexer {

    public ExpressionLexer(String expression) {
        super(expression);
    }

    @Override
    public Token nextToken() {
        while (c != EOF) {
            switch (c) {
                case ' ','\t','\n','\r' -> WS();

                case '+', '-', '*', '/', '^' -> {
                    String operator = String.valueOf(c);
                    consume();
                    return new Token(TokenType.OPERATOR, operator);
                }
                case '(' -> {
                    consume();
                    return new Token(TokenType.LBRACK, "(");
                }
                case ')' -> {
                    consume();
                    return new Token(TokenType.RBRACK, ")");
                }
                default -> {
                    if (Character.isDigit(c)) {
                        return OPERANT();
                    }
                    throw new RuntimeException("Invalid char: " + c);
                }
            }
        }
        return new Token(TokenType.EOF, "<EOF>");
    }

    private void WS() {
        while (c == ' ' || c == '\t' || c == '\n' || c == '\r')
            consume();
    }

    private Token OPERANT() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(c);
            consume();
        } while (Character.isDigit(c));
        return new Token(TokenType.OPERANT, buf.toString());
    }
}
