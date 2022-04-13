package com.kodilla.battleships;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

    private Pane createContent(){
        //BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        //BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        //Background background = new Background(backgroundImage);

        GridPane root = new GridPane();
        root.setPrefSize(640,320);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5));
        root.setHgap(5.5);
        root.setVgap(5.5);
        //grid.setBackground(background);
        root.setGridLinesVisible(true);

//        ColumnConstraints cons1 = new ColumnConstraints();
//        cons1.setHgrow(Priority.NEVER);
//        root.getColumnConstraints().add(cons1);
//
//        ColumnConstraints cons2 = new ColumnConstraints();
//        cons2.setHgrow(Priority.ALWAYS);
//
//        root.getColumnConstraints().addAll(cons1, cons2);
//
//        RowConstraints rcons1 = new RowConstraints();
//        rcons1.setVgrow(Priority.NEVER);
//
//        RowConstraints rcons2 = new RowConstraints();
//        rcons2.setVgrow(Priority.ALWAYS);
//
//        root.getRowConstraints().addAll(rcons1, rcons2);

        //ImageView img = new ImageView(card);
        //cards.getChildren().add(img);
        //grid.add(cards, 1, 2, 2, 1);

//        Button okBtn = new Button("OK");
//        Button closeBtn = new Button("Close");
//        root.add(okBtn, 0, 3);
//        root.add(closeBtn, 1, 3);

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

        MenuItem menuItem1 = new MenuItem("...");
        menuItem1.setOnAction(e -> {
            //this.startGame();
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
        System.out.println("refresh");
        visEnemyBoard.refresh();
        visPlayerBoard.refresh();
    }

}
