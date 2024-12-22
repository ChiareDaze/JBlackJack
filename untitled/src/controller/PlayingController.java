package controller;

import model.gameStates.PlayingModel;
import main.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayingController {

    private static PlayingController instance;
    private PlayingModel playingModel = PlayingModel.getInstance();
    private PauseOverlayController pauseOverlayController;

    private PlayingController(PauseOverlayController pauseOverlayController){
        this.pauseOverlayController = pauseOverlayController;
    }

    public static PlayingController getInstance(PauseOverlayController pauseOverlay){
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
            pauseOverlayController.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (playingModel.getPause())
            pauseOverlayController.mouseReleased(e);

    }

    public void mouseMoved(MouseEvent e) {
        if (playingModel.getPause())
            pauseOverlayController.mouseMoved(e);
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
        playingModel.getPlayer().hit();
        if (playingModel.getPlayer().getHandSum() > 21){
            gamePanel.deactiveHitButton();
        }
    }

    public void stayButtonPressed(GamePanel gamePanel){
        gamePanel.deactiveHitButton();
        playingModel.nextTurn();
        gamePanel.deactiveStayButton();
    }
}
