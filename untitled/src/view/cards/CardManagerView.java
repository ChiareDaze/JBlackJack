package view.cards;

import model.cards.CardModel;
import model.cards.DeckModel;
import model.gameStates.PlayingModel;

import java.awt.*;
import java.util.ArrayList;

public class CardManagerView {
    private static CardManagerView instance;
    private PlayingModel playingModel = PlayingModel.getInstance();

    private ArrayList <CardView> playerHand = new ArrayList<CardView>();
    private ArrayList <CardView> dealerHand = new ArrayList<CardView>();
    private ArrayList <CardView> bot1Hand = new ArrayList<CardView>();
    private ArrayList <CardView> bot2Hand = new ArrayList<CardView>();
    private ArrayList <CardView> bot3Hand = new ArrayList<CardView>();

    private CardManagerView(){
        // empty constructor
    }

    public static CardManagerView getInstance() {
        if (instance == null) {
            instance = new CardManagerView();
        }
        return instance;
    }

    public void syncView(){
        playerHand.clear();
        dealerHand.clear();
        bot1Hand.clear();
        bot2Hand.clear();
        bot3Hand.clear();

        for (CardModel card : playingModel.getPlayer().getHand()){
            playerHand.add(new CardView(card));
        }

        ArrayList<CardModel> dealerModelHand = playingModel.getDealer().getHand();

        for (int i = 0; i < dealerModelHand.size(); i++){
            dealerHand.add(new CardView(dealerModelHand.get(i)));
        }
        
        if (playingModel.getBotList().size() >= 1){
            for (CardModel card : playingModel.getBot(0).getHand()){
                bot1Hand.add(new CardView(card));
            }
        }

        if (playingModel.getBotList().size() >= 2){
            for (CardModel card : playingModel.getBot(1).getHand()){
                bot2Hand.add(new CardView(card));
            }
        }

        if (playingModel.getBotList().size() >= 3){
            for (CardModel card : playingModel.getBot(2).getHand()){
                bot3Hand.add(new CardView(card));
            }
        }
    }

    public void drawPlayerHand (Graphics g){

        int y1 = 470;
        int y2 = y1 - 134 - 5;
        int cardNumber = 0;
        for (CardView card : playerHand){
            if (cardNumber < 3) {
                int x = 25 + (card.cardWidth + 5) * cardNumber;
                card.draw(g, x, y1);
            }
            else {
                int x = 25 +  (card.cardWidth + 5) * (cardNumber - 3);
                card.draw(g, x, y2);
            }
            cardNumber++;
        }
    }

    private void drawDealerHand(Graphics g) {
        int y1 = 20;
        int y2 = y1 + 134 + 5;
        int cardNumber = 0;
        for (CardView card : dealerHand){
            if (cardNumber < 3) {
                int x = 25 + (card.cardWidth + 5) * cardNumber;
                card.draw(g, x, y1);
            }
            else {
                int x = 25 +  (card.cardWidth + 5) * (cardNumber - 3);
                card.draw(g, x, y2);
            }
            cardNumber++;
        }
    }

    private void drawBotHand(Graphics g, int botNumber){
        int y1 = 20;
        int y2 = y1 + 5;
        int pos = 0;
        for (CardView card : dealerHand){
            if (pos < 3)
                card.draw(g, pos, y1);

            else
                card.draw(g, pos - 3, y2);
            pos++;
        }
    }

    public void draw (Graphics g) {
        syncView();
        drawPlayerHand(g);
        drawDealerHand(g);
    }
}