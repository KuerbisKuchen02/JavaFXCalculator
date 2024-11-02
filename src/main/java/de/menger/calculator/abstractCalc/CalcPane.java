package de.menger.calculator.abstractCalc;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public abstract class CalcPane extends VBox {

    protected StringBuilder expression;
    protected Calc calc;

    protected Button[] buttons;
    protected TextField workingField;
    protected TextField historyField;

    public CalcPane(String[][] buttonTexts) {
        super();
        this.historyField = new TextField();
        this.historyField.getStyleClass().add("history-field");
        historyField.setEditable(false);
        historyField.setOnMouseClicked(event -> {
            String temp = historyField.getText();
            clear();
            this.expression.append(temp);
            workingField.setText(temp);
        });

        this.workingField = new TextField();
        this.workingField.getStyleClass().add("working-field");
        workingField.setEditable(false);

        GridPane grid = new GridPane();
        grid.getStyleClass().add("button-grid");
        this.getChildren().addAll(historyField, workingField, grid);
        grid.setAlignment(Pos.CENTER);

        ArrayList<Button> buttonList = new ArrayList<>();

        for (int i = 0; i < buttonTexts.length; i++) {
            for (int j = 0; j < buttonTexts[i].length; j++) {
                Button button = new Button(buttonTexts[i][j]);
                setButtonListeners(button);
                buttonList.add(button);
                grid.add(button, j, i);
                if (i == 0) {
                    button.getStyleClass().add("top-button");
                }
                if (j == buttonTexts[i].length - 1) {
                    button.getStyleClass().add("operator-button");
                }
            }
        }

        this.buttons = buttonList.toArray(new Button[0]);
        clear();
    }

    public void calculate() {
        if (expression.length() < 3) {
            return;
        }
        Double result = null;
        try {
            result = calc.eval(expression.toString());
        } catch (IllegalArgumentException ex) {
            workingField.setText("Error");
        }
        historyField.setText(expression.toString());
        expression = new StringBuilder();
        if (result != null) {
            if (result.isInfinite()) {
                workingField.setText("Inf");
            } else if (result.isNaN()) {
                workingField.setText("NaN");
            } else {
                Double out = truncate(result);
                if (out.longValue() == out) {
                    String t = Long.toString(out.longValue());
                    workingField.setText(t);
                } else {
                    workingField.setText(Double.toString(out));
                }
            }
        }
    }

    public static double truncate(double value) {
        BigDecimal b = new BigDecimal(String.valueOf(value));

        MathContext context = new MathContext(10);
        if (value > 0 && value < 1) {
            b = b.add(BigDecimal.ONE);
            b = b.round(context);
            b = b.subtract(BigDecimal.ONE);
        } else if (value < 0 && value > -1) {
            b = b.subtract(BigDecimal.ONE);
            b = b.round(context);
            b = b.add(BigDecimal.ONE);
        } else {
            b = b.round(context);
        }

        return b.doubleValue();
    }

    public void clear() {
        expression = new StringBuilder();
        workingField.setText("0");
        historyField.clear();
    }

    private  void handleBackSpace() {
        historyField.clear();
        if (expression.length() == 1) {
            clear();
        } else if (!expression.isEmpty()) {
            expression.deleteCharAt(expression.length() - 1);
            workingField.setText(expression.toString());
        }
    }

    private void editWorkingField(String text) {
        if (expression.isEmpty() && text.equals(".")) {
            expression.append(0);
        } else if (expression.isEmpty() && !text.matches("[0-9]")) {
            expression.append(workingField.getText());
        }
        expression.append(text);
        workingField.setText(expression.toString());
    }

    public void handleOnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER -> calculate();
            case ESCAPE -> clear();
            case BACK_SPACE -> handleBackSpace();
        }
        switch (event.getText()) {
            case "=" -> calculate();
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "^" -> {
                historyField.clear();
                editWorkingField(event.getText());
            }
        }
    }

    protected void setButtonListeners(Button button) {
        switch (button.getText()) {
            case "<" -> button.setOnAction(event -> handleBackSpace());
            case "C" -> button.setOnAction(event -> clear());
            case "=" -> button.setOnAction(e -> calculate());
            default -> button.setOnAction(e -> {
                historyField.clear();
                Button b = (Button) e.getSource();
                editWorkingField(b.getText());
            });
        }
    }
}
