package de.menger.calculator.advancedCalc;

import de.menger.calculator.basicCalc.BasicCalc;

public class AdvancedCalc extends BasicCalc {

    private boolean useDegrees;

    public AdvancedCalc() {
        super();
        useDegrees = true;
    }

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
                double temp = useDegrees ? Math.toRadians(operand1) : operand1;
                return Math.sin(temp);
            }
            case "cos" -> {
                double temp = useDegrees ? Math.toRadians(operand1) : operand1;
                return Math.cos(temp);
            }
            case "tan" -> {
                double temp = useDegrees ? Math.toRadians(operand1) : operand1;
                return Math.tan(temp);
            }
            case "asin" -> {
                double temp = Math.asin(operand1);
                return useDegrees ? Math.toDegrees(temp) : temp;
            }
            case "acos" -> {
                double temp = Math.acos(operand1);
                return useDegrees ? Math.toDegrees(temp) : temp;
            }
            case "atan" -> {
                double temp = Math.atan(operand1);
                return useDegrees ? Math.toDegrees(temp) : temp;
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

    public void setUseDegrees(boolean useDegrees) {
        this.useDegrees = useDegrees;
    }
}
