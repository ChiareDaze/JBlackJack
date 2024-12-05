package entities;

import model.cards.CardModel;

import java.util.ArrayList;
import java.util.List;

public class Player {

        private List<CardModel> hand;
        private String name;

        public Player(String name) {
            this.name = name;
            this.hand = new ArrayList<>();
        }


        public void addCard(CardModel cardModel) {
            hand.add(cardModel);
        }

        /*public int getHandValue() {
            return hand.stream()
                    .mapToInt(card -> card.getRank().equals("ace") ? 11 : card.getValue())
                    .sum();
        }*/

        public List<CardModel> getHand() {
            return hand;
        }

        public String getName() {
            return name;
        }


}

