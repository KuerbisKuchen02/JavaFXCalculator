package de.menger.calculator.basicCalc;

import de.menger.calculator.abstractCalc.CalcPane;
import javafx.scene.control.Button;

public class BasicCalcPane extends CalcPane {

    public BasicCalcPane() {
        super(new String[][] {
                {"<-", "C", "^", "*"},
                {"7", "8", "9", "/"},
                {"4", "5", "6", "+"},
                {"1", "2", "3", "-"},
                {"Mode", "0", ".", "="}
        });
        this.calc = new BasicCalc();
    }

    @Override
    protected void setButtonListeners(Button button) {
        switch (button.getText()) {
            case "<-" -> button.setOnAction(event -> {
                if (!expression.isEmpty()) {
                    expression.deleteCharAt(expression.length() - 1);
                    textField.setText(expression.toString());
                }
            });
            case "C" -> button.setOnAction(event -> {
                expression = new StringBuilder();
                textField.setText("");
            });
            case "=" -> button.setOnAction(e -> {
                Double result = null;
                try {
                    result = calc.eval(expression.toString());
                } catch (IllegalArgumentException ex) {
                    textField.setText("Error");
                }
                expression = new StringBuilder();
                if (result != null) {
                    textField.setText(formatResult(result));
                    expression.append(formatExpression(result));
                }
            });
            default -> button.setOnAction(e -> {
                Button b = (Button) e.getSource();
                expression.append(b.getText());
                textField.setText(expression.toString());
            });
        }
    }
}
