package model.entities;

import model.cards.CardModel;
import model.gameStates.PlayingModel;
import model.utilz.Constants.EntityNames;


/**
 * The Dealer class represents the dealer entity in the game, which performs actions based on the game state.
 */
public class Dealer extends Entity implements BotAction {

    /**
     * Constructs a Dealer object with the specified playing model.
     *
     * @param playingModel the playing model associated with the dealer
     */
    public Dealer(PlayingModel playingModel) {
        super(playingModel, EntityNames.DEALER);
    }

    /**
     * Performs the dealer's turn, including revealing the first card, checking hand sum, and adding cards to the hand.
     */
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

    /**
     * Builds the dealer's initial hand by drawing two cards from the deck and calculating the hand sum.
     */
    @Override
    protected void buildHand() {
        for (int i = 0; i < 2; i++) {
            CardModel card = deck.remove(deck.size() - 1);
            handSum += card.getNumericalValue();
            if (card.isAce()) aceCount++;
            hand.add(card);
        }
        hand.getFirst().setHidden(true);
        isBlackJack();
    }
}
