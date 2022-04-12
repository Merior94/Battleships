package com.kodilla.battleships;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Board {
    public Pane board = new VBox();
    private boolean isEnemy;

    public Board(boolean isEnemy) {
        this.isEnemy = isEnemy;

        for(int i = 0; i<10; i++){
            HBox row = new HBox();
            for(int j = 0; j<10; j++){
                Cell cell = new Cell(i,j,this);
                cell.setOnMouseClicked(e -> {
                    if (isEnemy){
                        if (!Battleships.running){
                            return;
                        }
                    }else{
                        if (Battleships.running){
                            return;
                        }
                    }
                    cell.shoot();
                });
                row.getChildren().add(cell);
            }
            board.getChildren().add(row);
        }



    }
}
