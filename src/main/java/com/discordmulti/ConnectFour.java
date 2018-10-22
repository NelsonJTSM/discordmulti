package com.discordmulti;

import java.util.ArrayList;

public class ConnectFour extends Game {

    ArrayList<boardSpots> playerBoardSpotsConcurrentArrayList = new ArrayList<boardSpots>();
    Board board = new Board(this);

    boolean playFirstPlayer = true;

    public ConnectFour() {
        super("Connect Four", 2, 2);

        //fill the array list of board spots
        for (int i = 0; i < playerBoardSpotsConcurrentArrayList.size(); i++) {
            if (i % 2 == 0) {
                playerBoardSpotsConcurrentArrayList.set(i, boardSpots.Black);
            } else {
                playerBoardSpotsConcurrentArrayList.set(i, boardSpots.Red);
            }
        }

    }

    public void start() {
        super.start();

        output("Welcome to Connect Four!");
        askForNextMove();
    }

    public void stop() {
        super.stop();

    }

    public void input(Player player, String message) {

        super.input(player, message);

        //check to see if it's the correct player
        if (playFirstPlayer) {
            if (!player.equals(players.get(0))) {
                return;
            }
        } else {
            if (!player.equals(players.get(1))) {
                return;
            }
        }

        //check to see if it's a valid play
        int columnNum;
        try {
            columnNum = Integer.parseInt(message);
        } catch(Exception e) {
            output(message + " is not a number, please input a column number for your next move");
            return;
        }
        if (columnNum < 0 || columnNum > 5) {
            output(message + " is not a valid number, please input a column number within the range 1 and " + board.columns + "for your next move");
            return;
        }

        //if the column is full
        if (board.board[board.rows - 1][columnNum] != boardSpots.Empty) {
            output("Column " + message + " is full, please play a different column");
            return;
        }

        int row = -1;
        //make a move
        if (playFirstPlayer) {
            row = board.makePlay(columnNum, playerBoardSpotsConcurrentArrayList.get(0));
        } else {
            row = board.makePlay(columnNum, playerBoardSpotsConcurrentArrayList.get(1));
        }

        //check for game over
        if (board.gameOver(row, columnNum)) {
            //there is a winner and it was !playFirstPlayer
            if (!playFirstPlayer) {
                output("Congratulations " + players.get(0).getName() + ", you have won the game!");
            } else {
                output("Congratulations " + players.get(1).getName() + ", you have won the game!");
            }
            return;
        }
        if (board.isBoardFull()) {
            //it is a tie

            return;
        }
        //ask for next move
        askForNextMove();

    }

    public void askForNextMove() {
        if (playFirstPlayer) {
            output(players.get(0).getName() + " please make a play in the form of $column number");
        } else {
            output(players.get(1).getName() + " please make a play in the form of $column number");
        }

        playFirstPlayer = !playFirstPlayer;
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
    ConnectFour connectFour;

    boardSpots[][] board = new boardSpots[rows][columns];

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
            game.output(toString());
            return true;
        } else {
            return false;
        }

    }

    public String toString() {
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

    public int makePlay(int column, boardSpots player) {

        int row = -1;
        //the column and player are both valid: assumption
        //make the play
        for (int i = 0; i < rows; i++) {
            if (board[i][column] == boardSpots.Empty) {
                board[i][column] = player;
                row = i;
                break;
            }
        }
        printBoard(board);
        String playerName;
        if (player.equals(boardSpots.Black)) {
            playerName = game.players.get(0).getName();
        } else {
            playerName = game.players.get(1).getName();
        }
        game.output(playerName + " has played in column " + column);

        return row;

    }

    public boolean isBoardFull() {
        boolean isBoardFull = true;

        for (int r = 0; r < rows; r++) {
            if (board[r][columns - 1] == boardSpots.Empty) {
                isBoardFull = false;
            }
        }

        return isBoardFull;
    }

    public boolean gameOver(int row, int column) {

        boardSpots piece = board[row][column];

        int horizontalSequence = 0;
        int verticalSequence = 0;
        int leftDiagSequence = 0;
        int rightDiagSequence = 0;

        //horizontal
        for (int i = row; i < rows; i++) {
            if (board[i][column].equals(piece)) {
                horizontalSequence++;
            }
        }
        for (int i = row; i >= 0; i--) {
            if (board[i][column].equals(piece)) {
                horizontalSequence++;
            }
        }


        //vertical
        for (int i = column; i < columns; i++) {
            if (board[row][i].equals(piece)) {
                verticalSequence++
            }
        }
        for (int i = column; i >= 0; i--) {
            if (board[row][i].equals(piece)) {
                verticalSequence++;
            }
        }

        //leftDiag
        for (int r = row, c = column; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c].equals(piece)) {
                leftDiagSequence++;
            }
        }
        for (int r = row, c = column; r < rows && c < columns; r++, c++) {
            if (board[r][c].equals(piece)) {
                leftDiagSequence++;
            }
        }

        //rightDiag
        for (int r = row, c = column; r >= 0 && c < columns; r--, c++) {
            if (board[r][c].equals(piece)) {
                rightDiagSequence++;
            }
        }
        for (int r = row, c = column; r < rows && c >= 0; r++, c--) {
            if (board[r][c].equals(piece)) {
                rightDiagSequence++;
            }
        }

        //check to see if there is four
        if (horizontalSequence >= 4 || verticalSequence >= 4 || leftDiagSequence >= 4 || rightDiagSequence >= 4) {
            return true;
        }


        return false;
    }

}