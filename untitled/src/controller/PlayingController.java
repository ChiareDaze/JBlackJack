package controller;

import model.gameStates.PlayingModel;
import main.GamePanel;
import view.gamestates.PlayingView;
import view.ui.MenuButton;
import view.ui.PauseOverlay;
import view.ui.PlayerButton;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayingController {

    private PlayingModel playingModel = PlayingModel.getInstance();
    private PauseOverlayController pauseOverlayController;
    private PlayingView playingView = PlayingView.getInstance();
    private boolean deactiveHitButton = false;


    public PlayingController(PauseOverlayController pauseOverlayController) {
        this.pauseOverlayController = pauseOverlayController;

    }

    public void mousePressed(MouseEvent e) {
        if (playingModel.getPause())
            pauseOverlayController.mousePressed(e);

        PlayerButton hitButton = playingView.getHitButton();
        PlayerButton stayButton = playingView.getStayButton();

        if (isIn(e,hitButton))
            hitButton.setMousePressed(true);

        if (isIn(e, stayButton))
            stayButton.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (playingModel.getPause())
            pauseOverlayController.mouseReleased(e);

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
        if (playingModel.getPause())
            pauseOverlayController.mouseMoved(e);

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
                playingModel.setPaused(!playingModel.getPause());
                break;
            default:
                break;
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
