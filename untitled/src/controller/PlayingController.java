package controller;

import gameStates.PlayingModel;
import main.GamePanel;
import model.cards.CardsManagerModel;
import view.ui.PauseOverlay;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayingController {

    private static PlayingController instance;
    private PlayingModel playingModel = PlayingModel.getInstance();
    private PauseOverlay pauseOverlay;

    private PlayingController(PauseOverlay pauseOverlay){
        this.pauseOverlay = pauseOverlay;
    }

    public static PlayingController getInstance(PauseOverlay pauseOverlay){
        if (instance == null){
            instance = new PlayingController(pauseOverlay);
        }
        return instance;
    }

    public static PlayingController getInstance(){

        if (instance == null){
            throw new IllegalStateException("PlayingController instance is null, use getInstance(PauseOverlay pauseOverlay) instead");
        }

        return instance;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (playingModel.getPause())
            pauseOverlay.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (playingModel.getPause())
            pauseOverlay.mouseReleased(e);

    }

    public void mouseMoved(MouseEvent e) {
        if (playingModel.getPause())
            pauseOverlay.mouseMoved(e);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                playingModel.setPaused(!playingModel.getPause());
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void hitButtonPressed(GamePanel gamePanel){
        CardsManagerModel.getInstace().hitButtonPressed(gamePanel);
    }


    public void stayButtonPressed(){
        CardsManagerModel.getInstace().stayButtonPressed();
    }
}
