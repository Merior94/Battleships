package com.kodilla.battleships;

public class Ship {
    private int type;
    private int health;
    private boolean isAlive;

    public Ship(int type) {
        this.type = type;
        this.health = this.type;
    }

    public boolean hit(){
        health--;
        isAlive = health > 0;
        return isAlive;
    }

    public int getHealth(){
        return this.health;
    }

    public boolean getIsAlive(){
        return this.isAlive;
    }
}
