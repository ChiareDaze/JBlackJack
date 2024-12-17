package model.gameStates;

import main.GamePanel;
import model.cards.CardModel;
import model.cards.DeckModel;
import main.Game;
import model.entities.Bot;
import model.entities.Dealer;
import model.entities.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayingModel extends State {

    private static PlayingModel instance;
    protected static Game game;

    private DeckModel deckModel = DeckModel.getInstace();
    private MenuModel menuModel;

    private boolean paused = false;

    private Player player = new Player();
    private Dealer dealer = new Dealer();
    private List<Bot> botList = new ArrayList<>();
    private int numberOfBots = 0;


    private PlayingModel() {
        initClasses();
    }

    public static PlayingModel getInstance(){
        if(instance == null){
            instance = new PlayingModel();
        }
        return instance;
    }

    private void initClasses(){
        menuModel = MenuModel.getInstance();
        initBotList();
    }

    public void update(){
    }

    public void initBotList(){
        for (int i = 0; i < numberOfBots; i++){
            botList.add(new Bot());
        }
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

    public Bot getBot(int index){
        return botList.get(index);
    }

    public List<Bot> getBotList() {
        return botList;
    }
}
