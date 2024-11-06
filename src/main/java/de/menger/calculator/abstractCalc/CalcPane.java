package de.menger.calculator.abstractCalc;

import de.menger.calculator.mathEditor.Converter;
import de.menger.calculator.mathEditor.MathEditor;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public abstract class CalcPane extends VBox {

    protected Calc calc;

    protected Button[] buttons;
    protected MathEditor workingField;
    protected MathEditor historyField;

    public CalcPane(String[][] buttonTexts) {
        super();

        this.historyField = new MathEditor(20, Color.web("#9F9EA0"));
        this.historyField.getStyleClass().add("history-field");
        historyField.setOnMouseClicked(this::handleHistoryClick);
        this.workingField = new MathEditor(30, Color.WHITE);
        this.workingField.getStyleClass().add("working-field");

        GridPane grid = new GridPane();
        grid.getStyleClass().add("button-grid");
        this.getChildren().addAll(historyField, workingField, grid);
        grid.setAlignment(Pos.CENTER);

        ArrayList<Button> buttonList = new ArrayList<>();

        for (int i = 0; i < buttonTexts.length; i++) {
            for (int j = 0; j < buttonTexts[i].length; j++) {
                Button button = new Button(buttonTexts[i][j]);
                button.setPrefSize(40,40);
                setButtonListeners(button);
                buttonList.add(button);
                grid.add(button, j, i);
                if (buttonTexts[i][j].matches("[⌫M]")) {
                    button.getStyleClass().add("small_font");
                }
                if (i == 0 && j >= buttonTexts[i].length - 4) {
                    button.getStyleClass().add("top-button");
                }
                if (j == buttonTexts[i].length - 1) {
                    button.getStyleClass().add("operator-button");
                }
                if (j < buttonTexts[i].length - 4) {
                    button.getStyleClass().add("extra-button");
                }
            }
        }

        this.buttons = buttonList.toArray(new Button[0]);
        clear();
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

    public void calculate() {
        if (workingField.isEmpty()) {
            return;
        }
        Double result = null;
        try {
            result = calc.eval(workingField.getText());
        } catch (IllegalArgumentException ex) {
            workingField.clear();
            workingField.setText("Error");
        }
        historyField.setText(workingField);
        if (result != null) {
            if (result.isInfinite()) {
                workingField.setText("Inf");
            } else if (result.isNaN()) {
                workingField.setText("NaN");
            } else {
                double out = truncate(result);
                if ((long) out == out) {
                    String t = Long.toString((long) out);
                    workingField.setText(t);
                } else {
                    workingField.setText(Double.toString(out));
                }
            }
        }
    }

    public void clear() {
        workingField.setText("0");
        historyField.clear();
    }

    private void handleHistoryClick(Event event) {
        if (historyField.isEmpty()) return;
        workingField.setText(historyField);
        historyField.clear();
    }

    private void handleBackSpace() {
        historyField.clear();
        workingField.removeLast();
    }

    private void editWorkingField(String text) {
        text = Converter.convertUnicodeToAsci(text);
        if (workingField.getText().equals("0") && !text.matches("[-+*/!%]")) {
            workingField.setText(text);
        } else {
            workingField.addText(text);
        }
    }

    public void handleOnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER -> calculate();
            case ESCAPE -> clear();
            case BACK_SPACE -> handleBackSpace();
        }
        switch (event.getText()) {
            case "=" -> calculate();
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "^", "." -> {
                historyField.clear();
                editWorkingField(event.getText());
            }
        }
    }

    protected void setButtonListeners(Button button) {
        switch (button.getText()) {
            case "⌫" -> button.setOnAction(event -> handleBackSpace());
            case "C" -> button.setOnAction(event -> clear());
            case "=" -> button.setOnAction(e -> calculate());
            case "M" -> button.setOnAction(e -> System.out.println("Not Implemented"));
            default -> button.setOnAction(e -> {
                historyField.clear();
                Button b = (Button) e.getSource();
                editWorkingField(b.getText());
            });
        }
    }
}
