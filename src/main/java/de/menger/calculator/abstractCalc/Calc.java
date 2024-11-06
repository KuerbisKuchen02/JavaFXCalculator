package de.menger.calculator.abstractCalc;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public abstract class Calc {

    protected Parser parser;

    public abstract double eval(String expression);

    protected double eval() {
        ArrayList<Token> postfix = parser.shuntingYard();

        Stack<Double> stack = new Stack<>();
        for (Token t : postfix) {
            switch (t.getType()) {
                case OPERANT -> stack.push(Double.parseDouble(t.getValue()));
                case CONSTANT -> stack.push(calc(t.getValue(), null, null));
                case OPERATOR -> {
                    double op1, op2;
                    try {
                        op2 = stack.pop();
                        op1 = stack.pop();
                    } catch (EmptyStackException e) {
                        throw new IllegalArgumentException("Expression is not valid");
                    }
                    stack.push(calc(t.getValue(), op1, op2));
                }
                case FUNCTION -> {
                    double op1;
                    try {
                        op1 = stack.pop();
                    } catch (EmptyStackException e) {
                        throw new IllegalArgumentException("Expression is not valid");
                    }
                    stack.push(calc(t.getValue(), op1, null));
                }
            }
        }
        double result = stack.pop();
        assert stack.isEmpty();
        return result;
    }

    protected abstract double calc(String op, Double op1, Double op2);
}
