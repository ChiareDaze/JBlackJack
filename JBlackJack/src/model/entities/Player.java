package model.entities;

import model.cards.CardModel;
import model.gameStates.PlayingModel;

import java.util.ArrayList;
import model.utilz.Constants.EntityNames;


/**
 * The Player class represents a player entity in the game, which performs actions based on the game state.
 */
public class Player extends Entity {

    /**
     * Constructs a Player object with the specified playing model.
     *
     * @param playingModel the playing model associated with the player
     */
    public Player(PlayingModel playingModel) {
        super(playingModel, EntityNames.PLAYER);
    }

    /**
     * Builds the player's initial hand by drawing two cards from the deck and calculating the hand sum.
     */
    protected void buildHand() {
        ArrayList<CardModel> deck = deckModel.getDeck();
        for (int i = 0; i < 2; i++) {
            CardModel card = deck.remove(deck.size() - 1);
            handSum += card.getNumericalValue();
            if (card.isAce()) aceCount++;
            hand.add(card);
        }
        isBlackJack();
    }

    /**
     * Adds a card to the player's hand.
     */
    public void hit() {
        addCardToHand();
    }
}