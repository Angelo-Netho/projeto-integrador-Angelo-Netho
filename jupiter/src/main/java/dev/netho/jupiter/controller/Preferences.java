package dev.netho.jupiter.controller;

import dev.netho.jupiter.Main;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Preferences {

    @FXML
    public HBox box;

    private boolean isDark;

    private final Home home;

    public Preferences(Home home) {
        this.home = home;
        this.isDark = home.root.getStylesheets().get(0).contains("style-dark");
    }

    public void initialize() {
        Preferences.ToggleSwitch toggleSwitch = new Preferences.ToggleSwitch();
        box.getChildren().add(toggleSwitch);
        toggleSwitch.setTranslateX(250);

        toggleSwitch.setOnMouseClicked(event -> {
            toggleSwitch.switchedOn.set(!toggleSwitch.switchedOn.get());
            changeTheme();
        });

        if(isDark) {
            toggleSwitch.switchedProperty().set(true);
            enableDarkMode();
        }
    }

    public void changeTheme() {
        if(isDark) {
            enableLightMode();
        }else {
            enableDarkMode();
        }
        isDark = !isDark;
    }

    private void enableDarkMode() {
        home.root.getStylesheets().remove(Main.class.getResource("/dev/netho/style/style-default.css").toExternalForm());
        home.root.getStylesheets().add(Main.class.getResource("/dev/netho/style/style-dark.css").toExternalForm());
    }

    private void enableLightMode() {
        home.root.getStylesheets().remove(Main.class.getResource("/dev/netho/style/style-dark.css").toExternalForm());
        home.root.getStylesheets().add(Main.class.getResource("/dev/netho/style/style-default.css").toExternalForm());
    }


    //https://www.youtube.com/watch?v=maX5ymmQixM
    private static class ToggleSwitch extends Parent {

        private final BooleanProperty switchedOn = new SimpleBooleanProperty(false);

        //Animações
        private final TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
        private final FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

        private final ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

        private BooleanProperty switchedProperty() {
            return switchedOn;
        }

        public ToggleSwitch() {
            //Cria formas

            Rectangle background = new Rectangle(50, 25);
            background.setArcWidth(25);
            background.setArcHeight(25);
            background.setFill(Color.WHITE);
            background.setStroke(Color.LIGHTGRAY);
            background.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.4), 10, 0, 0, 0);");

            Circle trigger = new Circle(12.5);
            trigger.setCenterX(12.5);
            trigger.setCenterY(12.5);
            trigger.setFill(Color.WHITE);
            trigger.setStroke(Color.LIGHTGRAY);
            trigger.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.4), 10, 0, 0, 0);");



            translateAnimation.setNode(trigger);
            fillAnimation.setShape(background);

            getChildren().addAll(background, trigger);

            switchedOn.addListener((observable, oldValue, newValue) -> {
                boolean isOn = newValue;
                translateAnimation.setToX(isOn ? 50 - 25 : 0);
                fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
                fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);

                animation.play();
            });

            setOnMouseClicked(event -> {
                switchedOn.set(!switchedOn.get());
            });

        }
    }
}
