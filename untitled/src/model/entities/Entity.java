package model.entities;

import model.cards.CardModel;
import model.cards.DeckModel;
import model.gameStates.PlayingModel;
import model.utilz.Constants.EntityNames;

import java.util.ArrayList;

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

    public Entity(PlayingModel playingModel, EntityNames entityName) {
        this.playingModel = playingModel;
        this.entityName = entityName;
        buildHand();
    }

    protected abstract void buildHand();

    public int reduceAce() {
        while (handSum > 21 && aceCount > 0) {
            handSum -= 10;
            aceCount--;
        }
        return handSum;
    }

    public void addCardToHand() {
        CardModel cardModel = deck.remove(deck.size() - 1);
        handSum += cardModel.getNumericalValue();
        if (cardModel.isAce()) aceCount++;
        hand.add(cardModel);
        reduceAce();
    }

    public boolean isBlackJack() {
        if (hand.get(0).isAce() && hand.get(1).isFigure() || hand.get(0).isFigure() && hand.get(1).isAce()) {
            blackJack = true;
        }
        return blackJack;
    }

    public int getAceCount() {
        return aceCount;
    }

    public int getHandSum() {
        return handSum;
    }

    public ArrayList<CardModel> getHand() {
        return hand;
    }
}
