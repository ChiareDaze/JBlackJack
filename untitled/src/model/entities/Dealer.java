package model.entities;

import model.cards.CardModel;
import model.cards.DeckModel;

public class Dealer extends Entity implements BotAction {

    public void turn() {

        hand.getFirst().setHidden(false);

        if (handSum < 17) {
            while (handSum < 17) {
                addCardToHand();
                hand.getLast().setHidden(false);
            }
        }
    }

    @Override
    protected void buildHand() {
        for (int i = 0; i < 2; i++) {
            CardModel card = deck.remove(deck.size() - 1);
            handSum += card.getNumericalValue();
            if (card.isAce()) aceCount++;
            hand.add(card);
        }
        hand.getFirst().setHidden(true);
    }
}
