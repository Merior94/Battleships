package com.kodilla.battleships;

public class Ship {
    private int[] head;
    private String orientation;
    private int type;
    private int health;
    private boolean isAlive;

    public Ship(int[] head, String orientation, int type) {
        this.head = head;
        this.orientation = orientation;
        this.type = type;
    }

    public boolean hit(){
        health--;
        isAlive = health <= 0;
        return isAlive;
    }
}
