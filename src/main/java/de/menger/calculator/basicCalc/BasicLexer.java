package de.menger.calculator.basicCalc;

import de.menger.calculator.abstractCalc.Lexer;
import de.menger.calculator.abstractCalc.Token;
import de.menger.calculator.abstractCalc.TokenType;

public class BasicLexer extends Lexer {

    public BasicLexer(String expression) {
        super(expression);
    }

    @Override
    public Token nextToken() {
        while (c != EOF) {
            if (c == '-' && (lc == EOF || lc == '+' || lc == '-' || lc == '*' || lc == '/' || lc == '(')) {
                consume();
                lc = '$';
            }
            switch (c) {
                case ' ','\t','\n','\r' -> WS();
                default -> {
                    return _nextToken(c);
                }
            }
        }
        return new Token(TokenType.EOF, "<EOF>");
    }

    protected Token _nextToken(char c) {
        switch (c) {
            case '+', '-', '*', '/', '^', '%' -> {
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

    private void WS() {
        while (c == ' ' || c == '\t' || c == '\n' || c == '\r')
            consume();
    }

    private Token OPERANT() {
        StringBuilder buf = new StringBuilder();
        if (lc == '$') {
            buf.append('-');
        }
        do {
            buf.append(c);
            consume();
        } while (Character.isDigit(c) || c == '.');
        return new Token(TokenType.OPERANT, buf.toString());
    }
}
