package de.menger.calculator.basicCalc;

import de.menger.calculator.abstractCalc.Calc;

public class BasicCalc extends Calc {

    @Override
    public double eval(String expression) {
        this.parser = new BasicParser(new BasicLexer(expression));
        return super.eval();
    }

    @Override
    protected double calc(String operator, Double operand1, Double operand2) {
        switch (operator) {
            case "+" -> {
                return operand1 + operand2;
            }
            case "-" -> {
                return operand1 - operand2;
            }
            case "*" -> {
                return operand1 * operand2;
            }
            case "/" -> {
                return operand1 / operand2;
            }
            case "^" -> {
                return Math.pow(operand1, operand2);
            }
            default -> throw new RuntimeException("Unsupported operator: " + operator);
        }
    }
}

