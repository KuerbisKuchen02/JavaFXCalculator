package de.menger.calculator.basicCalc;

import de.menger.calculator.calc.Lexer;
import de.menger.calculator.calc.Parser;
import de.menger.calculator.calc.Token;
import de.menger.calculator.calc.TokenType;

import java.util.ArrayList;
import java.util.Stack;

public class BasicParser extends Parser {

    public BasicParser(Lexer input) {
        super(input);
    }

    public ArrayList<Token> shuttingYard() {
        Stack<Token> stack = new Stack<>();
        ArrayList<Token> postFixExp = new ArrayList<>();
        while (lookahead.getType() != TokenType.EOF) {
            if (lookahead.getType() == TokenType.OPERANT) {
                postFixExp.add(lookahead);
                match(TokenType.OPERANT);
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
            }
        }
        while (!stack.isEmpty()) {
            // if a left parenthesis is left on the stack, then there are mismatched parentheses
            assert stack.peek().getType() != TokenType.LBRACK;
            postFixExp.add(stack.pop());
        }
        match(TokenType.EOF);
        return postFixExp;
    }

    private int getPrecedence(Token token) {
        return getPrecedence(token.getValue().charAt(0));
    }

    private int getPrecedence(char operator) {
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

    private int getAssociativity(Token token) {
        return getAssociativity(token.getValue().charAt(0));
    }

    private int getAssociativity(char operator) {
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
