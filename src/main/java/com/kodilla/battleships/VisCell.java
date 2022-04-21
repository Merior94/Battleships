package com.kodilla.battleships;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VisCell extends Rectangle {
    private Visu visu;
    private int x, y;
    private Ship ship;
    private VisBoard board;

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
            this.visu.game.click(x, y, board.isEnemy(), (boolean) (e.getButton() == MouseButton.PRIMARY));

            int winner = this.visu.game.hasWinner();
            switch (winner) {
                case 1:
                    this.visu.showWinner("Player");
                    break;
                case 2:
                    this.visu.showWinner("Enemy");
                    break;
                default:
            }
            this.visu.refresh();
            //System.out.println("test" + x + " " + y + " " + board.isEnemy());
        });
    }

    public void refresh() {
        switch (visu.game.getCellStatus(this.x, this.y, this.board.isEnemy())) {
            case 0:
                setFill(Color.LIGHTGRAY);
                setStroke(Color.BLACK);
                break;
            case 1:
                setFill(Color.BLUE); //miss
                break;
            case 2:
                setFill(Color.RED); //hit
                break;
            case 3:
                setFill(Color.BLACK); //hit and dead
                break;
            case 4:
                if (!this.board.isEnemy())
                    setFill(Color.GRAY);
                setStroke(Color.GREEN); //ship present
                break;
            case 5:
                setFill(Color.DARKBLUE); //hit and dead
                break;

        }
    }
}