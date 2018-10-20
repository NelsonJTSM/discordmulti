package com.discordmulti;

public class ConnectFour extends Game {

    public ConnectFour() {
        super("Connect Four");

    }

    public void start() {
        super.start();

        output("Welcome to Connect Four!\n");


    }

    public void stop() {
        super.stop();

    }

    public void input(Player player, String message) {

        super.input(player, message);



    }

    public boolean joinPlayer(String id, String name) {

    }



}

enum boardSpots {
    Black,
    Red,
    Empty
}

class Board {

    int rows = 5;
    int columns = 5;

    Game game;

    boardSpots[][] board = new boardSpots[5][5];

    public Board(Game game) {
        fillBoard(board);
        this.game = game;
    }

    public boolean fillBoard(boardSpots[][] array) {

        if (array.length > 0) {

            for (int r = 0; r < array.length; r++) {
                for (int c = 0; c < array[0].length; c++) {
                    array[r][c] = boardSpots.Empty;
                }
            }
            return true;

        } else {
            return false;
        }

    }

    public boolean printBoard(boardSpots[][] board) {

        if (board.length > 0) {
            game.output(toString(board));
            return true;
        } else {
            return false;
        }

    }

    public String toString(boardSpots[][] board) {
        String output = "";

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                switch (board[r][c]) {
                    case Red: output += "X"; break;
                    case Black: output += "O"; break;
                    case Empty: output += "*"; break;
                }
            }
            if (r < board.length - 1) {
                output += "\n";
            }
        }

        return output;
    }

    public boolean makePlay(int column, boardSpots player) {

        //if the column is not in range
        if (!(column > 0 && column <= columns)) {
            //column is not in range
            return false;
        }

        //if the player is not Black or Red
        if (!(player == boardSpots.Black || player == boardSpots.Red)) {
            //wrong player
            return false;
        }

        //the column is full
        if (board[rows - 1][column] != boardSpots.Empty) {
            //column is full
            return false;
        }

        //the column and player are both valid
        //make the play
        for (int i = 0; i < rows; i++) {
            if (board[i][column] == boardSpots.Empty) {
                board[i][column] = player;
            }
        }

    }

}