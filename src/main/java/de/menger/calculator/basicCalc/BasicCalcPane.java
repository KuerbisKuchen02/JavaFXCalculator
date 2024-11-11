package de.menger.calculator.basicCalc;

import de.menger.calculator.abstractCalc.CalcPane;
import javafx.scene.control.Button;

import java.util.function.BiConsumer;


public class BasicCalcPane extends CalcPane {

    public BasicCalcPane(BiConsumer<Button, CalcPane> modeSwitcher) {
        super(new String[][] {
                {"⌫", "C", "^", "÷"},
                {"7", "8", "9", "×"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"M", "0", ".", "="}
        }, modeSwitcher);
        this.calc = new BasicCalc();
    }
}
