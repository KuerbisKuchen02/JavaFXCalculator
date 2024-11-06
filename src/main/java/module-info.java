module de.menger.calculator {
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
    requires jdk.jshell;
    exports de.menger.calculator;
    exports de.menger.calculator.basicCalc;
    exports de.menger.calculator.abstractCalc;
    exports de.menger.calculator.mathEditor;
}