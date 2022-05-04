package com.kodilla.battleships;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Visu {
    public Game game;
    private final VisBoard visPlayerBoard;
    private final VisBoard visEnemyBoard;
    private final Stage stage;
    private Scene sceneMenu;
    private Scene sceneGame;
    private Scene sceneRanking;
    private final Text scoreText;
    Text[] ranking;

    public Visu(Stage stage) {
        this.stage = stage;
        this.game = new Game();
        this.scoreText = new Text();
        this.visPlayerBoard = new VisBoard(false, game, this);
        this.visEnemyBoard = new VisBoard(true, game, this);
        this.ranking = new Text[10];
    }

    public void launch() {
        stage.setTitle("Battleships");
        stage.setResizable(false);

        this.sceneGame = new Scene(createContent());
        this.sceneMenu = new Scene(createMenuContent());
        this.sceneRanking = new Scene(createRankingContent());

        this.stage.setScene(this.sceneMenu);

        stage.show();
    }

    public void showWinner(String winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The End");
        alert.setHeaderText("We have a winner!");
        alert.setContentText(winner + " wins!");
        alert.showAndWait();
    }

    public void confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        if (alert.showAndWait().isPresent()) {
            if (alert.showAndWait().get() == ButtonType.OK) {
                System.exit(0);
            }
        }
    }

    private Pane createMenuContent() {
        GridPane root = new GridPane();
        root.setPrefSize(640, 320);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5));
        root.setHgap(5.5);
        root.setVgap(5.5);

        Button btn0 = new Button("Continue");
        Button btn1 = new Button("New game");
        Button btn2 = new Button("Rankings");
        Button btn4 = new Button("Exit");

        btn0.setMinWidth(120);
        btn1.setMinWidth(120);
        btn2.setMinWidth(120);

        btn4.setMinWidth(120);

        root.add(btn0, 0, 1);   //If there is game started
        root.add(btn1, 0, 2);
        root.add(btn2, 0, 3);

        root.add(btn4, 0, 6);

        btn0.setOnAction(event -> {
            stage.setScene(sceneGame);
            refresh();
        });

        btn1.setOnAction(event -> {
            stage.setScene(sceneGame);
            game.newGame();
            refresh();
        });

        btn2.setOnAction(event -> {
            stage.setScene(sceneRanking);
            refreshRanking();
        });

        btn4.setOnAction(event -> confirmExit());

        return root;
    }

    private Pane createContent() {
        GridPane root = new GridPane();
        root.setPrefSize(640, 320);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5));
        root.setHgap(5.5);
        root.setVgap(5.5);

        //Texts
        Text myBoardText = new Text();
        myBoardText.setFont(new Font(20));
        myBoardText.setText("My board");
        root.add(myBoardText, 0, 1, 1, 1);

        Text enemyBoardText = new Text();
        enemyBoardText.setFont(new Font(20));
        enemyBoardText.setText("Enemy board");
        root.add(enemyBoardText, 1, 1, 1, 1);

        //Menu
        Button menuBtn = new Button("Menu");
        root.add(menuBtn, 0, 0);
        menuBtn.setOnAction(event -> stage.setScene(sceneMenu));

        //Score
        this.scoreText.setFont(new Font(20));
        root.add(this.scoreText, 1, 0, 1, 1);

        //Boards
        root.add(visPlayerBoard.board, 0, 2);
        root.add(visEnemyBoard.board, 1, 2);

        return root;
    }

    private Pane createRankingContent() {
        GridPane root = new GridPane();
        root.setPrefSize(640, 320);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5));
        root.setHgap(5.5);
        root.setVgap(5.5);

        //Texts
        Text myTitle = new Text();
        myTitle.setFont(new Font(20));
        myTitle.setText("Ranking");
        root.add(myTitle, 0, 1, 1, 1);


        for (int i = 0; i < ranking.length; i++) {
            ranking[i] = new Text();
            ranking[i].setFont(new Font(20));
            root.add(ranking[i], 1, 2 + i);
        }

        //Menu
        Button menuBtn = new Button("Menu");
        root.add(menuBtn, 0, 0);
        menuBtn.setOnAction(event -> stage.setScene(sceneMenu));

        return root;
    }

    public void refreshRanking() {
        this.game.loadRanking();
        List<RankingEntry> rank = this.game.getRanking();
        for (int i = 0; i < rank.size(); i++) {
            this.ranking[i].setText(i + 1 + ". "
                    + "Player " + rank.get(i).getPlayerScore() + " : "
                    + rank.get(i).getEnemyScore() + " Enemy - at "
                    + rank.get(i).getDt().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }
    }

    public void refresh() {
        this.visEnemyBoard.refresh();
        this.visPlayerBoard.refresh();
        this.scoreText.setText(this.game.getScore());
    }

}
