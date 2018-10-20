package com.discordmulti;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    protected String name;

    int minPlayers;
    int maxPlayers;

    ArrayList<Player> players = new ArrayList<Player>();
    HashMap<Long, Player> playerHashMap = new HashMap<Long, Player>();

    public Game(String name, int minPlayers, int maxPlayers) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    protected void input(Player player, String message) {

        //handles people joining the game
        if (message.toLowerCase().equals("join")) {
            if (addPlayer(player.getId(), player.getName())) {
                output(player.getName() + " has successfully joined the game");
            } else {
                output(player.getName() + " was already in the game");
            }
        }

        System.out.println(player.getName() + ":\"" + message + "\"");
    }

    protected boolean addPlayer(long id, String name) {

        //if the player isn't already added
        if (!playerHashMap.containsKey(id)) {
            Player player = new Player(name, id);
            players.add(player);
            playerHashMap.put(id, player);

            return true;
        } else {
            return false;
        }

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

    }

    public void stop() {

    }
}
