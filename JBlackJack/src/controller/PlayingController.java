package controller;

import model.gameStates.PlayingModel;
import view.gamestates.PlayingView;
import view.music.MusicManager;
import view.ui.PlayerButton;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The PlayingController class handles the mouse and keyboard events for the playing state of the game.
 */
public class PlayingController {

    private PlayingModel playingModel = PlayingModel.getInstance();
    private SelectProfileController selectProfileController = SelectProfileController.getInstance();
    private PauseOverlayController pauseOverlayController;
    private GameFinishedController gameFinishedController;
    private PlayingView playingView = PlayingView.getInstance();
    private boolean deactiveHitButton = false;

    /**
     * Constructs a PlayingController object with the specified PauseOverlayController.
     *
     * @param pauseOverlayController the PauseOverlayController associated with the playing state
     */
    public PlayingController(PauseOverlayController pauseOverlayController) {
        this.pauseOverlayController = pauseOverlayController;
        gameFinishedController = new GameFinishedController();
    }

    /**
     * Handles the mouse pressed event.
     *
     * @param e the MouseEvent to be processed
     */
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

        if (isIn(e, hitButton))
            hitButton.setMousePressed(true);

        if (isIn(e, stayButton))
            stayButton.setMousePressed(true);
    }

    /**
     * Handles the mouse released event.
     *
     * @param e the MouseEvent to be processed
     */
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

    /**
     * Handles the mouse moved event.
     *
     * @param e the MouseEvent to be processed
     */
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

    /**
     * Handles the key pressed event.
     *
     * @param e the KeyEvent to be processed
     */
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

    /**
     * Handles the key released event.
     *
     * @param e the KeyEvent to be processed
     */
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Handles the action when the hit button is pressed.
     */
    public void hitButtonPressed(){
        playingModel.getPlayer().hit();
        if (playingModel.getPlayer().getHandSum() > 21){
            deactiveHitButton = true;
        }
    }

    /**
     * Handles the action when the stay button is pressed.
     */
    public void stayButtonPressed(){
        deactiveHitButton = true;
        playingModel.nextTurn();
    }

    /**
     * Checks if the mouse event is within the bounds of the specified player button.
     *
     * @param e the MouseEvent to be checked
     * @param mb the PlayerButton to be checked
     * @return true if the mouse event is within the bounds of the button, false otherwise
     */
    public boolean isIn(MouseEvent e, PlayerButton mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * Handles the mouse clicked event.
     *
     * @param e the MouseEvent to be processed
     */
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Returns whether the hit button is deactivated.
     *
     * @return true if the hit button is deactivated, false otherwise
     */
    public boolean isHitButtonDeactivated() {
        return deactiveHitButton;
    }
}