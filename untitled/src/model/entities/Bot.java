package model.entities;

import model.cards.CardModel;
import model.cards.DeckModel;
import model.gameStates.PlayingModel;

import java.util.ArrayList;

public class Bot extends Entity implements BotAction {

    public Bot(PlayingModel playingModel) {
        super(playingModel);
    }

    public void turn() {

        hand.getFirst().setHidden(false);

        if (lastActionTime == 0)
            lastActionTime = System.currentTimeMillis();

        delayActionTime -= System.currentTimeMillis() - lastActionTime;
        lastActionTime = System.currentTimeMillis();

        if (handSum >= 17){
            playingModel.nextTurn();
            return;
        }

        if (delayActionTime < 0) {
            addCardToHand();
            hand.getLast().setHidden(false);
            delayActionTime = 700;
        }
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
