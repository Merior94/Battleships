package com.kodilla.battleships;

public class Ship {
    private int health;

    public Ship(int type) {
        this.health = type;
    }

    public boolean hit(){
        health--;
        return health > 0;
    }

    public int getHealth(){
        return this.health;
    }
}
