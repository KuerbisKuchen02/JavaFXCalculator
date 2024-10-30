module de.menger.calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens de.menger.calculator to javafx.fxml;
    exports de.menger.calculator;
    exports de.menger.calculator.basicCalc;
    opens de.menger.calculator.basicCalc to javafx.fxml;
    exports de.menger.calculator.abstractCalc;
    opens de.menger.calculator.abstractCalc to javafx.fxml;
}