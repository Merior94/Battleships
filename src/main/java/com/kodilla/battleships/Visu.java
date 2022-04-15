package com.kodilla.battleships;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Visu {
    public Game game;
    private VisBoard visPlayerBoard;
    private VisBoard visEnemyBoard;

    public Visu(Game game) {
        this.game = game;
        this.visPlayerBoard = new VisBoard(false,game, this);
        this.visEnemyBoard = new VisBoard(true,game,this);
    }

    public void launch(Stage stage) {
        Scene scene = new Scene(createContent());
        stage.setTitle("Battleships");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void showWinner(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The End");
        alert.setHeaderText("We have a winner!");
        alert.setContentText(winner + " wins!");
        alert.showAndWait();
    }

    private Pane createContent(){
        GridPane root = new GridPane();
        root.setPrefSize(640,320);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5));
        root.setHgap(5.5);
        root.setVgap(5.5);
        //root.setGridLinesVisible(true);

//        Button okBtn = new Button("OK");
//        Button closeBtn = new Button("Close");
//        root.add(okBtn, 0, 3);
//        root.add(closeBtn, 1, 3);

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
        root.add(createMenuBar(),0,0);

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

//        MenuItem menuItem1 = new MenuItem("...");
//        menuItem1.setOnAction(e -> {
//            //this.startGame();
//        });
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

        //menuGame.getItems().add(menuItem1);
        menuGame.getItems().add(menuItem2);
        menuGame.getItems().add(menuItem3);
        menuBar.getMenus().add(menuGame);

        return menuBar;
    }

    public void refresh(){
        visEnemyBoard.refresh();
        visPlayerBoard.refresh();
    }

}
