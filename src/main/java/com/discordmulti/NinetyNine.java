package com.discordmulti;

import java.util.ArrayList;
import java.util.HashMap;

public class NinetyNine extends Game {

    ArrayList<NNPlayer> players;
    ArrayList<NNPlayer> playersAlive;
    HashMap<Long, NNPlayer> playerHashMap;

    private final int MIN_PLAYERS = 2;
    private final int MAX_PLAYERS = 8;

    private ArrayList<Card> cards;
    private int currentSum;
    private int currentPlayerIndex;
    private int amountChange;

    public NinetyNine() {
        super("99");
        reset();
    }

    @Override
    public void start() {
        super.start();
        reset();

        for (NNPlayer player : players) {
            for (int i = 0; i < player.playerCards.length; i++) {
                player.playerCards[i] = cards.remove(0);
            }
        }
    }

    public void gameLoop() {
        // Game loop
        while (players.size() > 1) {
            NNPlayer currentPlayer = players.get(currentPlayerIndex);
            if (!hasPossibleHand(players.get(currentPlayerIndex).playerCards, currentSum)) {
                players.remove(currentPlayerIndex);
                output("Player " + currentPlayer.getName() + " does not have any playable cards");
            } else {

            }

            currentPlayerIndex = (currentPlayerIndex+amountChange)%(players.size());
        }

        end();
    }

    public void end() {
        output(players.get(0).getName() + " won!!");
        stop();
    }

    @Override
    public void reset() {
        super.reset();
        players = new ArrayList<NNPlayer>();
        playerHashMap = new HashMap<Long, NNPlayer>();
        currentSum = 0;
        currentPlayerIndex = 0;
        amountChange = 1;
        setupCards();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void input(Player player, String message) {
        if (message.equals("join")) {
            playerJoin(player);
        } else if (message.equals("start")) {
            checkStart();
        } else {
            output("Unknown command");
        }
    }

    public boolean hasPossibleHand(Card[] cards, int sum) {
        for (int c = 0; c < cards.length; c++) {
            if (sum + getMinValue(cards[c]) <= 99)
                return true;
        }
        return false;
    }

    public int getMinValue(Card card) {
        if (card.number == 'A')
            return 1;
        if (card.number == '2')
            return 2;
        if (card.number == '3')
            return 0;
        if (card.number == '4')
            return 0;
        if (card.number == '5')
            return 5;
        if (card.number == '6')
            return 0;
        if (card.number == '7')
            return 7;
        if (card.number == '8')
            return 8;
        if (card.number == '9')
            return 0;
        if (card.number == '0')
            return -1;

        return 10;
    }

    /*

     */
    private void playerJoin(Player player) {
        if (playerHashMap.containsKey(player.getId())) {
            output(playerHashMap.get(player.getId()).getName() + " already exists");
        } else if (players.size() >= MAX_PLAYERS) {
            output("There are too many players (" + MAX_PLAYERS + ") already");
        } else {
            NNPlayer newPlayer = new NNPlayer(player.getName(), player.getId());
            players.add(newPlayer);
            playerHashMap.put(player.getId(), newPlayer);
            output("Added " + newPlayer.getName());
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

class NNPlayer extends Player {

    Card[] playerCards = new Card[3];

    public NNPlayer(String name, long id) {
        super(name, id);
    }
}
