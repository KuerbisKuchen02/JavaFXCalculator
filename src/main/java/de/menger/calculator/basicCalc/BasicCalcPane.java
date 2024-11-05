package de.menger.calculator.basicCalc;

import de.menger.calculator.abstractCalc.CalcPane;

public class BasicCalcPane extends CalcPane {

    public BasicCalcPane() {
        super(new String[][] {
                {"⌫", "C", "^", "÷"},
                {"7", "8", "9", "×"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"M", "0", ".", "="}
        });
        this.calc = new BasicCalc();
    }
}
