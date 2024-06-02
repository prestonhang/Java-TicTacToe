package com.tictactoe;

import java.util.Scanner;

import javafx.scene.control.Button;

/**
 * JavaFX App
 */

// Tic Tac Toe

/*
 * initialize() - setup board to be all empty
 * printBoard() - print current conditions of board
 * isValidMove() - makes sure user's move is within bounds and spot is empty
 * winningMove() - check if user's move is a win for tic tac toe
 * boardFull() - check if board is all full
 */

public class TicTacToe {

    private char[][] board;
    private boolean gameDone;
    private char currentPlayer;

    // Constructor
    public TicTacToe(){
        this.board = new char[3][3];
        initialize(this.board);

        this.gameDone = false;
        this.currentPlayer = 'X';
    }
    public static void main(String[] args) {
        System.out.println("Hello World");

        TicTacToe game = new TicTacToe();

        startGame(game);
    }

    public char getCurrentPlayer(TicTacToe game) {
        return game.currentPlayer;
    }

    public char[][] getBoard(TicTacToe game) {
        return game.board;
    }

    public String Status(TicTacToe game, int i, int j, Button button) {

        if (isValidMove(i,j,game.board) == false) {
            return "Invalid Move!";
        }

        setMove(game.currentPlayer, i, j, game.board);
        button.setText(Character.toString(game.getCurrentPlayer(game)));

        if (winningMove(game.currentPlayer, game.board)) {
            return "Winner! - " + game.currentPlayer;
        }

        else if (boardFull(board)) {
            return "Tie Game!";
        }
        

        return swapPlayers(game);

    }

    private String swapPlayers(TicTacToe game){
        if (game.currentPlayer == 'X') { 
            game.currentPlayer = 'O'; 
        }
        else {
            game.currentPlayer = 'X';
        }
        return "Current Player: " + game.currentPlayer;
    }    

    private static void startGame(TicTacToe game) {
        Scanner read = new Scanner(System.in);

        while (game.gameDone == false){

            System.out.println("Current Player: " + game.currentPlayer);    
            printBoard(game.board);

            System.out.println("Select Row for next move: ");
            int i = read.nextInt();

            System.out.println("Select Column for next move: ");
            int j = read.nextInt();

            while (game.isValidMove(i, j, game.board) == false) {
                System.out.println("ERROR: Invalid move!");
                System.out.println("Select Row for next move: ");
                int new_i = read.nextInt();

                System.out.println("Select Column for next move: ");
                int new_j = read.nextInt();

                i = new_i;
                j = new_j;
            }
            
            game.setMove(game.currentPlayer, i, j, game.board);

            if (game.winningMove(game.currentPlayer, game.board)) {
                printBoard(game.board);
                System.out.println("Game Win for " + game.currentPlayer);
                game.gameDone = true;
            }

            else if (game.boardFull(game.board)) {
                printBoard(game.board);
                System.out.println("Tie! Restart Game?");
                game.gameDone = true;
            }
            else {
                if (game.currentPlayer == 'X') { 
                    game.currentPlayer = 'O'; 
                }
                else {
                    game.currentPlayer = 'X';
                }
            }
        }
        read.close();

    }

    private static void initialize(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                board[i][j] = '-';
            }
        }
    }
    private static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------------");
            System.out.println("|   " + board[i][0] + "    " + board[i][1] + "    " + board[i][2] + "   |");
        }
        System.out.println("--------------------");
    }
    public boolean isValidMove(int i, int j, char[][] board){
        // Move must be within bounds and empty
        if (0 <= i && i <= 2 && 0 <= j && j <= 2 && board[i][j] == '-') {
            return true;
        }
        else { return false;}
    }

    public void setMove(char player, int i, int j, char[][] board){
        board[i][j] = player;
    }

    private boolean winningMove(char currentPlayer, char[][] board){
        // Check for 3 in a row, 3 in a column, or 3 in a diagional
        for (int i = 0; i < 3; i++) {
            // Checking Rows
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            // Checking Columns
            else if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        // Checking Diagionals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) || 
        (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) 
        {  
            return true;
        }

        else {
            return false;
        }
    }

    private boolean boardFull(char[][] board){
        // Check for 3 in a row, 3 in a column, or 3 in a diagional
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
} // Class TicTacToe
