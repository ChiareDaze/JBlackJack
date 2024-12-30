package model.gameStates;

import model.cards.DeckModel;
import main.Game;
import model.entities.Bot;
import model.entities.Dealer;
import model.entities.Player;
import model.utilz.Constants.Turns;
import static model.utilz.Constants.Turns.*;

import java.util.ArrayList;
import java.util.List;


public class PlayingModel extends State {

    private static PlayingModel instance;
    protected static Game game;

    private DeckModel deckModel = DeckModel.getInstace();
    private MenuModel menuModel;

    private boolean paused = false;

    private Player player = new Player(this);
    private Dealer dealer = new Dealer(this);
    private List<Bot> botList = new ArrayList<>();
    private int numberOfBots = 0;
    private Turns currentTurn = PLAYER;
    private boolean gameFinished = false;
    private boolean firstUpdate = true;


    private PlayingModel() {
        menuModel = MenuModel.getInstance();
    }

    public static PlayingModel getInstance(){
        if(instance == null){
            instance = new PlayingModel();
        }
        return instance;
    }

    public void firstUpdate(){
        if (firstUpdate) {
            initBotList();
            firstUpdate = false;
        }
    }

    public void update(){
        if (paused)
            return;

        firstUpdate();

        switch (currentTurn){
            case PLAYER -> {}
            case DEALER -> dealer.turn();
            case BOT1 -> botList.get(0).turn();
            case BOT2 -> botList.get(1).turn();
            case BOT3 -> botList.get(2).turn();
            case NONE -> {}
        }
    }
    
    public void nextTurn(){
        switch (currentTurn){
            
            case PLAYER:
                if (botList.size() >= 1)
                    currentTurn = BOT1;
                else
                    currentTurn = DEALER;
                break;
            
            case BOT1:
                if (botList.size() >= 2)
                    currentTurn = BOT2;
                else
                    currentTurn = DEALER;
                break;

            case BOT2:
                if (botList.size() >= 3)
                    currentTurn = BOT3;
                else
                    currentTurn = DEALER;
                break;

            case BOT3:
                currentTurn = DEALER;
                break;

            case DEALER:
                currentTurn = NONE;
                break;

            case NONE:
                gameFinished = true;
                break;
        }
    }

    public void initBotList(){
        for (int i = 0; i < numberOfBots; i++){
            botList.add(new Bot(this));
        }
    }

    public void increaseBotCount(){
        if (numberOfBots < 3){
            numberOfBots++;
        }
    }

    public void decreaseBotCount(){
        if (numberOfBots > 0)
            numberOfBots--;
    }

    public void unpauseGame(){
        paused = false;
    }

    public boolean getPause(){
        return paused;
    }

    public void setPaused(boolean paused){
        this.paused = paused;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }

    public int getNumberOfBots(){
        return numberOfBots;
    }

    public Bot getBot(int index){
        return botList.get(index);
    }

    public List<Bot> getBotList() {
        return botList;
    }

    public Turns getCurrentTurn(){
        return currentTurn;
    }
}
