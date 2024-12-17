package model.entities;

import model.cards.CardModel;
import model.cards.DeckModel;

import java.util.ArrayList;

public abstract class Entity {

    protected DeckModel deckModel = DeckModel.getInstace();
    protected ArrayList<CardModel> deck = deckModel.getDeck();
    protected ArrayList<CardModel> hand = new ArrayList<CardModel>();
    protected int handSum = 0;
    protected int aceCount = 0;

    protected abstract void buildHand();

    public int reduceAce(){
        while (handSum > 21 && aceCount > 0) {
            handSum -= 10;
            aceCount--;
        }
        return handSum;
    }

    public void addCardToHand() {
        CardModel cardModel = deck.remove(deck.size()-1);
        handSum += cardModel.getNumericalValue();
        if (cardModel.isAce()) aceCount++;
        hand.add(cardModel);
        reduceAce();
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
