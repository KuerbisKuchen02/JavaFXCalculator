package de.menger.calculator.mathEditor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MathEditor extends HBox {

    private final int fontSize;
    private final Color textColor;
    private final ArrayList<String> expression;

    public MathEditor(int fontSize, Color color) {
        super();
        this.fontSize = fontSize;
        this.textColor = color;
        this.expression = new ArrayList<>();
        this.setAlignment(Pos.CENTER_RIGHT);
    }

    public void setText(String text) {
        clear();
        addText(text);
    }

    public void setText(MathEditor other) {
        this.expression.clear();
        this.expression.addAll(other.expression);
        this.getChildren().clear();
        for (Node node : other.getChildren()) {
            TextElement text = (TextElement) node;
            TextElement newText = new TextElement(text.getText(), this.fontSize, this.textColor, text.isSuperScript());
            this.getChildren().add(newText);
        }

    }

    public void addText(String text) {
        boolean isSuperscript;
        try {
            isSuperscript = text.matches("[.0-9]") &&
                    (this.expression.get(this.expression.size() - 1).equals("^")
                            || ((TextElement) this.getChildren().get(this.getChildren().size() - 1)).isSuperScript());
        } catch (IndexOutOfBoundsException e) {
            isSuperscript = false;
        }
        this.expression.add(text);
        if (text.equals("^")) {
            return;
        }
        text = Converter.convertAsciToUnicode(text);
        this.getChildren().add(new TextElement(text, fontSize, textColor, isSuperscript));
        this.layout();
    }

    public void removeLast() {
        if (!this.isEmpty()) {
            String s = this.expression.remove(this.expression.size() - 1);

            if (!s.equals("^")) {
                this.getChildren().remove(this.getChildren().size() - 1);
            }
        }
        if (this.getChildren().isEmpty()) {
            addText("0");
        }
        this.layout();
    }

    public void clear() {
        this.expression.clear();
        this.getChildren().clear();
        this.layout();
    }

    public String getText() {
        StringBuilder expression = new StringBuilder();
        for (String text : this.expression) {
            expression.append(text);
        }
        return expression.toString();
    }

    public boolean isEmpty() {
        return this.expression.isEmpty();
    }

    public int length() {
        return this.expression.size();
    }
}
