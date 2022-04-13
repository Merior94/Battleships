package com.kodilla.battleships;

import java.util.Scanner;

public class Game {
    private Board playerBoard;
    private Board enemyBoard;
    private boolean run;

    public Game() {
        this.run = false;
        this.playerBoard = new Board(false);
        this.enemyBoard = new Board(true);
    }

    public void newGame() {
        //clear boards;
        this.run = false;
        playerBoard.clear();
        enemyBoard.clear();
    }

    public void startGame() {
        System.out.println("Game started!");
        this.run = true;
    }

    public void exit() {
        System.out.println("Exit...");
        System.exit(0);
    }

    public void click(int x, int y, boolean isEnemy){
        placeShip(x,y,'v');
    }

    public int placeShip(int x, int y, char orientation) {
        int numberOfShips = playerBoard.getNumberOfShips();

        switch (numberOfShips) {
            case 0:
                playerBoard.placeShip(4, x, y, orientation);
                return 4;
            case 1, 2:
                playerBoard.placeShip(3, x, y, orientation);
                return 3;
            case 3, 4, 5:
                playerBoard.placeShip(2, x, y, orientation);
                return 2;
            default:
                startGame();
                return 0;
        }

    }

    public int shoot(int x, int y) {
        return playerBoard.shoot(x, y);
    }

    public boolean isRunning() {
        return this.run;
    }

    public int getCellStatus(int x,int y, boolean isEnemy){
        if (!isEnemy){
            //System.out.println(playerBoard.getCellStatus(x,y));
            return playerBoard.getCellStatus(x,y);

        } else {
            return enemyBoard.getCellStatus(x,y);
        }
    }

}
