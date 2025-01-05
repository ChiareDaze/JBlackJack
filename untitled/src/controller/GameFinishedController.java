package controller;

import view.ui.GameFinished;
import view.ui.GameFinishedButtons;
import view.ui.ScoreBoard;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameFinishedController {

    private GameFinished gameFinished = GameFinished.getInstance();
    private ScoreBoard scoreBoard = ScoreBoard.getInstance();


    public void mousePressed(MouseEvent e) {

        GameFinishedButtons quitButton = gameFinished.getQuitButton();
        GameFinishedButtons scoreButton = gameFinished.getScoreButton();

        if (isIn(e,quitButton))
            quitButton.setMousePressed(true);

        if (isIn(e, scoreButton))
            scoreButton.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {

        GameFinishedButtons quitButton = gameFinished.getQuitButton();
        GameFinishedButtons scoreButton = gameFinished.getScoreButton();

        if (isIn(e, quitButton)){
            if (quitButton.isMousePressed()){
                quitButtonPressed();
            }
            quitButton.setMousePressed(false);
        }

        if (isIn(e, scoreButton)){
            if (scoreButton.isMousePressed()){
                scoreButtonPressed();
            }
            scoreButton.setMousePressed(false);
        }

        gameFinished.resetButtons();
    }

    public void mouseMoved(MouseEvent e) {

        GameFinishedButtons quitButton = gameFinished.getQuitButton();
        GameFinishedButtons scoreButton = gameFinished.getScoreButton();

        quitButton.setMouseOver(false);
        scoreButton.setMouseOver(false);

        if (isIn(e, quitButton)){
            quitButton.setMouseOver(true);
        }

        if (isIn(e, scoreButton)){
            scoreButton.setMouseOver(true);
        }
    }

    public void quitButtonPressed(){
        System.exit(0);
    }

    public void scoreButtonPressed(){
        if (gameFinished.isScoreBoardActive()){
            gameFinished.setScoreBoardActive(false);
        } else {
            scoreBoard.updateScores();
            gameFinished.setScoreBoardActive(true);
        }
    }

    public boolean isIn(MouseEvent e, GameFinishedButtons gb){
        return gb.getBounds().contains(e.getX(), e.getY());
    }

    public void mouseClicked(MouseEvent e) {
    }
}