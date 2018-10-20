package com.discordmulti;

public class ConnectFour extends Game {

    public ConnectFour() {
        super("Connect Four");
    }

    public void start() {
        super.start();




    }

    public void stop() {
        super.stop();

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

    //public boolean makePlay(int column, boardSpots) {

    //}

}