package com.kodilla.battleships;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private boolean isEnemy;
    private Cell[][] cells;
    private List<Ship> ships;

    public Board(boolean isEnemy) {
        this.isEnemy = isEnemy;
        this.ships = new ArrayList<>();
        this.cells = new Cell[10][10];
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                this.cells[i][j] = new Cell(i,j);
            }
        }
    }

    public int shoot(int x, int y){
        return this.cells[x][y].shoot();
    }

    public int[][] getBoard(){
        int[][] board = new int[10][10];
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                board[i][j] = this.cells[i][j].getStatus();
            }
        }
        return board;
    }

    public boolean placeShip(int type, int x, int y, char orientation){
        //check if possible
        if (orientation=='v'){
            Ship ship = new Ship(type);
            this.ships.add(ship);
            for (int i=0; i<type; i++) {
                this.cells[x][y+i].setShip(ship);
            }
        }
        if (orientation=='h'){
            Ship ship = new Ship(type);
            for (int i=0; i<type; i++){
                this.cells[x+i][y].setShip(ship);
            }
        }
        return true;
    }

    public int getNumberOfShips() {
        return ships.size();
    }

    public void clear(){
        this.ships.clear();
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                this.cells[i][j].clear();
            }
        }
    }

    public int getCellStatus(int x, int y){
        return this.cells[x][y].getStatus();
    }
}
