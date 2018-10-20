package com.discordmulti;

import java.util.ArrayList;

public class Game {

    protected String name;
    protected ArrayList<Player> players;

    public Game(String name) {
        this.name = name;
    }

    protected void input(String message) {
        output(message);
    }

    // TODO: Change for discord
    protected void output(String message) {
        System.out.println(message);
    }

    public void start() {

    }

    public void reset() {

    }

    public void stop() {

    }
}
