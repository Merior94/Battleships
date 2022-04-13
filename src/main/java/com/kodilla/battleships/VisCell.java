package com.kodilla.battleships;

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
            this.visu.game.click(x, y, board.isEnemy());
            //int[][] drawBoard = game.getBoard(false);
            //game.checkWinner?
            this.visu.refresh();
            //System.out.println("test" + x + " " + y + " " + board.isEnemy());
        });
    }

    public void refresh() {
        switch (visu.game.getCellStatus(this.x,this.y,this.board.isEnemy())) {
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
                setFill(Color.GRAY);
                setStroke(Color.GREEN); //ship present
                break;
        }
    }

    @Override
    public String toString() {
        return "VisCell{" +
                "game=" + visu.game +
                ", x=" + x +
                ", y=" + y +
                ", ship=" + ship +
                ", board=" + board +
                '}';
    }
}
//            for (int i=0;i<10;i++){
//                for (int j=0;j<10;j++){
//                    System.out.println("testall " + i + " " + j + " " + drawBoard[i][j]);
//                    switch (drawBoard[i][j]) {
//                        case 0:
//                            //try again
//                            break;
//                        case 1:
//                            setFill(Color.BLUE); //miss
//                            break;
//                        case 2:
//                            setFill(Color.RED); //hit
//                            break;
//                        case 3:
//                            setFill(Color.BLACK); //hit and dead
//                            break;
//                        case 4:
//                            //ship present
//                            setStroke(Color.GREEN);
//                            System.out.println("test");
//                            break;
//                    }
//                }
//            }
//
//
//
//                    if (game.isRunning()) {
//
//                        if (!board.isEnemy()) {
//                            return;
//                        }
//
//                        game.shoot(x, y);
//                        int result = game.getPlayerCellStatus(x, y);
//                        switch (result) {
//                            case 0:
//                                //try again
//                                break;
//                            case 1:
//                                setFill(Color.BLUE); //miss
//                                break;
//                            case 2:
//                                setFill(Color.RED); //hit
//                                break;
//                            case 3:
//                                setFill(Color.BLACK); //hit and dead
//                                break;
//                        }
//                    }
//                    }
//                }
