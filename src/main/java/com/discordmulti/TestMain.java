package com.discordmulti;

import java.util.Scanner;

public class TestMain implements Runnable {

    Game game;
    Scanner scan;

    public static void main(String[] args) {
        TestMain main = new TestMain();

        if (args.length != 1) {
            System.out.println("Correct usage: TestMain checkers");
        } else {
            String gameName = args[0];

            if (gameName.equals("99")) {
                main.game = new NinetyNine();
            } else if (gameName.equals("connect4")) {
                main.game = new ConnectFour();
            } else {
                System.out.println("Game not found");
                System.exit(1);
            }
        }

        main.run();
    }

    public TestMain() {
        game = new NinetyNine();
        scan = new Scanner(System.in);
    }

    //TODO: - make a method for when a user quits

    public void run() {
        String line = "";

        while (!line.equals("$quit")) {
            if (scan.hasNext()) {
                line = scan.nextLine();
                int dollarIndex = line.indexOf('$');

                if (dollarIndex > 0 && line.length() > dollarIndex) {
                    String playerId = line.substring(0, dollarIndex);
                    String message = line.substring(dollarIndex+1);

                    try {
                        long id = Long.parseLong(playerId);
                        game.input(new Player("Player #" + id, id), message);
                    } catch (Exception e) {
                        System.err.println("Player ID must be a long");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
