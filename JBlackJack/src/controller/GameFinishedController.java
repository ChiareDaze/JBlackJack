package controller;

import view.ui.GameFinished;
import view.ui.GameFinishedButtons;
import view.ui.ScoreBoard;

import java.awt.event.MouseEvent;

/**
 * The GameFinishedController class handles the mouse events for the game finished screen.
 */
public class GameFinishedController {

    private GameFinished gameFinished = GameFinished.getInstance();
    private ScoreBoard scoreBoard = ScoreBoard.getInstance();

    /**
     * Handles the mouse pressed event.
     *
     * @param e the MouseEvent to be processed
     */
    public void mousePressed(MouseEvent e) {
        GameFinishedButtons quitButton = gameFinished.getQuitButton();
        GameFinishedButtons scoreButton = gameFinished.getScoreButton();

        if (isIn(e, quitButton))
            quitButton.setMousePressed(true);

        if (isIn(e, scoreButton))
            scoreButton.setMousePressed(true);
    }

    /**
     * Handles the mouse released event.
     *
     * @param e the MouseEvent to be processed
     */
    public void mouseReleased(MouseEvent e) {
        GameFinishedButtons quitButton = gameFinished.getQuitButton();
        GameFinishedButtons scoreButton = gameFinished.getScoreButton();

        if (isIn(e, quitButton)) {
            if (quitButton.isMousePressed()) {
                quitButtonPressed();
            }
            quitButton.setMousePressed(false);
        }

        if (isIn(e, scoreButton)) {
            if (scoreButton.isMousePressed()) {
                scoreButtonPressed();
            }
            scoreButton.setMousePressed(false);
        }

        gameFinished.resetButtons();
    }

    /**
     * Handles the mouse moved event.
     *
     * @param e the MouseEvent to be processed
     */
    public void mouseMoved(MouseEvent e) {
        GameFinishedButtons quitButton = gameFinished.getQuitButton();
        GameFinishedButtons scoreButton = gameFinished.getScoreButton();

        quitButton.setMouseOver(false);
        scoreButton.setMouseOver(false);

        if (isIn(e, quitButton)) {
            quitButton.setMouseOver(true);
        }

        if (isIn(e, scoreButton)) {
            scoreButton.setMouseOver(true);
        }
    }

    /**
     * Handles the action when the quit button is pressed.
     */
    public void quitButtonPressed() {
        System.exit(0);
    }

    /**
     * Handles the action when the score button is pressed.
     */
    public void scoreButtonPressed() {
        if (gameFinished.isScoreBoardActive()) {
            gameFinished.setScoreBoardActive(false);
        } else {
            scoreBoard.updateScores();
            gameFinished.setScoreBoardActive(true);
        }
    }

    /**
     * Checks if the mouse event is within the bounds of the specified button.
     *
     * @param e the MouseEvent to be checked
     * @param gb the GameFinishedButtons to be checked
     * @return true if the mouse event is within the bounds of the button, false otherwise
     */
    public boolean isIn(MouseEvent e, GameFinishedButtons gb) {
        return gb.getBounds().contains(e.getX(), e.getY());
    }
}