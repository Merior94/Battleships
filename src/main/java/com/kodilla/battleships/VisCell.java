package com.kodilla.battleships;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VisCell extends Rectangle {
    private Game game;
    private int x, y;
    private Ship ship;
    private VisBoard board;

    public VisCell(int x, int y, VisBoard board, Game game) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(2);

        this.setOnMouseClicked(e -> {
                    if (game.isRunning()) {

                        if (!board.isEnemy()) {
                            return;
                        }

                        game.shoot(x, y);
                        int result = game.getPlayerCellStatus(x, y);
                        switch (result) {
                            case 0:
                                //try again
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
                        }
                    } else {

                        if (board.isEnemy()) {
                            return;
                        }

                        game.placeShip(x, y, 'v');

                        int[][] drawBoard = game.getPlayerBoard();

                        for (int i=0;i<10;i++){
                            for (int j=0;j<10;j++){
                                System.out.println("testall " + i + " " + j + " " + drawBoard[i][j]);
                                switch (drawBoard[i][j]) {
                                    case 0:
                                        //try again
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
                                        //ship present
                                        setStroke(Color.GREEN);
                                        setStrokeWidth(2);
                                        System.out.println("test");
                                        break;
                                }
                            }
                        }



                        //show all with reference
                        //board.show ships

                    }
                }
        );
    }

    public boolean shoot() {
        System.out.println("cell clicked! " + this.x + " " + this.y);
        setFill(Color.BLACK);

        if (ship != null) {
            ship.hit();
            setFill(Color.RED);
            return true;
        }
        // check for ship reference
        // check for ship health

        return false;
    }

    @Override
    public String toString() {
        return "VisCell{" +
                "x=" + x +
                ", y=" + y +
                ", ship=" + ship +
                ", board=" + board +
                '}';
    }
}
