package com.kodilla.battleships;

import javafx.application.Application;
import javafx.stage.Stage;

public class Battleships extends Application {

    //private Image imageback = new Image("file:src/main/resources/background.jpeg");
    //private Image card = new Image("file:src/main/resources/ship.jpg");
    //private FlowPane cards = new FlowPane(Orientation.HORIZONTAL);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Visu visu = new Visu(stage);
        visu.launch();

    }
}