package de.menger.calculator;

import de.menger.calculator.abstractCalc.CalcPane;
import de.menger.calculator.basicCalc.BasicCalcPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class CalcApplication extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("Calculator");
        stage.setResizable(false);

        CalcPane calc = new BasicCalcPane();
        Scene scene = new Scene(calc);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        scene.setOnKeyPressed(calc::handleOnKeyPressed);
        stage.setScene(scene);
        stage.show();
        stage.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}