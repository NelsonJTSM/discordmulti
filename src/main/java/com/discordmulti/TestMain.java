package com.discordmulti;

import java.util.Scanner;

public class TestMain implements Runnable {

    static Game game;
    static Scanner scan;

    public static void main(String[] args) {
        TestMain main = new TestMain();
        main.run();
    }

    public TestMain() {
        game = new Game("test");
        scan = new Scanner(System.in);
    }

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
                        game.input(id, message);
                    } catch (Exception e) {
                        System.err.println("Player ID must be a long");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
