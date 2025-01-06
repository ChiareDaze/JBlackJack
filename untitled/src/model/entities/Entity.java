package model.entities;

import model.cards.CardModel;
import model.cards.DeckModel;
import model.gameStates.PlayingModel;
import model.utilz.Constants.EntityNames;

import java.util.ArrayList;

/**
 * The Entity class represents an abstract entity in the game, which holds a hand of cards and performs actions based on the game state.
 */
public abstract class Entity {

    protected PlayingModel playingModel;
    protected DeckModel deckModel = DeckModel.getInstace();
    protected ArrayList<CardModel> deck = deckModel.getDeck();
    protected ArrayList<CardModel> hand = new ArrayList<CardModel>();
    protected int handSum = 0;
    protected int aceCount = 0;
    protected long lastActionTime = 0;
    protected long delayActionTime = 700;
    protected boolean blackJack = false;
    protected EntityNames entityName;

    /**
     * Constructs an Entity object with the specified playing model and entity name.
     *
     * @param playingModel the playing model associated with the entity
     * @param entityName the name of the entity
     */
    public Entity(PlayingModel playingModel, EntityNames entityName) {
        this.playingModel = playingModel;
        this.entityName = entityName;
        buildHand();
    }

    /**
     * Builds the initial hand of the entity.
     * This method must be implemented by subclasses.
     */
    protected abstract void buildHand();

    /**
     * Reduces the hand sum by 10 for each ace in the hand if the hand sum exceeds 21.
     *
     * @return the adjusted hand sum
     */
    public int reduceAce() {
        while (handSum > 21 && aceCount > 0) {
            handSum -= 10;
            aceCount--;
        }
        return handSum;
    }

    /**
     * Adds a card to the entity's hand and adjusts the hand sum and ace count accordingly.
     */
    public void addCardToHand() {
        CardModel cardModel = deck.remove(deck.size() - 1);
        handSum += cardModel.getNumericalValue();
        if (cardModel.isAce()) aceCount++;
        hand.add(cardModel);
        reduceAce();
    }

    /**
     * Checks if the entity has a blackjack (an ace and a figure card).
     *
     * @return true if the entity has a blackjack, false otherwise
     */
    public boolean isBlackJack() {
        if (hand.get(0).isAce() && hand.get(1).isFigure() || hand.get(0).isFigure() && hand.get(1).isAce()) {
            blackJack = true;
        }
        return blackJack;
    }

    /**
     * Gets the sum of the entity's hand.
     *
     * @return the sum of the entity's hand
     */
    public int getHandSum() {
        return handSum;
    }

    /**
     * Gets the entity's hand of cards.
     *
     * @return the entity's hand of cards
     */
    public ArrayList<CardModel> getHand() {
        return hand;
    }
}
