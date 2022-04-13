package com.kodilla.battleships;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {
    private Ship ship;
    private boolean wasShot;
    private int x;
    private int y;
    private int status;

    public Cell(int x, int y) {
        this.wasShot = false;
        this.x = x;
        this.y = y;
        this.status = 0;
    }

    public int shoot() {
        //System.out.println("cell shot!");
        if (wasShot == false) {
            if (ship != null) {
                boolean isAlive = ship.hit();
                if (isAlive) {
                    status = 2;
                    return 2;   //hit
                } else {
                    status = 3;
                    return 3;   //hit and dead
                }
            } else {
                status = 1;
                return 1;   //no hit
            }
        }
        //status = 0;
        return 0;   //try again
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        this.status = 4;
        System.out.println("setship" + status);
    }

    public Ship getShip() {
        return ship;
    }

    public void clear() {
        this.ship = null;
        this.wasShot = false;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "ship=" + ship +
                ", wasShot=" + wasShot +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
