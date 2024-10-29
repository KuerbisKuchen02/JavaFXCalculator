package de.menger.calculator.basicCalc;

import de.menger.calculator.calc.Calc;
import de.menger.calculator.calc.Token;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class BasicCalc extends Calc {
    public int eval(String expression) {
        BasicParser parser = new BasicParser(new ExpressionLexer(expression));
        ArrayList<Token> postfix = parser.shuttingYard();

        Stack<Integer> stack = new Stack<>();
        for (Token t : postfix) {
            switch (t.getType()) {
                case OPERANT -> stack.push(Integer.parseInt(t.getValue()));
                case OPERATOR -> {
                    int op1, op2;
                    try {
                        op2 = stack.pop();
                        op1 = stack.pop();
                    }
                    catch (EmptyStackException e) {
                        throw new IllegalArgumentException("Expression is not valid");
                    }
                    stack.push(calc(t.getValue().charAt(0), op1, op2));
                }
            }
        }
        int result = stack.pop();
        assert stack.isEmpty();
        return result;
    }

    private int calc(char operator, int operand1, int operand2) {
        switch (operator) {
            case '+' -> {
                return operand1 + operand2;
            }
            case '-' -> {
                return operand1 - operand2;
            }
            case '*' -> {
                return operand1 * operand2;
            }
            case '/' -> {
                return operand1 / operand2;
            }
            case '^' -> {
                return (int) Math.pow(operand1, operand2);
            }
            default -> throw new RuntimeException("Unsupported operator: " + operator);
        }
    }
}

