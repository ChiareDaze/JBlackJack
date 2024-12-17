package model.entities;

import model.cards.CardModel;
import model.cards.DeckModel;

import java.util.ArrayList;

public class Bot extends Entity implements BotAction {

    public void turn() {

    }

    protected void buildHand() {
        ArrayList<CardModel> deck = deckModel.getDeck();
        for (int i = 0; i < 2; i++) {
            CardModel card = deck.remove(deck.size() - 1);
            handSum += card.getNumericalValue();
            if (card.isAce()) aceCount++;
            hand.add(card);
        }
    }

}
