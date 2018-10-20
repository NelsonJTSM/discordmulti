package com.discordmulti;

import java.util.ArrayList;

public class NinetyNine extends Game {

    private final int MIN_PLAYERS = 2;
    private final int MAX_PLAYERS = 8;

    private ArrayList<Card> cards;

    public NinetyNine() {
        super("99");
        reset();
    }

    @Override
    public void input(Player player, String message) {
        if (message.equals("join")) {
            playerJoin(player);
        } else if (message.equals("start")) {
            checkStart();
        }
    }

    /*

     */
    private void playerJoin(Player player) {
        if (playerHashMap.containsKey(player.getId())) {
            output(playerHashMap.get(player.getId()).getName() + " already exists");
        } else if (players.size() >= MAX_PLAYERS) {
            output("There are too many players (" + MAX_PLAYERS + ") already");
        } else {
            players.add(player);
            playerHashMap.put(player.getId(), player);
            output("Added " + player.getName());
        }
    }

    private void checkStart() {
        if (players.size() < MIN_PLAYERS) {
            output("Not enough players");
        } else {
            start();
        }
    }

    private void setupCards() {
        cards = new ArrayList<Card>();

        char[] suits = {'C', 'D', 'H', 'S'};
        char[] numbers = {'A', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K'};

        for (int s = 0; s < suits.length; s++) {
            for (int n = 0; n < numbers.length; n++) {
                cards.add(new Card(suits[s], numbers[n]));
            }
        }


    }
}

class Card {
    char suit;
    char number;

    public String toString() {
        return "" + suit + number;
    }

    public Card(char suit, char number) {
        this.suit = suit;
        this.number = number;
    }
}
