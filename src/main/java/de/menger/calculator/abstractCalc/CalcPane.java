package de.menger.calculator.abstractCalc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public abstract class CalcPane extends VBox {

    protected StringBuilder expression;
    protected Calc calc;

    protected Button[] buttons;
    protected TextField textField;

    public CalcPane(String[][] buttonTexts) {
        super();
        this.textField = new TextField();
        GridPane grid = new GridPane();
        this.getChildren().addAll(textField, grid);
        VBox.setMargin(textField, new Insets(10, 10, 10, 10));
        VBox.setMargin(grid, new Insets(0, 10, 10, 10));

        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.setEditable(false);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        ArrayList<Button> buttonList = new ArrayList<>();

        for (int i = 0; i < buttonTexts.length; i++) {
            for (int j = 0; j < buttonTexts[i].length; j++) {
                Button button = new Button(buttonTexts[i][j]);
                button.setPrefSize(40, 40);
                setButtonListeners(button);
                buttonList.add(button);
                grid.add(button, j, i);
            }
        }

        this.buttons = buttonList.toArray(new Button[0]);
        this.expression = new StringBuilder();
    }

    public String formatResult(Double value) {
        if (value.isInfinite()) {
            return "Inf";
        } else if (value.isNaN()) {
            return "NaN";
        } else {
            Double out = truncate(value);
            if (out.longValue() == out) {
                return Long.toString(out.longValue());
            } else {
                return Double.toString(out);
            }
        }
    }

    public String formatExpression(Double value) {
        if (value.isInfinite()) {
            return "";
        } else if (value.isNaN()) {
            return "";
        } else {
            Double out = truncate(value);
            if (out.longValue() == out) {
                return Long.toString(out.longValue());
            } else {
                return Double.toString(out);
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

    protected abstract void setButtonListeners(Button button);
}
