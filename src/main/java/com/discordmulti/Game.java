package com.discordmulti;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    protected String name;
    protected ArrayList<Player> players;
    protected HashMap<Long, Player> playerHashMap;

    public Game(String name) {
        this.name = name;
    }

    protected void input(Player player, String message) {
        System.out.println(player.getName() + ":\"" + message + "\"");
    }

    // TODO: Change for discord
    protected void output(String message) {
        System.out.println(message);
    }

    // TODO: Change for discord
    protected void dmPlayer(long id, String message) {
        System.out.println("DM for " + id + ": " + message);
    }

    public void start() {
        reset();
    }

    public void reset() {
        players = new ArrayList<Player>();
        playerHashMap = new HashMap<Long, Player>();
    }

    public void stop() {

    }
}
