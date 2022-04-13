package com.kodilla.battleships;

import javafx.application.Application;
import javafx.stage.Stage;

public class Battleships extends Application {

    //private Image imageback = new Image("file:src/main/resources/background.jpeg");
    //private Image card = new Image("file:src/main/resources/ship.jpg");
    //private FlowPane cards = new FlowPane(Orientation.HORIZONTAL);

    private Game game = new Game();
    private Visu visu = new Visu(game);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        visu.launch(stage);
        startNewGame();
    }

    private void startNewGame(){
        //clear tables
        System.out.println("Starting new game!");
        game.newGame();
    }
}