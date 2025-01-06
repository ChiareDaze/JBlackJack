package controller.inputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.MenuController;
import controller.PlayingController;
import model.gameStates.Gamestate;
import view.GamePanel;

/**
 * The KeyboardInputs class implements the KeyListener interface to handle keyboard inputs.
 */
public class KeyboardInputs implements KeyListener {

    private final GamePanel gamePanel;
    private final PlayingController playingController;

    /**
     * Constructs a KeyboardInputs object with the specified game panel and playing controller.
     *
     * @param gamePanel the game panel associated with the keyboard inputs
     * @param playingController the playing controller associated with the keyboard inputs
     */
    public KeyboardInputs(GamePanel gamePanel, PlayingController playingController) {
        this.gamePanel = gamePanel;
        this.playingController = playingController;
    }

    /**
     * Invoked when a key has been typed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
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

    /**
     * Invoked when a key has been released.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
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
