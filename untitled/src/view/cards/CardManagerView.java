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

        for (CardModel card : playingModel.getDealer().getHand()){
            dealerHand.add(new CardView(card));
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