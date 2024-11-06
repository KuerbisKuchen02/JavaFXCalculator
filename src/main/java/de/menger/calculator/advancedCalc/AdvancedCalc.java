package de.menger.calculator.advancedCalc;

import de.menger.calculator.basicCalc.BasicCalc;

public class AdvancedCalc extends BasicCalc {

    @Override
    public double eval(String expression) {
        this.parser = new AdvancedParser(new AdvancedLexer(expression));
        return super.eval();
    }

    @Override
    protected double calc(String operator, Double operand1, Double operand2) {
        switch (operator) {
            case "e" -> {
                return Math.E;
            }
            case "pi" -> {
                return Math.PI;
            }
            case "!" -> {
                return factorial(operand1);
            }
            case "root" -> {
                return Math.pow(operand2, 1/operand1);
            }
            case "sin" -> {
                return Math.sin(operand1);
            }
            case "cos" -> {
                return Math.cos(operand1);
            }
            case "tan" -> {
                return Math.tan(operand1);
            }
            case "asin" -> {
                return Math.asin(operand1);
            }
            case "acos" -> {
                return Math.acos(operand1);
            }
            case "atan" -> {
                return Math.atan(operand1);
            }
            case "ln" -> {
                return Math.log(operand1);
            }
            case "ld" -> {
                return Math.log10(operand1);
            }
            // log_b(a) = ln(a) / ln(b)
            case "log" -> {
                return Math.log(operand2) / Math.log(operand1);
            }
            default -> {
                return super.calc(operator, operand1, operand2);
            }
        }
    }

    private double factorial(double n) {
        return n <= 1 ? 1 : n*factorial(n-1);
    }
}
