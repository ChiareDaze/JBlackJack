package entities;

import cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

        private List<Card> hand;
        private String name;

        public Player(String name) {
            this.name = name;
            this.hand = new ArrayList<>();
        }


        public void addCard(Card card) {
            hand.add(card);
        }

        /*public int getHandValue() {
            return hand.stream()
                    .mapToInt(card -> card.getRank().equals("ace") ? 11 : card.getValue())
                    .sum();
        }*/

        public List<Card> getHand() {
            return hand;
        }

        public String getName() {
            return name;
        }


}

