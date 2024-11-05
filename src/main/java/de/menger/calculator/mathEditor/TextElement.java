package de.menger.calculator.mathEditor;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class TextElement extends Text {

    private final boolean isSuperScript;

    public TextElement(String text, int fontSize, Color color, boolean isSuperScript) {
        super(text);
        this.isSuperScript = isSuperScript;
        this.setFill(color);
        if (isSuperScript) {
            double size = 3.0 / 4 * fontSize;
            this.setFont(new Font("System", size));
            this.setTranslateY(-size/2);
        } else {
            this.setFont(new Font("System", fontSize));
        }
    }

    public boolean isSuperScript() {
        return isSuperScript;
    }
}
