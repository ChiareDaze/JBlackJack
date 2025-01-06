package view.ui;

import model.profiles.Profile;
import model.profiles.ProfilesManager;
import model.utilz.Constants;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The ScoreBoard class represents the scoreboard in the game.
 * It handles the display and updating of player scores.
 */
public class ScoreBoard {

    private static ScoreBoard instance;
    private ArrayList<Profile> scores;

    /**
     * Private constructor to prevent instantiation.
     */
    private ScoreBoard() {

    }

    /**
     * Returns the singleton instance of the ScoreBoard.
     *
     * @return the singleton instance of the ScoreBoard
     */
    public static ScoreBoard getInstance() {
        if (instance == null) {
            instance = new ScoreBoard();
        }
        return instance;
    }

    /**
     * Draws the scoreboard on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(Graphics g) {
        drawTitle(g);
        drawScores(g);
    }

    /**
     * Draws the scores on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    private void drawScores(Graphics g) {
        int position = 1;

        for (Profile p : scores) {
            String name = p.getName();
            String score = String.valueOf(p.getNumberOfWins());
            String loses = String.valueOf(p.getNumberOfGames() - p.getNumberOfWins());
            String txt = position + "° " + name + " games: " + p.getNumberOfGames() + ", wins: " + score + ", loses: " + loses + ", level: " + p.getLevel();
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString(txt, getCenteredTextPosition(g, txt), 200 + scores.indexOf(p) * 50);
            position++;
        }
    }

    /**
     * Draws the title of the scoreboard on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    private void drawTitle(Graphics g) {
        String title = "Top 5 Scores";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(title, getCenteredTextPosition(g, title), 60);
    }

    /**
     * Updates the scores by fetching the top 5 profiles with the highest number of wins.
     */
    public void updateScores() {
        ArrayList<Profile> allProfiles = ProfilesManager.getInstance().getProfiles();
        scores = (ArrayList<Profile>) allProfiles.stream()
                .sorted(Comparator.comparing(Profile::getNumberOfWins).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Returns the x-coordinate for centering the text on the screen.
     *
     * @param g the Graphics context
     * @param text the text to be centered
     * @return the x-coordinate for centering the text
     */
    private static int getCenteredTextPosition(Graphics g, String text) {
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (Constants.WIDTH - metrics.stringWidth(text)) / 2;
        return x;
    }
}