package de.menger.calculator;

import de.menger.calculator.abstractCalc.CalcPane;
import de.menger.calculator.basicCalc.BasicCalcPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalcApplication extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("Calculator");
        stage.setResizable(false);

        CalcPane calc = new BasicCalcPane();
        stage.setScene(new Scene(calc));

        stage.show();
        stage.requestFocus();

    }

    public static void main(String[] args) {
        launch();
    }
}