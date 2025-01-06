package view.ui;

import controller.PlayingController;
import model.PointManager;
import model.utilz.Constants;
import model.utilz.Constants.EntityNames;

import java.awt.*;

public class GameFinished {
    private static GameFinished instance;
    private PlayingController playingController;
    private ScoreBoard scoreBoard = ScoreBoard.getInstance();
    private GameFinishedButtons quitButton;
    private GameFinishedButtons scoreButton;
    private boolean scoreBoardActive = false;

    private GameFinished() {
        initButtons();
    }

    //rowIndex is 0 for quitButton and 1 for scoreButton
    private void initButtons() {
        quitButton = new GameFinishedButtons(400, 620, 0, playingController);
        scoreButton = new GameFinishedButtons(600, 620, 1, playingController);
    }

    public static GameFinished getInstance() {
        if (instance == null) {
            instance = new GameFinished();
        }
        return instance;
    }

    public void startPlayingController(PlayingController playingController) {
        this.playingController = playingController;
        initButtons();
    }

    public void update() {
        quitButton.update();
        scoreButton.update();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0,220));
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        quitButton.draw(g);
        scoreButton.draw(g);

        if (scoreBoardActive) {
            scoreBoard.draw(g);
            return;
        }

        if (PointManager.getInstance().isDealerOver()) {
            drawDealerOver(g);
            writeWinners(g);
        }
        else {
            if (PointManager.getInstance().getWinners().contains(EntityNames.PLAYER)) {
                if (PointManager.getInstance().getWinners().size() == 1) {
                    drawPlayerWin(g);
                } else {
                    drawPlayerTie(g);
                }
            } else drawPlayerLose(g);
        }
    }

    private void drawDealerOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "The dealer is over 21";
        g.drawString(text, getCenteredTextPosition(g, text), 300);
    }

    private void drawPlayerWin(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "Player Wins";
        g.drawString(text, getCenteredTextPosition(g, text), 300);
    }

    private void drawPlayerLose(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "Player Loses";
        g.drawString(text, getCenteredTextPosition(g, text), 300);
        writeWinners(g);
    }

    private void drawPlayerTie(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "Tie";
        g.drawString(text, getCenteredTextPosition(g, text), 300);
        writeWinners(g);
    }

    private void writeWinners(Graphics g){

        String winners = "The winners is: ";

        if (PointManager.getInstance().getWinners().size() > 1) {
            winners = "The winners are: ";
        }

        for (EntityNames winner : PointManager.getInstance().getWinners()) {
            winners += winner.toString() + " ";
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(winners, getCenteredTextPosition(g, winners), 400);
    }

    private static int getCenteredTextPosition(Graphics g, String text){
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (Constants.WIDTH - metrics.stringWidth(text)) / 2;
        return x;
    }

    public void resetButtons() {
        quitButton.resetBools();
        scoreButton.resetBools();
    }

    public GameFinishedButtons getQuitButton() {
        return quitButton;
    }

    public GameFinishedButtons getScoreButton() {
        return scoreButton;
    }

    public boolean isScoreBoardActive() {
        return scoreBoardActive;
    }

    public void setScoreBoardActive(boolean scoreBoardActive) {
        this.scoreBoardActive = scoreBoardActive;
    }
}