package model.entities;

import model.cards.CardModel;
import model.gameStates.PlayingModel;

import java.util.ArrayList;
import model.utilz.Constants.EntityNames;


public class Player extends Entity {

    public Player(PlayingModel playingModel) {
        super(playingModel, EntityNames.PLAYER);
    }

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

    public void hit(){
        addCardToHand();
    }
}

