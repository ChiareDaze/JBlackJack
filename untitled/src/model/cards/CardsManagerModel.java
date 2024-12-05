package model.cards;

import main.GamePanel;
import model.utilz.Load.Images.CardType;

import java.util.ArrayList;
import java.util.Random;

import static model.utilz.Load.Images.CardType.*;

public class CardsManagerModel {

    private static CardsManagerModel instance;
    private Random random = new Random(); //shuffle deck
    private ArrayList <CardModel> deck;

    private ArrayList <CardModel> playerHand = new ArrayList<CardModel>();
    private ArrayList <CardModel> dealerHand = new ArrayList<CardModel>();

    private int dealerSum, dealerAceCount;
    private int playerSum, playerAceCount;
    private CardModel hiddenCardModel;
    private Boolean isHiddenCardActive = false;

    private CardsManagerModel(){
        startGame();
    }

    public static CardsManagerModel getInstace(){
        if(instance == null){
            instance = new CardsManagerModel();
        }
        return instance;
    }

    public void update(){
        //if (!stayButton.isEnabled()){
        //   hiddenCardImage = new ImageIcon(getClass().getResource(hiddenCard.getImagePath())).getImage();
        //   isHiddenCardActive = true;
        //}
        for (CardModel cardModel : dealerHand) cardModel.update();
        for (CardModel cardModel : playerHand) cardModel.update();
    }

    public void startGame() {
        //deck
        buildDeck();
        shuffleDeck();

        //dealer
        hiddenCardModel = deck.remove(deck.size() - 1); //remove the last card from the deck
        dealerSum += hiddenCardModel.getNumericalValue();
        if (hiddenCardModel.isAce()) dealerAceCount++;

        CardModel cardModel = deck.remove(deck.size() - 1);
        dealerSum += cardModel.getNumericalValue();
        if (cardModel.isAce()) dealerAceCount++;
        dealerHand.add(cardModel);

        System.out.println("Dealer;");
        System.out.println("Hidden model.cards.Card: " + hiddenCardModel);
        System.out.println("DealerHand" + dealerHand);
        System.out.println("DealerSum: " + dealerSum);
        System.out.println("DealerAceCount: " + dealerAceCount);

        //player
        for (int i = 0; i < 2; i++) {
            cardModel = deck.remove(deck.size() - 1);
            playerSum += cardModel.getNumericalValue();
            if (cardModel.isAce()) playerAceCount++;
            playerHand.add(cardModel);
        }
    }



    public void buildDeck(){
        deck = new ArrayList<>();
        String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        CardType[] types = {H, D, C, S};

        //iterate through all values and types to create a deck of model.cards
        for (String value : values) {
            for (CardType type : types) {
                deck.add(new CardModel(value, type));
            }
        }
        System.out.println("built deck");
        System.out.println(deck);
    }

    public void shuffleDeck(){
        for (int i = 0; i<deck.size(); i++){
            int j = random.nextInt(deck.size()); //gives a random number between 0 and 51
            CardModel currCardModel = deck.get(i);
            CardModel randomCardModel = deck.get(j);
            deck.set(i, randomCardModel);
            deck.set(j, currCardModel);
        }
        System.out.println("shuffled deck");
        System.out.println(deck);
    }

    public int reducePlayerAce() { //when the points are over 21, reduce the value of the ace from 11 to 1
        while (playerSum > 21 && playerAceCount > 0) {
            playerSum -= 10;
            playerAceCount--;
        }
        return playerSum;
    }

    public int reduceDealerAce(){ //when the points are over 21, reduce the value of the ace from 11 to 1
        while (dealerSum > 21 && dealerAceCount > 0){
            dealerSum -= 10;
            dealerAceCount--;
        }
        return dealerSum;
    }

    public void hitButtonPressed(GamePanel gamePanel){
        CardModel cardModel = deck.remove(deck.size()-1);
        playerSum += cardModel.getNumericalValue();
        if (cardModel.isAce()) playerAceCount++;
        playerHand.add(cardModel);
        if (reducePlayerAce() > 21){
            gamePanel.deactiveHitButton();
        }
    }

    public void stayButtonPressed(){
        while (dealerSum < 17){
            CardModel cardModel = deck.remove(deck.size()-1);
            dealerSum += cardModel.getNumericalValue();
            if (cardModel.isAce()) dealerAceCount++;
            dealerHand.add(cardModel);
            reduceDealerAce();
        }
    }

    public ArrayList<CardModel> getDealerHand() {
        return dealerHand;
    }

    public ArrayList<CardModel> getPlayerHand() {
        return playerHand;
    }

    public Boolean getHiddenCardActive() {
        return isHiddenCardActive;
    }

    public CardModel getHiddenCardModel() {
        return hiddenCardModel;
    }
}
