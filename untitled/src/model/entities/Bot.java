package model.entities;

import model.cards.CardModel;
import model.utilz.Constants.EntityNames;
import model.gameStates.PlayingModel;

import java.util.ArrayList;

public class Bot extends Entity implements BotAction {

    public Bot(PlayingModel playingModel, int botNumber) {
        EntityNames botName = null;
        switch  (botNumber) {
            case 1 -> botName = EntityNames.BOT1;
            case 2 -> botName = EntityNames.BOT2;
            case 3 -> botName = EntityNames.BOT3;
        }
        super(playingModel, botName);
    }

    public void turn() {

        hand.getFirst().setHidden(false);

        if (lastActionTime == 0)
            lastActionTime = System.currentTimeMillis();

        delayActionTime -= System.currentTimeMillis() - lastActionTime;
        lastActionTime = System.currentTimeMillis();

        if (handSum >= 21){
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
        handSum = 0;
        for (int i = 0; i < 2; i++) {
            CardModel card = deck.remove(deck.size() - 1);
            handSum += card.getNumericalValue();
            if (card.isAce()) aceCount++;
            hand.add(card);
        }
        isBlackJack();
    }
}