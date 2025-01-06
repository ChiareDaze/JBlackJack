package view.ui;

import controller.PlayingController;
import model.PointManager;
import model.utilz.Constants;
import model.utilz.Constants.EntityNames;

import java.awt.*;

/**
 * The GameFinished class is responsible for managing the end-of-game screen.
 * It handles the display of the final game state, including the quit and score buttons,
 * and the display of the winners.
 */
public class GameFinished {
    private static GameFinished instance;
    private PlayingController playingController;
    private ScoreBoard scoreBoard = ScoreBoard.getInstance();
    private GameFinishedButtons quitButton;
    private GameFinishedButtons scoreButton;
    private boolean scoreBoardActive = false;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the buttons.
     */
    private GameFinished() {
        initButtons();
    }

    /**
     * Initializes the quit and score buttons with their positions and the playing controller.
     * rowIndex is 0 for quitButton and 1 for scoreButton.
     */
    private void initButtons() {
        quitButton = new GameFinishedButtons(400, 620, 0, playingController);
        scoreButton = new GameFinishedButtons(600, 620, 1, playingController);
    }

    /**
     * Returns the singleton instance of the GameFinished class.
     * If the instance is null, it creates a new instance.
     *
     * @return the singleton instance of GameFinished
     */
    public static GameFinished getInstance() {
        if (instance == null) {
            instance = new GameFinished();
        }
        return instance;
    }

    /**
     * Starts the playing controller by setting the provided PlayingController instance
     * and initializing the buttons.
     *
     * @param playingController the PlayingController instance to set
     */
    public void startPlayingController(PlayingController playingController) {
        this.playingController = playingController;
        initButtons();
    }

    /**
     * Updates the state of the quit and score buttons.
     */
    public void update() {
        quitButton.update();
        scoreButton.update();
    }

    /**
     * Draws the game finished screen on the given Graphics context.
     * It includes drawing the background, buttons, and the score board if active.
     * Also, it displays the winners based on the game state.
     *
     * @param g the Graphics context to draw on
     */
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

    /**
     * Draws the "dealer over" message on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    private void drawDealerOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "The dealer is over 21";
        g.drawString(text, getCenteredTextPosition(g, text), 300);
    }

    /**
     * Draws the "player wins" message on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    private void drawPlayerWin(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "Player Wins";
        g.drawString(text, getCenteredTextPosition(g, text), 300);
    }

    /**
     * Draws the "player loses" message on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    private void drawPlayerLose(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "Player Loses";
        g.drawString(text, getCenteredTextPosition(g, text), 300);
        writeWinners(g);
    }

    /**
     * Draws the "tie" message on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    private void drawPlayerTie(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "Tie";
        g.drawString(text, getCenteredTextPosition(g, text), 300);
        writeWinners(g);
    }

    /**
     * Writes the winners' names on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
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

    /**
     * Returns the x-coordinate for centering the text on the screen.
     *
     * @param g the Graphics context
     * @param text the text to center
     * @return the x-coordinate for centering the text
     */
    private static int getCenteredTextPosition(Graphics g, String text){
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (Constants.WIDTH - metrics.stringWidth(text)) / 2;
        return x;
    }

    /**
     * Resets the state of the quit and score buttons.
     */
    public void resetButtons() {
        quitButton.resetBools();
        scoreButton.resetBools();
    }

    /**
     * Returns the quit button.
     *
     * @return the quit button
     */
    public GameFinishedButtons getQuitButton() {
        return quitButton;
    }

    /**
     * Returns the score button.
     *
     * @return the score button
     */
    public GameFinishedButtons getScoreButton() {
        return scoreButton;
    }

    /**
     * Returns whether the score board is active.
     *
     * @return true if the score board is active, false otherwise
     */
    public boolean isScoreBoardActive() {
        return scoreBoardActive;
    }

    /**
     * Sets the score board active state.
     *
     * @param scoreBoardActive true to activate the score board, false to deactivate
     */
    public void setScoreBoardActive(boolean scoreBoardActive) {
        this.scoreBoardActive = scoreBoardActive;
    }
}