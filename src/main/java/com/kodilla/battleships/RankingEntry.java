package com.kodilla.battleships;

import java.time.LocalDateTime;

public class RankingEntry implements java.io.Serializable{
    private final int playerScore;
    private final int enemyScore;
    private final LocalDateTime dt;

    RankingEntry(int playerScore, int enemyScore){
        this.playerScore = playerScore;
        this.enemyScore = enemyScore;
        this.dt = LocalDateTime.now();
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getEnemyScore() {
        return enemyScore;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    @Override
    public String toString() {
        return "RankingEntry{" +
                "playerScore=" + playerScore +
                ", enemyScore=" + enemyScore +
                ", dt=" + dt +
                '}';
    }
}
