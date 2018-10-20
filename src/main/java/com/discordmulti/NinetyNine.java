package com.discordmulti;

import java.util.ArrayList;

public class NinetyNine extends Game {

    private ArrayList<Card> cards;

    public NinetyNine() {
        super("99");
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

    public Card(char suit, char number) {
        this.suit = suit;
        this.number = number;
    }
}
