package de.menger.calculator;

import javafx.application.Application;
import javafx.stage.Stage;

public class CalcApplication extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("Calculator");
        stage.setResizable(false);

        new WrapperCalcPane(stage);

        stage.show();
        stage.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}