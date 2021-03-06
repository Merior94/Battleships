package com.kodilla.battleships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final Cell[][] cells;
    private final List<Ship> ships;

    public Board() {
        this.ships = new ArrayList<>();
        this.cells = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }
    }

    public int shoot(int x, int y) {
        return this.cells[x][y].shoot();
    }

    public void setAsShoot(int x, int y) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
            this.cells[x][y].setIsEmpty();
        }
    }

    public void placeShip(int type, int x, int y, char orientation) {
        //check if possible
        Ship ship = new Ship(type);
        if (orientation == 'v' && canShipBePlaced(type, x, y, 'v')) {
            this.ships.add(ship);
            for (int i = 0; i < type; i++) {
                this.cells[x][y + i].setShip(ship);
            }
        }
        if (orientation == 'h' && canShipBePlaced(type, x, y, 'h')) {
            this.ships.add(ship);
            for (int i = 0; i < type; i++) {
                this.cells[x + i][y].setShip(ship);
            }
        }
    }

    public boolean canShipBePlaced(int type, int x, int y, char orientation) {
        int result = 0;

        switch (orientation) {
            case 'v':
                for (int i = 0; i < type; i++) {
                    result += this.getCellNeighbourStatus(x, y + i);
                }
                break;
            case 'h':
                for (int i = 0; i < type; i++) {
                    result += this.getCellNeighbourStatus(x + i, y);
                }
                break;
        }
        return (result == 0);
    }

    public boolean getCellShootStatus(int x, int y) {
        return cells[x][y].getWasShot();
    }

    public int getNumberOfShips() {
        return ships.size();
    }

    public int getHealthOfShips() {
        return ships.stream()
                .map(Ship::getHealth)
                .reduce(0, Integer::sum);
    }

    public void clear() {
        this.ships.clear();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.cells[i][j].clear();
            }
        }
    }

    public int getCellStatus(int x, int y) {
        if ((x < 0) || (y < 0) || (x > 9) || (y > 9)) {
            return 0;
        }
        return this.cells[x][y].getStatus();
    }

    public int getCellNeighbourStatus(int x, int y) {
        if ((x < 0) || (y < 0) || (x > 9) || (y > 9)) {
            return 1;
        }

        int result = 0;
        result += this.getCellStatus(x - 1, y - 1);
        result += this.getCellStatus(x - 1, y);
        result += this.getCellStatus(x - 1, y + 1);

        result += this.getCellStatus(x, y - 1);
        result += this.getCellStatus(x, y);
        result += this.getCellStatus(x, y + 1);

        result += this.getCellStatus(x + 1, y - 1);
        result += this.getCellStatus(x + 1, y);
        result += this.getCellStatus(x + 1, y + 1);

        return result;
    }

    public Cell[] getNotShootedCells() {
        Cell[] freeCells = Arrays.stream(this.cells)
                .flatMap(Arrays::stream)
                .filter(c -> !c.getWasShot())
                .toArray(Cell[]::new);

        for (Cell c:freeCells) {
            System.out.println(c + " ");

        }
        return freeCells;
    }
}
