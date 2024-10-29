package de.menger.calculator;

import de.menger.calculator.basicCalc.BasicCalc;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalcApplication extends Application {

    StringBuilder expression = new StringBuilder();
    BasicCalc calc = new BasicCalc();

    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.setEditable(false);
        VBox.setMargin(textField, new Insets(10, 10, 10, 10));
        GridPane grid = new GridPane();
        VBox.setMargin(grid, new Insets(0, 10, 10, 10));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        vbox.getChildren().addAll(textField, grid);

        String[] buttonTexts = {
                "7", "8", "9", "/", "(",
                "4", "5", "6", "*", ")",
                "1", "2", "3", "-", "^",
                "<-", "0", "C", "=", "+"};

        Button[] buttons = new Button[buttonTexts.length];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(buttonTexts[i]);
            buttons[i].setPrefSize(40,40);
            grid.add(buttons[i], i % 5, i / 5);

            switch (buttonTexts[i]) {
                case "C" -> buttons[i].setOnAction(event -> {
                    expression = new StringBuilder();
                    textField.setText("");
                });
                case "<-" -> buttons[i].setOnAction(event -> {
                    if (!expression.isEmpty()) {
                        expression.deleteCharAt(expression.length() - 1);
                        textField.setText(expression.toString());
                    }
                });
                case "=" -> buttons[i].setOnAction(e -> {
                    Integer result = null;
                    try {
                        result = calc.eval(expression.toString());
                    } catch (IllegalArgumentException ex) {
                        textField.setText("Error");
                    }
                    expression = new StringBuilder();
                    if (result != null) {
                        expression.append(result);
                        textField.setText(expression.toString());
                    }
                });
                default -> buttons[i].setOnAction(e -> {
                    Button b = (Button) e.getSource();
                    expression.append(b.getText());
                    textField.setText(expression.toString());
                });
            }
        }

        stage.setTitle("Calculator");

        stage.setScene(new Scene(vbox));
        stage.setResizable(false);
        stage.show();
        stage.requestFocus();

    }

    public static void main(String[] args) {
        launch();
    }
}