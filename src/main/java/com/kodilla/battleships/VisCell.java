package com.kodilla.battleships;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VisCell extends Rectangle {
    private final Visu visu;
    private final int x;
    private final int y;
    private final VisBoard board;

    public VisCell(int x, int y, VisBoard board, Visu visu) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        this.visu = visu;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(1.5);

        this.setOnMouseClicked(e -> {
            this.visu.game.click(x, y, board.isEnemy(), (e.getButton() == MouseButton.PRIMARY));

            switch (this.visu.game.hasWinner()) {
                case 1 -> this.visu.showWinner("Player");
                case 2 -> this.visu.showWinner("Enemy");
            }
            this.visu.refresh();
        });
    }

    public void refresh() {
        switch (visu.game.getCellStatus(this.x, this.y, this.board.isEnemy())) {
            case 0 -> {
                setFill(Color.LIGHTGRAY);
                setStroke(Color.BLACK);
            }
            case 1 -> setFill(Color.BLUE); //miss
            case 2 -> setFill(Color.RED); //hit
            case 3 -> setFill(Color.BLACK); //hit and dead
            case 4 -> {
                if (!this.board.isEnemy())
                    setFill(Color.GRAY);
                setStroke(Color.GREEN); //ship present
            }
            case 5 -> setFill(Color.DARKBLUE); //empty field
        }
    }
}