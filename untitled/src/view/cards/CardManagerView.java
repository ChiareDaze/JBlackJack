package view.cards;

import model.cards.CardModel;
import model.cards.CardsManagerModel;
import java.awt.*;
import java.util.ArrayList;

public class CardManagerView {
    private static CardManagerView instance;
    private CardsManagerModel cardsManagerModel = CardsManagerModel.getInstace();
    private ArrayList<CardView> playerHand = new ArrayList<CardView>();
    private ArrayList <CardView> dealerHand = new ArrayList<CardView>();


    public static CardManagerView getInstance() {
        if (instance == null) {
            instance = new CardManagerView();
        }
        return instance;
    }

    public void syncView(){
        playerHand.clear();
        dealerHand.clear();

        for (CardModel cardModel : cardsManagerModel.getPlayerHand())
            playerHand.add(new CardView(cardModel));

        for (CardModel cardModel : cardsManagerModel.getDealerHand())
            dealerHand.add(new CardView(cardModel));
    }

    public void draw (Graphics g){
        syncView();
        int y = 320;
        int pos = 0;
        for (CardView card : playerHand){
            card.draw(g, pos, y);
            pos++;
        }

        y = 20;
        pos = 0;
        for (CardView card : dealerHand){
            card.draw(g, pos, y);
            pos++;
        }
    }
}