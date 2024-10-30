package de.menger.calculator.basicCalc;

import de.menger.calculator.abstractCalc.Lexer;
import de.menger.calculator.abstractCalc.Parser;
import de.menger.calculator.abstractCalc.Token;

public class BasicParser extends Parser {

    public BasicParser(Lexer input) {
        super(input);
    }

    @Override
    protected int getPrecedence(Token token) {
        char operator = token.getValue().charAt(0);

        switch (operator) {
            case '^' -> {
                return 4;
            }
            case '*', '/' -> {
                return 3;
            }
            case '+', '-' -> {
                return 2;
            }
            default -> {
                return -1;
            }
        }
    }

    @Override
    protected int getAssociativity(Token token) {
        char operator = token.getValue().charAt(0);

        switch (operator) {
            case '^' -> {
                return 1;
            }
            case '*', '/', '+', '-' -> {
                return 0;
            }
            default -> {
                return -1;
            }
        }
    }
}
