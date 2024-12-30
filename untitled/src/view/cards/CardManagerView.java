package view.cards;

import model.cards.CardModel;
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

        
        if (playingModel.getBotList().size() == 1){
            ArrayList<CardModel> bot1ModelHand = playingModel.getBot(0).getHand();
            for (int i = 0; i < bot1ModelHand.size(); i++){
                bot1Hand.add(new CardView(bot1ModelHand.get(i)));
            }
        }

        if (playingModel.getBotList().size() == 2){

            ArrayList<CardModel> bot1ModelHand = playingModel.getBot(0).getHand();
            ArrayList<CardModel> bot2ModelHand = playingModel.getBot(1).getHand();

            for (int i = 0; i < bot1ModelHand.size(); i++){
                bot1Hand.add(new CardView(bot1ModelHand.get(i)));
            }

            for (int i = 0; i < bot2ModelHand.size(); i++){
                bot2Hand.add(new CardView(bot2ModelHand.get(i)));
            }
        }

        if (playingModel.getBotList().size() == 3){

            ArrayList<CardModel> bot1ModelHand = playingModel.getBot(0).getHand();
            ArrayList<CardModel> bot2ModelHand = playingModel.getBot(1).getHand();
            ArrayList<CardModel> bot3ModelHand = playingModel.getBot(2).getHand();

            for (int i = 0; i < bot1ModelHand.size(); i++){
                bot1Hand.add(new CardView(bot1ModelHand.get(i)));
            }

            for (int i = 0; i < bot2ModelHand.size(); i++){
                bot2Hand.add(new CardView(bot2ModelHand.get(i)));
            }

            for (int i = 0; i < bot3ModelHand.size(); i++){
                bot3Hand.add(new CardView(bot3ModelHand.get(i)));
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
        int y1 = 40;
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

        ArrayList <CardView>  botHand = null;

        switch (botNumber){
            case 1 -> botHand = bot1Hand;
            case 2 -> botHand = bot2Hand;
            case 3 -> botHand = bot3Hand;
        }

        int x = 600 + 130 * (botNumber - 1);
        int cardNumber = 0;

        for (CardView card : botHand){
            int y = 40 + 30 * cardNumber;
            card.draw(g, x, y);
            cardNumber++;
        }
    }

    public void draw (Graphics g) {
        syncView();
        drawPlayerHand(g);
        drawDealerHand(g);

        int numberOfBots = playingModel.getNumberOfBots();

        if (numberOfBots > 0) {
            for (int i = 1; i <= playingModel.getNumberOfBots(); i++) {
                drawBotHand(g, i);
            }
        }
    }
}