package de.menger.calculator.advancedCalc;

import de.menger.calculator.abstractCalc.CalcPane;
import javafx.scene.control.Button;

public class AdvancedCalcPane extends CalcPane {

    public AdvancedCalcPane() {
        super(new String[][] {
                {"(", ")", "%", "⌫", "C", "^", "÷"},
                {"root", "e", "pi", "7", "8", "9", "×"},
                {"ln", "ld", "log", "4", "5", "6", "-"},
                {"sin", "cos", "tan", "1", "2", "3", "+"},
                {"asin", "acos", "atan", "M", "0", ".", "="}
        });
        this.calc = new AdvancedCalc();
    }

    @Override
    protected void setButtonListeners(Button button) {
        switch (button.getText()) {
            case "" -> button.setOnAction(e -> System.out.println("Not Implemented!"));
            default -> super.setButtonListeners(button);
        }
    }
}
