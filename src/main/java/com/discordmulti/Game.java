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

            //make sure the player can join the game
            if (players.size() >= maxPlayers) {
                output("Sorry, there are too many players in the game to join");
            }

            if (addPlayer(player.getId(), player.getName())) {
                output(player.getName() + " has successfully joined the game");
            } else {
                output(player.getName() + " was already in the game");
            }
        }

        //handles people starting the game
        if (message.toLowerCase().equals("start") && players.size() >= minPlayers && players.size() <= maxPlayers) {
            start();
        }

        System.out.println(player.getName() + ":\"" + message + "\"");
    }

    protected boolean addPlayer(long id, String name) {

        //if the player isn't already added
        if (!playerHashMap.containsKey(id)) {
            Player player = new Player(name, id);
            players.add(player);
            playerHashMap.put(id, player);

            askForMorePlayers();
            return true;
        } else {
            return false;
        }

    }

    protected void askForMorePlayers() {
        //output a message
        int numOfPlayers = players.size();

        if (numOfPlayers < minPlayers) {
            output("More players need to join before you can start");
        } else if (numOfPlayers >= maxPlayers) {
            output("Starting the game because the maximum number of players have joined");
            start();
        } else {
            output("Either start the game by typing $start, or wait for more players to join");
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
