package com.kodilla.battleships;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
public int x,y;
    public Ship ship;
    private Board board;

    public Cell(int x, int y, Board board) {
        super(30,30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    public boolean shoot(){
        System.out.println("cell clicked! " + this.x + " " + this.y);
        setFill(Color.BLACK);

        if (ship!=null){
            ship.hit();
            setFill(Color.RED);
            return true;
        }
        // check for ship reference
        // check for ship health

        return false;
    }
}
