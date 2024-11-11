package de.menger.calculator.advancedCalc;

import de.menger.calculator.abstractCalc.CalcPane;
import javafx.scene.control.Button;

import java.util.function.BiConsumer;

public class AdvancedCalcPane extends CalcPane {

    private double memory;
    private Button memoryButton;

    public AdvancedCalcPane(BiConsumer<Button, CalcPane> modeSwitcher) {
        super(new String[][] {
                {"(", ")", "mc", "m+", "m-", "mr", "⌫", "C", "%", "÷"},
                {"^2", "^3", "^", "e^", "10^", "2^", "7", "8", "9", "×"},
                {"2√", "3√", "√", "ln", "lg", "log", "4", "5", "6", "-"},
                {"x!", "sin", "cos", "tan", "e", "2log", "1", "2", "3", "+"},
                {"Rad", "asin", "acos", "atan", "π", "Rand", "M", "0", ".", "="}
        }, modeSwitcher);
        for (Button button : buttons) {
            button.setPrefWidth(50);
        }
        this.memory = 0;
        this.calc = new AdvancedCalc();
    }

    @Override
    protected void setButtonListeners(Button button) {
        switch (button.getText()) {
            case "Rand" -> button.setOnAction(e -> editWorkingField(String.valueOf(Math.random())));
            case "Rad" -> button.setOnAction(e -> {
                if (button.getText().equals("Rad")) {
                    ((AdvancedCalc) calc).setUseDegrees(false);
                    button.setText("Deg");
                } else {
                    ((AdvancedCalc) calc).setUseDegrees(true);
                    button.setText("Rad");
                }
            });
            case "mc" -> button.setOnAction(e -> {
                memory = 0;
                memoryButton.getStyleClass().remove("active-button");
            });
            case "m+" -> button.setOnAction(e -> {
                memoryButton.getStyleClass().add("active-button");
                calculate();
                try {
                    memory += Double.parseDouble(this.workingField.getText());
                } catch (NumberFormatException ex) {
                    memory = 0;
                }
            });
            case "m-" -> button.setOnAction(e -> {
                memoryButton.getStyleClass().add("active-button");
                calculate();
                try {
                    memory -= Double.parseDouble(this.workingField.getText());
                } catch (NumberFormatException ex) {
                    memory = 0;
                }
            });
            case "mr" -> {
                memoryButton = button;
                button.setOnAction(e -> this.editWorkingField(String.valueOf(memory)));
            }
            default -> super.setButtonListeners(button);
        }
    }
}
