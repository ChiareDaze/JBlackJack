package model;

import model.entities.Bot;
import model.entities.Dealer;
import model.entities.Player;
import model.gameStates.PlayingModel;
import model.profiles.ProfilesManager;
import model.utilz.Constants.EntityNames;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * The PointManager class manages the points and game state for the players, dealer, and bots.
 * It is implemented as a singleton and extends Observable to notify observers of changes.
 */
public class PointManager extends Observable {

    private static PointManager instance;
    private Player player;
    private Dealer dealer;
    private Bot bot1;
    private Bot bot2;
    private Bot bot3;
    private ArrayList<EntityNames> winners = new ArrayList<>();
    private ProfilesManager profilesManager = ProfilesManager.getInstance();
    private boolean dealerOver = false;
    private boolean alreadyControlled = false;
    private boolean playerWins = false;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private PointManager() {

    }

    /**
     * Returns the singleton instance of the PointManager class.
     * If the instance is null, it creates a new instance.
     *
     * @return the singleton instance of PointManager
     */
    public static PointManager getInstance() {
        if (instance == null) {
            instance = new PointManager();
        }
        return instance;
    }

    /**
     * Sets the player, dealer, and bot entities.
     *
     * @param player the player entity
     * @param dealer the dealer entity
     * @param botList the list of bot entities
     */
    public void setEntities(Player player, Dealer dealer, List<Bot> botList) {
        this.player = player;
        this.dealer = dealer;

        if (botList.size() == 1) {
            bot1 = botList.get(0);
        }
        if (botList.size() == 2) {
            bot1 = botList.get(0);
            bot2 = botList.get(1);
        }
        if (botList.size() == 3) {
            bot1 = botList.get(0);
            bot2 = botList.get(1);
            bot3 = botList.get(2);
        }
    }

    /**
     * Checks if any entity has a blackjack hand and adds them to the winners list.
     */
    public void blackJackHand(){
        if (PlayingModel.getInstance().isGameFinished()) {

            if (player.isBlackJack()) {
                winners.add(EntityNames.PLAYER);
            }

            if (dealer.isBlackJack()) {
                winners.add(EntityNames.DEALER);
            }

            if (bot1 != null && bot1.isBlackJack()) {
                winners.add(EntityNames.BOT1);
            }

            if (bot2 != null && bot2.isBlackJack()) {
                winners.add(EntityNames.BOT2);
            }

            if (bot3 != null && bot3.isBlackJack()) {
                winners.add(EntityNames.BOT3);
            }
        }
        checkIfPlayerWins();
    }

    /**
     * Checks if any entity has a hand sum of twenty-one and adds them to the winners list.
     */
    public void checkTwentyOne(){
        int playerSum = player.getHandSum();
        int dealerSum = dealer.getHandSum();
        int bot1Sum = 0, bot2Sum = 0, bot3Sum = 0;

        if (bot1 != null) {
            bot1Sum = bot1.getHandSum();
        }
        if (bot2 != null){
            bot2Sum = bot2.getHandSum();
        }
        if (bot3 != null) {
            bot3Sum = bot3.getHandSum();
        }


        if (playerSum == 21) {
            winners.add(EntityNames.PLAYER);
        }

        if (dealerSum == 21) {
            winners.add(EntityNames.DEALER);
        }

        if (bot1Sum == 21) {
            winners.add(EntityNames.BOT1);
        }

        if (bot2Sum == 21) {
            winners.add(EntityNames.BOT2);
        }

        if (bot3Sum == 21) {
            winners.add(EntityNames.BOT3);
        }
        checkIfPlayerWins();
        setChanged();
        notifyObservers();
    }

    /**
     * Determines the winner of the game.
     */
    public void setWinner() {

        if (alreadyControlled){
            return;
        }

        if (winners.size() > 0) {
            alreadyControlled = true;
            profilesManager.increaseGames();
            return;
        }
        checkIfDealerOver();

        if (winners.size() > 0) {
            alreadyControlled = true;
            profilesManager.increaseGames();
            return;
        }
        blackJackHand();

        if (winners.size() > 0) {
            alreadyControlled = true;
            profilesManager.increaseGames();
            return;
        }
        checkTwentyOne();


        if (winners.size() > 0) {
            alreadyControlled = true;
            profilesManager.increaseGames();
            return;
        }
        checkGreater();
        profilesManager.increaseGames();
        alreadyControlled = true;
    }

    /**
     * Checks which entity has the highest hand sum and adds them to the winners list.
     */
    private void checkGreater() {

        int playerSum = player.getHandSum();
        int dealerSum = dealer.getHandSum();
        int bot1Sum = 0, bot2Sum = 0, bot3Sum = 0;

        if (bot1 != null) {
            bot1Sum = bot1.getHandSum();
        }
        if (bot2 != null){
            bot2Sum = bot2.getHandSum();
        }
        if (bot3 != null) {
            bot3Sum = bot3.getHandSum();
        }
        if (playerSum > 21) playerSum = 0;
        if (dealerSum > 21) dealerSum = 0;
        if (bot1Sum > 21) bot1Sum = 0;
        if (bot2Sum > 21) bot2Sum = 0;
        if (bot3Sum > 21) bot3Sum = 0;

        int maxSum = Math.max(playerSum, Math.max(dealerSum, Math.max(bot1Sum, Math.max(bot2Sum, bot3Sum))));
        if (playerSum == maxSum) winners.add(EntityNames.PLAYER);
        if (dealerSum == maxSum) winners.add(EntityNames.DEALER);
        if (bot1Sum == maxSum) winners.add(EntityNames.BOT1);
        if (bot2Sum == maxSum) winners.add(EntityNames.BOT2);
        if (bot3Sum == maxSum) winners.add(EntityNames.BOT3);

        if (maxSum == 0) {
            winners.remove(EntityNames.DEALER);
        }
        checkIfPlayerWins();
        setChanged();
        notifyObservers();
    }

    /**
     * Checks if the dealer's hand sum exceeds twenty-one and adds the player and bots to the winners list.
     */
    public void checkIfDealerOver() {
        if (dealer.getHandSum() > 21) {

            dealerOver = true;
            winners.add(EntityNames.PLAYER);

            if (bot1 != null) {
                winners.add(EntityNames.BOT1);
            }
            if (bot2 != null) {
                winners.add(EntityNames.BOT2);
            }
            if (bot3 != null) {
                winners.add(EntityNames.BOT3);
            }
        }
        checkIfPlayerWins();
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the list of winners.
     *
     * @return the list of winners
     */
    public ArrayList<EntityNames> getWinners() {
        return winners;
    }

    /**
     * Checks if the player is among the winners and updates the player's win count.
     */
    public void checkIfPlayerWins(){
        if (winners.contains(EntityNames.PLAYER)) {
            playerWins = true;
            profilesManager.increaseWins();
        }
    }

    /**
     * Returns whether the dealer's hand sum exceeds twenty-one.
     *
     * @return true if the dealer's hand sum exceeds twenty-one, false otherwise
     */
    public boolean isDealerOver() {
        return dealerOver;
    }

    /**
     * Returns whether the player is among the winners.
     *
     * @return true if the player is among the winners, false otherwise
     */
    public boolean isPlayerWins() {
        return playerWins;
    }
}