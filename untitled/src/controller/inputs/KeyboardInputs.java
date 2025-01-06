package controller.inputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.MenuController;
import controller.PlayingController;
import model.gameStates.Gamestate;
import view.GamePanel;

public class KeyboardInputs implements KeyListener {

    private final GamePanel gamePanel;
    private final PlayingController playingController;

    public KeyboardInputs(GamePanel gamePanel, PlayingController playingController) {
        this.gamePanel = gamePanel;
        this.playingController = playingController;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (Gamestate.state){
            case MENU:
                MenuController.getInstance().keyPressed(e);
                break;
            case PLAYING:
                playingController.keyPressed(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state){
            case MENU:
                MenuController.getInstance().keyReleased(e);
                break;
            case PLAYING:
                playingController.keyReleased(e);
                break;
            default:
                break;
        }

    }
}
