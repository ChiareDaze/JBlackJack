package model.entities;

import java.util.ArrayList;

import model.cards.CardModel;
import model.utilz.Constants.EntityNames;
import model.gameStates.PlayingModel;


/**
 * The Bot class represents a bot entity in the game, which performs actions based on the game state.
 */
public class Bot extends Entity implements BotAction {

    /**
     * Constructs a Bot object with the specified playing model and bot number.
     *
     * @param playingModel the playing model associated with the bot
     * @param botNumber the number of the bot (1, 2, or 3)
     */
    public Bot(PlayingModel playingModel, int botNumber) {
        EntityNames botName = null;
        switch  (botNumber) {
            case 1 -> botName = EntityNames.BOT1;
            case 2 -> botName = EntityNames.BOT2;
            case 3 -> botName = EntityNames.BOT3;
        }
        super(playingModel, botName);
    }

    /**
     * Performs the bot's turn, including revealing the first card, checking hand sum, and adding cards to the hand.
     */
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

    /**
     * Builds the bot's initial hand by drawing two cards from the deck and calculating the hand sum.
     */
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