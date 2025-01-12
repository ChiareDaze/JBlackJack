package model.cards;

import java.util.ArrayList;
import java.util.Random;

import model.utilz.Constants.CardType;
import static model.utilz.Constants.CardType.*;


/**
 * The DeckModel class represents a deck of cards and provides methods to build and shuffle the deck.
 */
public class DeckModel {

    private static DeckModel instance;
    private Random random = new Random(); // shuffle deck
    private ArrayList<CardModel> deck;

    /**
     * Private constructor to prevent instantiation.
     * Builds and shuffles the deck upon creation.
     */
    private DeckModel() {
        buildDeck();
        shuffleDeck();
    }

    /**
     * Returns the singleton instance of the DeckModel.
     *
     * @return the singleton instance of the DeckModel
     */
    public static DeckModel getInstace() {
        if (instance == null) {
            instance = new DeckModel();
        }
        return instance;
    }

    /**
     * Builds the deck by creating cards of all values and types.
     */
    public void buildDeck() {
        deck = new ArrayList<>();
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        CardType[] types = {H, D, C, S};

        // Iterate through all values and types to create a deck of cards
        for (String value : values) {
            for (CardType type : types) {
                deck.add(new CardModel(value, type));
            }
        }
    }

    /**
     * Shuffles the deck by randomly swapping cards.
     */
    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size()); // gives a random number between 0 and 51
            CardModel currCardModel = deck.get(i);
            CardModel randomCardModel = deck.get(j);
            deck.set(i, randomCardModel);
            deck.set(j, currCardModel);
        }
    }

    /**
     * Gets the deck of cards.
     *
     * @return the deck of cards
     */
    public ArrayList<CardModel> getDeck() {
        return deck;
    }
}