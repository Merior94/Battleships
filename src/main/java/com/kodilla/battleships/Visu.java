package com.kodilla.battleships;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Visu {
    public Game game;
    private VisBoard visPlayerBoard;
    private VisBoard visEnemyBoard;
    private Stage stage;
    private Scene sceneMenu;
    private Scene sceneGame;
    private Scene sceneRanking;
    private Scene sceneSettings;
    private Text scoreText;

    public Visu(Stage stage) {
        this.stage = stage;
        this.game = new Game();
        this.scoreText = new Text();
        this.visPlayerBoard = new VisBoard(false,game, this);
        this.visEnemyBoard = new VisBoard(true,game,this);
    }

    public void launch() {
        stage.setTitle("Battleships");
        stage.setResizable(false);

        this.sceneGame = new Scene(createContent());
        this.sceneMenu = new Scene(createMenuContent());
        //this.sceneGame = new Scene(createRankingContent());
        //this.sceneGame = new Scene(createSettingsContent());

        this.stage.setScene(this.sceneMenu);

        stage.show();
    }

    public void showWinner(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The End");
        alert.setHeaderText("We have a winner!");
        alert.setContentText(winner + " wins!");
        alert.showAndWait();
    }

    public void confirmExit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK){
            System.exit(0);
        }
    }

    private Pane createMenuContent(){
        GridPane root = new GridPane();
        root.setPrefSize(640,320);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5));
        root.setHgap(5.5);
        root.setVgap(5.5);

        Button btn0 = new Button("Continue");
        Button btn1 = new Button("New game");
        Button btn2 = new Button("Rankings");
        Button btn3 = new Button("Settings");
        Button btn4 = new Button("Exit");

        btn0.setMinWidth(120);
        btn1.setMinWidth(120);
        btn2.setMinWidth(120);
        btn2.setStyle("-fx-background-color: DARKGREY; ");
        btn3.setMinWidth(120); //Color.DARKGREY
        btn3.setStyle("-fx-background-color: DARKGREY; ");
        btn4.setMinWidth(120);

        root.add(btn0, 0, 1);   //If there is game started
        root.add(btn1, 0, 2);
        root.add(btn2, 0, 3);
        root.add(btn3, 0, 4);
        root.add(btn4, 0, 6);

        btn0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneGame);
                refresh();
            }
        });

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneGame);
                game.newGame();
                refresh();
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //stage.setScene(sceneRanking);
            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //stage.setScene(sceneSettings);
            }
        });

        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                confirmExit();
            }
        });

        return root;
    }

    private Pane createContent(){
        GridPane root = new GridPane();
        root.setPrefSize(640,320);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5));
        root.setHgap(5.5);
        root.setVgap(5.5);
        //root.setGridLinesVisible(true);

//        Text scoreText = new Text();
//        scoreText.setFont(new Font(20));
//        scoreText.setText(game.getScore());
//        root.add(scoreText,1,0,1,1);

        //Texts
        Text myBoardText = new Text();
        myBoardText.setFont(new Font(20));
        myBoardText.setText("My board");
        root.add(myBoardText,0,1,1,1);

        Text enemyBoardText = new Text();
        enemyBoardText.setFont(new Font(20));
        enemyBoardText.setText("Enemy board");
        root.add(enemyBoardText,1,1,1,1);

        //Menu
        //root.add(createMenuBar(),0,0);
        Button menuBtn = new Button("Menu");
        root.add(menuBtn, 0, 0);
        menuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneMenu);
            }
        });

        //Score
        this.scoreText.setFont(new Font(20));
        root.add(this.scoreText,1,0,1,1);

        //Boards
        //this.visMyBoard = new VisBoard(false,game, this);
        System.out.println(visPlayerBoard);
        root.add(visPlayerBoard.board,0,2);

        //VisBoard visEnemyBoard = new VisBoard(true,game,this);
        root.add(visEnemyBoard.board,1,2);

        return root;
    }

    private MenuBar createMenuBar(){
        MenuBar menuBar = new MenuBar();

        Menu menuGame = new Menu("Game");

        MenuItem menuItem1 = new MenuItem("Main menu");
        menuItem1.setOnAction(e -> {
            this.stage.setScene(this.sceneMenu);;
        });
        MenuItem menuItem2 = new MenuItem("New game");
        menuItem2.setOnAction(e -> {
            this.game.newGame();
            this.refresh();
            System.out.println("Place ships");
        });
        MenuItem menuItem3 = new MenuItem("Exit game");
        menuItem3.setOnAction(e -> {
            this.game.exit();
        });

        menuGame.getItems().add(menuItem1);
        menuGame.getItems().add(menuItem2);
        menuGame.getItems().add(menuItem3);
        menuBar.getMenus().add(menuGame);

        return menuBar;
    }

    public void refresh(){
        this.visEnemyBoard.refresh();
        this.visPlayerBoard.refresh();
        this.scoreText.setText(this.game.getScore());
    }

}
