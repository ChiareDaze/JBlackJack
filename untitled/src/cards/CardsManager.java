package cards;

import main.GamePanel;
import utilz.Load.Images.CardType;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static utilz.Load.Images.CardType.*;

public class CardsManager {

    private static CardsManager instance;
    private Random random = new Random(); //shuffle deck
    private ArrayList <Card> deck;
    private ArrayList <Card> playerHand = new ArrayList<Card>();
    private ArrayList <Card> dealerHand = new ArrayList<Card>();
    private int dealerSum, dealerAceCount;
    private int playerSum, playerAceCount;
    private Card hiddenCard;
    private Boolean isHiddenCardActive = false;

    private CardsManager(){
        startGame();
    }

    public static CardsManager getInstace(){
        if(instance == null){
            instance = new CardsManager();
        }
        return instance;
    }

    public void update(){
        //if (!stayButton.isEnabled()){
        //   hiddenCardImage = new ImageIcon(getClass().getResource(hiddenCard.getImagePath())).getImage();
        //   isHiddenCardActive = true;
        //}
        for (Card card : dealerHand) card.update();
        for (Card card: playerHand) card.update();
    }


    public void draw(Graphics g){
        int y = 320;
        int pos = 0;
        for (Card card : playerHand){
            card.draw(g, pos, y);
            pos++;
        }

        y = 20;
        pos = 0;
        for (Card card : dealerHand){
            card.draw(g, pos, y);
            pos++;
        }

        if (isHiddenCardActive){
            hiddenCard.draw(g, 0, 20);
        }

    }


    public void startGame() {
        //deck
        buildDeck();
        shuffleDeck();

        //dealer
        hiddenCard = deck.remove(deck.size() - 1); //remove the last card from the deck
        dealerSum += hiddenCard.getValue();
        if (hiddenCard.isAce()) dealerAceCount++;

        Card card = deck.remove(deck.size() - 1);
        dealerSum += card.getValue();
        if (card.isAce()) dealerAceCount++;
        dealerHand.add(card);

        System.out.println("Dealer;");
        System.out.println("Hidden cards.Card: " + hiddenCard);
        System.out.println("DealerHand" + dealerHand);
        System.out.println("DealerSum: " + dealerSum);
        System.out.println("DealerAceCount: " + dealerAceCount);

        //player
        for (int i = 0; i < 2; i++) {
            card = deck.remove(deck.size() - 1);
            playerSum += card.getValue();
            if (card.isAce()) playerAceCount++;
            playerHand.add(card);
        }

    }



    public void buildDeck(){
        deck = new ArrayList<>();
        String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        CardType[] types = {H, D, C, S};

        //iterate through all values and types to create a deck of cards
        for (String value : values) {
            for (CardType type : types) {
                deck.add(new Card(value, type));
            }
        }
        System.out.println("built deck");
        System.out.println(deck);
    }

    public void shuffleDeck(){
        for (int i = 0; i<deck.size(); i++){
            int j = random.nextInt(deck.size()); //gives a random number between 0 and 51
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
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
        Card card = deck.remove(deck.size()-1);
        playerSum += card.getValue();
        if (card.isAce()) playerAceCount++;
        playerHand.add(card);
        if (reducePlayerAce() > 21){
            gamePanel.deactiveHitButton();
        }
    }

    public void stayButtonPressed(){
        while (dealerSum < 17){
            Card card = deck.remove(deck.size()-1);
            dealerSum += card.getValue();
            if (card.isAce()) dealerAceCount++;
            dealerHand.add(card);
            reduceDealerAce();
        }
    }

}
