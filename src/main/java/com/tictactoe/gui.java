package com.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class gui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage Home) {
        Home.setTitle("TicTacToe");

        int buttonHeight = 200;
        int buttonWidth = 200;

        TicTacToe game = new TicTacToe();
        GridPane root = new GridPane();
        Label status = new Label("Current Player: " + Character.toString(game.getCurrentPlayer(game)));
        VBox vbox = new VBox();
        Button restart_button = new Button("Click to Restart");
        restart_button.setOnAction(event -> {
            reset_tictactoe(game, root);
            game.reset(game);
            status.setText("Current Player: " + Character.toString(game.getCurrentPlayer(game)));
        });

        status.setStyle("-fx-font-size: 48px;");
        vbox.setSpacing(10);
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefHeight(buttonHeight);
                button.setPrefWidth(buttonWidth);
                button.setStyle("-fx-font-size: 50px;");

                root.setRowIndex(button, i);
                root.setColumnIndex(button, j);
                root.getChildren().add(button);

                int move_row = i;
                int move_column = j;

                button.setOnAction(event -> {
                    status.setText(game.Status(game, move_row, move_column, button));
                });
            }
        }

        vbox.getChildren().add(status);
        vbox.getChildren().add(root);
        vbox.getChildren().add(restart_button);

        Scene scene = new Scene(vbox, 600, 750);
        Home.setScene(scene);
        Home.show();

    }
    private void reset_tictactoe(TicTacToe game, GridPane grid) {
        for(int i = 0; i < grid.getChildren().size(); i++){
            Button newButton = (Button) grid.getChildren().get(i);
            newButton.setText(" ");
        }

    }
        
}
