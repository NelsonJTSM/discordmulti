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
        while (true) {
            if (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.startsWith("$") && line.length() > 1) {
                    game.input(line.substring(1, line.length()));
                }
            }
        }
    }
}
