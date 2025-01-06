package model.cards;

import java.util.ArrayList;
import java.util.Random;

import model.utilz.Constants.CardType;
import static model.utilz.Constants.CardType.*;


public class DeckModel {

    private static DeckModel instance;
    private Random random = new Random(); //shuffle deck
    private ArrayList <CardModel> deck;

    private DeckModel(){
        buildDeck();
        shuffleDeck();
    }

    public static DeckModel getInstace(){
        if(instance == null){
            instance = new DeckModel();
        }
        return instance;
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

    public ArrayList<CardModel> getDeck() {
        return deck;
    }
}
