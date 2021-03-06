package com.kodilla.battleships;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VisBoard {
    public Pane board = new VBox();
    private final VisCell[][] visCells = new VisCell[10][10];
    private final boolean isEnemy;

    public VisBoard(boolean isEnemy, Game game, Visu visu) {
        this.isEnemy = isEnemy;

        for (int i = 0; i < 10; i++) {
            HBox row = new HBox();
            for (int j = 0; j < 10; j++) {
                visCells[i][j] = new VisCell(j, i, this, visu);
                row.getChildren().add(visCells[i][j]);
            }
            board.getChildren().add(row);
        }
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public void refresh() {
        for (VisCell[] ac : visCells) {
            for (VisCell c : ac) {
                c.refresh();
            }
        }
    }
}
