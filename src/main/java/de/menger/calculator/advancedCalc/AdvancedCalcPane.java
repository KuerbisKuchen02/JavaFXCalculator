package de.menger.calculator.advancedCalc;

import de.menger.calculator.abstractCalc.CalcPane;
import javafx.scene.control.Button;

public class AdvancedCalcPane extends CalcPane {

    public AdvancedCalcPane() {
        super(new String[][] {
                {"(", ")", "mc", "m+", "m-", "mr", "⌫", "C", "%", "÷"},
                {"^2", "^3", "^", "e^", "10^", "2^", "7", "8", "9", "×"},
                {"2√", "3√", "√", "ln", "lg", "log", "4", "5", "6", "-"},
                {"x!", "sin", "cos", "tan", "e", "2log", "1", "2", "3", "+"},
                {"Rad", "asin", "acos", "atan", "π", "Rand", "M", "0", ".", "="}
        });
        for (Button button : buttons) {
            button.setPrefWidth(50);
        }
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
