package controller;

import model.gameStates.PlayingModel;import view.gamestates.PlayingView;
import view.music.MusicManager;
import view.ui.PlayerButton;
import view.ui.SelectProfile;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayingController {

    private PlayingModel playingModel = PlayingModel.getInstance();
    private SelectProfileController selectProfileController = SelectProfileController.getInstance();
    private PauseOverlayController pauseOverlayController;
    private GameFinishedController gameFinishedController;
    private PlayingView playingView = PlayingView.getInstance();
    private boolean deactiveHitButton = false;


    public PlayingController(PauseOverlayController pauseOverlayController) {
        this.pauseOverlayController = pauseOverlayController;
        gameFinishedController = new GameFinishedController();
    }

    public void mousePressed(MouseEvent e) {
        if (playingModel.getSelectProfile()) {
            selectProfileController.mousePressed(e);
            return;
        }

        if (playingModel.getPause()) {
            pauseOverlayController.mousePressed(e);
            return;
        }

        if (playingModel.isGameFinished()){
            gameFinishedController.mousePressed(e);
            return;
        }

        PlayerButton hitButton = playingView.getHitButton();
        PlayerButton stayButton = playingView.getStayButton();

        if (isIn(e,hitButton))
            hitButton.setMousePressed(true);

        if (isIn(e, stayButton))
            stayButton.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (playingModel.getSelectProfile()) {
            selectProfileController.mouseReleased(e);
            return;
        }

        if (playingModel.getPause()) {
            pauseOverlayController.mouseReleased(e);
            return;
        }

        if (playingModel.isGameFinished()){
            gameFinishedController.mouseReleased(e);
            return;
        }

        PlayerButton hitButton = playingView.getHitButton();
        PlayerButton stayButton = playingView.getStayButton();

        if (isIn(e, hitButton)){
            if (hitButton.isMousePressed() && !deactiveHitButton){
                hitButtonPressed();
            }
            hitButton.setMousePressed(false);
        }

        if (isIn(e, stayButton)){
            if (stayButton.isMousePressed()){
                stayButtonPressed();
            }
            stayButton.setMousePressed(false);
        }

        playingView.resetButtons();
    }

    public void mouseMoved(MouseEvent e) {
        if (playingModel.getSelectProfile()) {
            selectProfileController.mouseMoved(e);
            return;
        }

        if (playingModel.getPause()) {
            pauseOverlayController.mouseMoved(e);
            return;
        }

        if (playingModel.isGameFinished()){
            gameFinishedController.mouseMoved(e);
            return;
        }

        PlayerButton hitButton = playingView.getHitButton();
        PlayerButton stayButton = playingView.getStayButton();

        hitButton.setMouseOver(false);
        stayButton.setMouseOver(false);

        if (isIn(e, hitButton)){
            hitButton.setMouseOver(true);
        }

        if (isIn(e, stayButton)){
            stayButton.setMouseOver(true);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                MusicManager.getInstance().stopPlayingSong();
                playingModel.setPaused(!playingModel.getPause());
                break;
            default:
                break;
        }
        if (playingModel.getSelectProfile()) {
            selectProfileController.keyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void hitButtonPressed(){
        playingModel.getPlayer().
                hit();
        if (playingModel.getPlayer().getHandSum() > 21){
            deactiveHitButton = true;
        }
    }

    public void stayButtonPressed(){
        deactiveHitButton = true;
        playingModel.nextTurn();
    }

    public boolean isIn(MouseEvent e, PlayerButton mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public void mouseClicked(MouseEvent e) {
    }

    public boolean isHitButtonDeactivated() {
        return deactiveHitButton;
    }
}