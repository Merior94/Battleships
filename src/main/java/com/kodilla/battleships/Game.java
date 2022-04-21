package com.kodilla.battleships;

import java.util.IllegalFormatCodePointException;
import java.util.Random;

public class Game {
    private Board playerBoard;
    private Board enemyBoard;
    private boolean run;
    private int enemyX;
    private int enemyY;

    public Game() {
        this.run = false;
        this.playerBoard = new Board(false);
        this.enemyBoard = new Board(true);
        this.enemyX = -1;
        this.enemyY = -1;
    }

    public void newGame() {
        //clear boards;
        this.run = false;
        playerBoard.clear();
        enemyBoard.clear();
    }

    public void exit() {
        System.out.println("Exit...");
        System.exit(0);
    }

    public boolean isRunning() {
        return this.run;
    }

    private void start() {
        //Randomize opponent ships
        System.out.println("Enemy is placing ships...");

        Random random = new Random();
        int result = 0;
        char orient;
        do {
            if (random.nextInt() % 2 == 0) {
                orient = 'v';
            } else {
                orient = 'h';
            }
            result = this.placeShipEnemy(random.nextInt(10), random.nextInt(10), orient);
        } while (result != 0);

        System.out.println("Game started!");
        this.run = true;
    }

    public void click(int x, int y, boolean isEnemy, boolean primaryButton) {

        //System.out.println("click!" + x + " " + y);
        if (!this.run) { //prepare game
            if (primaryButton) {
                this.placeShip(x, y, 'v');
            } else {
                this.placeShip(x, y, 'h');
            }
        } else {    //play
            if (isEnemy) {
                boolean res = enemyBoard.getCellShootStatus(x, y);
                if (res) { //return if shooting the same cell
                    return;
                }

                if (enemyBoard.shoot(x, y) == 1) {

                    //poziom trudnosci!!!
                    Random random = new Random();
                    //if (enemyX == -1 && enemyY == -1) {
                    enemyX = random.nextInt(10);
                    enemyY = random.nextInt(10);
                    //}
                    int enemyShootResult = playerBoard.shoot(enemyX, enemyY);
                    System.out.println("first shoot -> x: " + enemyX + ", y: " + enemyY + " -> " + enemyShootResult);

                    while (enemyShootResult != 1) {
                        switch (enemyShootResult) {
                            case 0:
                                enemyX = enemyX + 1;
                                if (enemyX > 9) {
                                    enemyX = 0;
                                    enemyY++;
                                    if (enemyY > 9) {
                                        enemyY = 0;
                                    }
                                }
                                enemyShootResult = playerBoard.shoot(enemyX, enemyY);
                                break;

                            case 1:
                                enemyX = random.nextInt(10);
                                enemyY = random.nextInt(10);
                                enemyShootResult = playerBoard.shoot(enemyX, enemyY);
                                break;

                            case 2:
                                playerBoard.setAsShoot(enemyX-1,enemyY-1);
                                playerBoard.setAsShoot(enemyX-1,enemyY+1);
                                playerBoard.setAsShoot(enemyX+1,enemyY-1);
                                playerBoard.setAsShoot(enemyX+1,enemyY+1);

                                enemyX = enemyX + random.nextInt(3) - 1;
                                if (enemyX < 0)
                                    enemyX = 0;
                                if (enemyX > 9)
                                    enemyX = 9;

                                enemyY = enemyY + random.nextInt(3) - 1;
                                if (enemyY < 0)
                                    enemyY = 0;
                                if (enemyY > 9)
                                    enemyY = 9;

                                enemyShootResult = playerBoard.shoot(enemyX, enemyY);
                                break;

                            case 3:
                                playerBoard.setAsShoot(enemyX-1,enemyY-1);
                                playerBoard.setAsShoot(enemyX-1,enemyY+1);
                                playerBoard.setAsShoot(enemyX+1,enemyY-1);
                                playerBoard.setAsShoot(enemyX+1,enemyY+1);
                                playerBoard.setAsShoot(enemyX,enemyY-1);
                                playerBoard.setAsShoot(enemyX,enemyY+1);
                                playerBoard.setAsShoot(enemyX-1,enemyY);
                                playerBoard.setAsShoot(enemyX+1,enemyY);

                                enemyX = random.nextInt(10);
                                enemyY = random.nextInt(10);
                                enemyShootResult = playerBoard.shoot(enemyX, enemyY);
                                break;
                        }
                        System.out.println("x: " + enemyX + ", y: " + enemyY + " -> " + enemyShootResult);
                    }
                    ; //enemy shoots until miss
                }
            }
        }
    }

    public String getScore() {
        return ("Player ships: " + playerBoard.getHealthOfShips() + " | Enemy ships: " + enemyBoard.getHealthOfShips());
    }

    public int hasWinner() {
        if (this.run) {
            if (enemyBoard.getHealthOfShips() == 0) {
                return 1;
            }
            if (playerBoard.getHealthOfShips() == 0) {
                return 2;
            }
        }
        return 0;
    }

    public int getCellStatus(int x, int y, boolean isEnemy) {
        if (!isEnemy) {
            return playerBoard.getCellStatus(x, y);
        } else {
            return enemyBoard.getCellStatus(x, y);
        }
    }

    public int placeShip(int x, int y, char orientation) {
        int numberOfShips = playerBoard.getNumberOfShips();

        switch (numberOfShips) {
            case 0:
                playerBoard.placeShip(4, x, y, orientation);
                return 1;

            case 1, 2:
                playerBoard.placeShip(3, x, y, orientation);
                return 1;

            case 3, 4, 5:
                playerBoard.placeShip(2, x, y, orientation);
                return 1;

            default:
                this.start();
                return 0;
        }
    }

    public int placeShipEnemy(int x, int y, char orientation) {
        int numberOfShips = enemyBoard.getNumberOfShips();

        switch (numberOfShips) {
            case 0:
                if (enemyBoard.canShipBePlaced(4, x, y, orientation)) {
                    enemyBoard.placeShip(4, x, y, orientation);
                    return 1;
                }
                return -1;

            case 1, 2:
                if (enemyBoard.canShipBePlaced(3, x, y, orientation)) {
                    enemyBoard.placeShip(3, x, y, orientation);
                    return 1;
                }
                return -1;
            case 3, 4, 5:
                if (enemyBoard.canShipBePlaced(2, x, y, orientation)) {
                    enemyBoard.placeShip(2, x, y, orientation);
                    return 1;
                }
                return -1;
            default:
                return 0;
        }
    }

}
