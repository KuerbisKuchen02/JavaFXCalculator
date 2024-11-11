package de.menger.calculator;

import de.menger.calculator.abstractCalc.CalcPane;
import de.menger.calculator.advancedCalc.AdvancedCalcPane;
import de.menger.calculator.basicCalc.BasicCalcPane;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class WrapperCalcPane {

    private final Stage root;

    public WrapperCalcPane(Stage stage) {
        root = stage;
        swapScene(new BasicCalcPane(this::showPopup));
    }

    private void showPopup(Node sourceNode, CalcPane sourceClass) {
        ContextMenu contextMenu = new ContextMenu();

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResource("checkmark.png")).toExternalForm()));
        imageView.setFitHeight(10);
        imageView.setFitWidth(10);

        MenuItem basic = new MenuItem("Standard");
        if (sourceClass instanceof BasicCalcPane) {
            basic.setGraphic(imageView);
        }
        MenuItem advanced = new MenuItem("Wissenschaftlich");
        if (sourceClass instanceof AdvancedCalcPane) {
            advanced.setGraphic(imageView);
        }
        basic.setOnAction(e -> swapScene(new BasicCalcPane(this::showPopup)));
        advanced.setOnAction(e -> swapScene(new AdvancedCalcPane(this::showPopup)));

        contextMenu.getItems().addAll(basic, advanced);
        contextMenu.getStyleClass().add("context-menu");
        Bounds bounds = sourceNode.localToScreen(sourceNode.getBoundsInLocal());
        contextMenu.show(sourceNode, bounds.getMinX(), bounds.getMaxY() + 11);
    }

    private void swapScene(CalcPane calcPane) {
        Scene scene = new Scene(calcPane);
        scene.setOnKeyPressed(calcPane::handleOnKeyPressed);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        root.setScene(scene);
    }
}