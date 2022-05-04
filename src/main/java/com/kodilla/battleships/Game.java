package com.kodilla.battleships;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Game {
    private final Board playerBoard;
    private final Board enemyBoard;
    private boolean run;
    private int enemyX;
    private int enemyY;
    File savedRanking = new File("ranking.list");
    List<RankingEntry> ranking = new ArrayList<>();

    public Game() {
        this.run = false;
        this.playerBoard = new Board();
        this.enemyBoard = new Board();
        this.enemyX = -1;
        this.enemyY = -1;
    }

    public void newGame() {
        this.run = false;
        playerBoard.clear();
        enemyBoard.clear();
    }

    public void exit() {
        System.out.println("Exit...");
        System.exit(0);
    }

    private void start() {
        //Randomize opponent ships
        System.out.println("Enemy is placing ships...");

        Random random = new Random();
        int result;
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
        if (!this.run) { //prepare game
            if (!isEnemy) { //use only players board
                if (primaryButton) {
                    this.placeShip(x, y, 'v');
                } else {
                    this.placeShip(x, y, 'h');
                }
            }
        } else {    //play
            if (isEnemy) {
                boolean res = enemyBoard.getCellShootStatus(x, y);
                if (res) { //return if shooting the same cell
                    return;
                }

                if (enemyBoard.shoot(x, y) == 1) {
                    Random random = new Random();
                    Cell[] freeCells;
                    Cell target;
                    int enemyShootResult = 0;

                    while (enemyShootResult != 1) {         //until miss

                        freeCells = playerBoard.getNotShootedCells();   //szukaj niestrzelonych

                        switch (enemyShootResult) {
                            case 0 -> {
                                target = freeCells[random.nextInt(freeCells.length)];   //wylosuj z niestrzelonych
                                enemyX = target.getX();
                                enemyY = target.getY();
                                enemyShootResult = playerBoard.shoot(enemyX, enemyY);
                            }
                            case 2 -> {
                                playerBoard.setAsShoot(enemyX - 1, enemyY - 1);
                                playerBoard.setAsShoot(enemyX - 1, enemyY + 1);
                                playerBoard.setAsShoot(enemyX + 1, enemyY - 1);
                                playerBoard.setAsShoot(enemyX + 1, enemyY + 1);
                                freeCells = Arrays.stream(freeCells)
                                        .filter(c -> !c.getWasShot())
                                        .filter(c -> abs(enemyX - c.getX()) <= 1)
                                        .filter(c -> abs(enemyY - c.getY()) <= 1)
                                        .toArray(Cell[]::new);
                                System.out.println("---hit---");
                                for (Cell c : freeCells) {
                                    System.out.println(c + " ");
                                }
                                System.out.println("------");
                                target = freeCells[random.nextInt(freeCells.length)];   //wylosuj z sąsiadów
                                enemyX = target.getX();
                                enemyY = target.getY();
                                enemyShootResult = playerBoard.shoot(enemyX, enemyY);
                            }
                            case 3 -> {
                                playerBoard.setAsShoot(enemyX - 1, enemyY - 1);
                                playerBoard.setAsShoot(enemyX - 1, enemyY + 1);
                                playerBoard.setAsShoot(enemyX + 1, enemyY - 1);
                                playerBoard.setAsShoot(enemyX + 1, enemyY + 1);
                                playerBoard.setAsShoot(enemyX, enemyY - 1);
                                playerBoard.setAsShoot(enemyX, enemyY + 1);
                                playerBoard.setAsShoot(enemyX - 1, enemyY);
                                playerBoard.setAsShoot(enemyX + 1, enemyY);
                                target = freeCells[random.nextInt(freeCells.length)];   //wylosuj z niestrzelonych
                                enemyX = target.getX();
                                enemyY = target.getY();
                                enemyShootResult = playerBoard.shoot(enemyX, enemyY);
                            }
                        }
                        System.out.println("x: " + enemyX + ", y: " + enemyY + " -> " + enemyShootResult);
                    }
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
                addToRanking();
                return 1;
            }
            if (playerBoard.getHealthOfShips() == 0) {
                addToRanking();
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

    public void placeShip(int x, int y, char orientation) {
        int numberOfShips = playerBoard.getNumberOfShips();

        switch (numberOfShips) {
            case 0 -> playerBoard.placeShip(4, x, y, orientation);
            case 1, 2 -> playerBoard.placeShip(3, x, y, orientation);
            case 3, 4, 5 -> playerBoard.placeShip(2, x, y, orientation);
            default -> this.start();
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

    public void addToRanking() {
        ranking.add(new RankingEntry(playerBoard.getHealthOfShips(), enemyBoard.getHealthOfShips()));
        saveRanking();
    }

    public List<RankingEntry> getRanking() {
        return ranking;
    }

    public void saveRanking() {
        System.out.println("saving...");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedRanking));
            oos.writeObject(ranking);
            oos.close();
        } catch (Exception e) {
            System.out.println("exception! " + e);
        }
    }

    public void loadRanking() {
        System.out.println("loading...");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedRanking));
            Object readObject = ois.readObject();

            Object readMap = ois.readObject();
            if (readMap instanceof HashMap) {
                ranking.clear();
                ranking.addAll((ArrayList) readObject);
            }
            ois.close();
            ranking = ranking.stream().sorted(Comparator.comparing(RankingEntry::getDt).reversed()).collect(Collectors.toList());

        } catch (Exception e) {
            ranking.clear();
            System.out.println("exception! " + e);
        }
    }
}
