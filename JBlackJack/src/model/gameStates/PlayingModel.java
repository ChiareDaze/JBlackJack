package model.gameStates;

import model.PointManager;

import model.entities.Bot;
import model.entities.Dealer;
import model.entities.Player;
import model.utilz.Constants.Turns;
import static model.utilz.Constants.Turns.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The PlayingModel class represents the model for the playing state of the game.
 * It manages the game entities, their turns, and the game state.
 */
public class PlayingModel {

    private static PlayingModel instance;
    private PointManager pointManager;
    private boolean paused = false;
    private boolean selectProfile = true;

    private Player player = new Player(this);
    private Dealer dealer = new Dealer(this);
    private List<Bot> botList = new ArrayList<>();
    private int numberOfBots = 0;
    private Turns currentTurn = PLAYER;
    private boolean firstUpdate = true;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the PointManager instance.
     */
    private PlayingModel() {
        pointManager = PointManager.getInstance();
    }

    /**
     * Returns the singleton instance of the PlayingModel class.
     * If the instance is null, it creates a new instance.
     *
     * @return the singleton instance of PlayingModel
     */
    public static PlayingModel getInstance(){
        if(instance == null){
            instance = new PlayingModel();
        }
        return instance;
    }

    /**
     * Performs the first update of the game state.
     * Initializes the bot list and sets the entities in the PointManager.
     */
    public void firstUpdate(){
        if (firstUpdate) {
            initBotList();
            pointManager.setEntities(player, dealer, botList);
            firstUpdate = false;
        }
    }

    /**
     * Updates the game state based on the current turn.
     * If the game is paused or the profile selection is active, the update is skipped.
     */
    public void update(){
        if (paused || selectProfile)
            return;

        firstUpdate();

        switch (currentTurn){
            case PLAYER -> {}
            case DEALER -> dealer.turn();
            case BOT1 -> botList.get(0).turn();
            case BOT2 -> botList.get(1).turn();
            case BOT3 -> botList.get(2).turn();
            case FINISHED -> pointManager.setWinner();
        }
    }

    /**
     * Advances the game to the next turn based on the current turn.
     */
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
                currentTurn = FINISHED;
                break;

            case FINISHED:
                //
                break;
        }
    }

    /**
     * Initializes the bot list by creating the specified number of bots.
     */
    public void initBotList(){
        for (int i = 0; i < numberOfBots; i++){
            botList.add(new Bot(this, i+1));
        }
    }

    /**
     * Increases the number of bots by one, up to a maximum of three.
     */
    public void increaseBotCount(){
        if (numberOfBots < 3){
            numberOfBots++;
        }
    }

    /**
     * Decreases the number of bots by one, down to a minimum of zero.
     */
    public void decreaseBotCount(){
        if (numberOfBots > 0)
            numberOfBots--;
    }

    /**
     * Unpauses the game.
     */
    public void unpauseGame(){
        paused = false;
    }

    /**
     * Returns whether the game is paused.
     *
     * @return true if the game is paused, false otherwise
     */
    public boolean getPause(){
        return paused;
    }

    /**
     * Sets the paused state of the game.
     *
     * @param paused the new paused state
     */
    public void setPaused(boolean paused){
        this.paused = paused;
    }

    /**
     * Returns the dealer entity.
     *
     * @return the dealer entity
     */
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * Returns the player entity.
     *
     * @return the player entity
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the number of bots.
     *
     * @return the number of bots
     */
    public int getNumberOfBots(){
        return numberOfBots;
    }

    /**
     * Returns the bot at the specified index.
     *
     * @param index the index of the bot
     * @return the bot at the specified index
     */
    public Bot getBot(int index){
        return botList.get(index);
    }

    /**
     * Returns the list of bots.
     *
     * @return the list of bots
     */
    public List<Bot> getBotList() {
        return botList;
    }

    /**
     * Returns the current turn.
     *
     * @return the current turn
     */
    public Turns getCurrentTurn(){
        return currentTurn;
    }

    /**
     * Returns whether the game is finished.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isGameFinished(){
        return currentTurn == FINISHED;
    }

    /**
     * Sets the profile selection state.
     *
     * @param selectProfile the new profile selection state
     */
    public void setSelectProfile(boolean selectProfile){
        this.selectProfile = selectProfile;
    }

    /**
     * Returns whether the profile selection is active.
     *
     * @return true if the profile selection is active, false otherwise
     */
    public boolean getSelectProfile(){
        return selectProfile;
    }
}