package com.kodilla.battleships;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VisBoard {
    private Game game;
    public Pane board = new VBox();
    private boolean isEnemy;

    public VisBoard(boolean isEnemy, Game game) {
        this.isEnemy = isEnemy;

        for (int i = 0; i < 10; i++) {
            HBox row = new HBox();
            for (int j = 0; j < 10; j++) {
                VisCell c = new VisCell(j, i, this,game);
                row.getChildren().add(c);
            }
            board.getChildren().add(row);
        }
    }

    public boolean isEnemy(){
        return isEnemy;
    }

}
