package model;

import model.entities.Bot;
import model.entities.Dealer;
import model.entities.Player;
import model.gameStates.PlayingModel;
import model.profiles.ProfilesManager;
import model.utilz.Constants.EntityNames;
import java.util.ArrayList;
import java.util.List;

public class PointManager {

    private static PointManager instance;
    private Player player;
    private Dealer dealer;
    private Bot bot1;
    private Bot bot2;
    private Bot bot3;
    private ArrayList<EntityNames> winners = new ArrayList<>();
    private ProfilesManager profilesManager = ProfilesManager.getInstance();

    private PointManager() {

    }

    public static PointManager getInstance() {
        if (instance == null) {
            instance = new PointManager();
        }
        return instance;
    }

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
        System.out.println(winners.size());
    }

    public void checkTwentyOne(){
        int playerSum = player.getHandSum();
        int dealerSum = dealer.getHandSum();
        int bot1Sum = 0, bot2Sum = 0, bot3Sum = 0;

        if (bot1 != null) bot1Sum = bot1.getHandSum();
        if (bot2 != null) bot1Sum = bot2.getHandSum();
        if (bot3 != null) bot1Sum = bot3.getHandSum();


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
    }

    public void setWinner() {

        if (winners.size() > 0) {
            return;
        }
        blackJackHand();

        if (winners.size() > 0) {
            return;
        }

        checkTwentyOne();
        if (winners.size() > 0) {
            return;
        }
        checkGreater();
        checkIfPlayerWins();
    }

    private void checkGreater() {
        int playerSum = player.getHandSum();
        int dealerSum = dealer.getHandSum();
        int bot1Sum = 0, bot2Sum = 0, bot3Sum = 0;

        if (bot1 != null) bot1Sum = bot1.getHandSum();
        if (bot2 != null) bot1Sum = bot2.getHandSum();
        if (bot3 != null) bot1Sum = bot3.getHandSum();

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
    }

    public ArrayList<EntityNames> getWinners() {
        return winners;
    }

    public void checkIfPlayerWins(){
        if (winners.contains(EntityNames.PLAYER)) {
            profilesManager.increaseWins();
        }
    }
}