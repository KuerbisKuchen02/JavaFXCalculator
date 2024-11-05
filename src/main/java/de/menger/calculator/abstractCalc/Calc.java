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
                case OPERATOR -> {
                    double op1, op2;
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
        double result = stack.pop();
        assert stack.isEmpty();
        return result;
    }

    protected abstract double calc(char op, double op1, double op2);
}
