package com.kodilla.battleships;

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
        if (wasShot == false) {
            wasShot = true;
            if (ship != null) {
                boolean isAlive = ship.hit();
                if (isAlive) {
                    status = 2;
                    return 2;   //hit
                } else {
                    status = 3;
                    return 3;   //hit and dead
                }
            }
            status = 1;
            return 1;   //miss
        }
        return 0;   //try again
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        this.status = 4;
    }

    public void clear() {
        this.ship = null;
        this.wasShot = false;
        this.status = 0;
    }

    public int getStatus() {
        return status;
    }

    public boolean getWasShot() {
        return wasShot;
    }

    public void setIsEmpty(){
        this.wasShot = true;
        if (this.status!=2 && this.status!=3) {
            this.status = 5;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "ship=" + ship +
                ", wasShot=" + wasShot +
                ", x=" + x +
                ", y=" + y +
                ", status=" + status +
                '}';
    }
}
