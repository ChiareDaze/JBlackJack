package model.entities;

import model.cards.CardsManagerModel;

public class Dealer {

    private CardsManagerModel cardsManagerModel;

    public Dealer(CardsManagerModel cardsManagerModel) {
        this.cardsManagerModel = cardsManagerModel;
    }

    public void dealerTurn() {

        cardsManagerModel.getDealerHand().getFirst().setHidden(false);

        if (cardsManagerModel.getDealerSum() < 17) {
            while (cardsManagerModel.getDealerSum() < 17) {
                cardsManagerModel.addCardToDealerHand();
                cardsManagerModel.getDealerHand().getLast().setHidden(false);
                System.out.println(cardsManagerModel.getDealerSum());
            }
        }
    }

}
