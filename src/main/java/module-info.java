module de.menger.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.menger.calculator to javafx.fxml;
    exports de.menger.calculator;
    exports de.menger.calculator.basicCalc;
    opens de.menger.calculator.basicCalc to javafx.fxml;
    exports de.menger.calculator.calc;
    opens de.menger.calculator.calc to javafx.fxml;
}