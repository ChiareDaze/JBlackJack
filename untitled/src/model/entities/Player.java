package model.entities;

import model.cards.CardModel;
import model.gameStates.PlayingModel;

import java.util.ArrayList;


public class Player extends Entity {

    public Player(PlayingModel playingModel) {
        super(playingModel);
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

    public void hit(){
        addCardToHand();
    }

    public void stay(){

    }
}

