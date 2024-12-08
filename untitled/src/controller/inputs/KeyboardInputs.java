package controller.inputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.MenuController;
import controller.PlayingController;
import model.gameStates.Gamestate;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {

    private final main.GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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
                PlayingController.getInstance().keyPressed(e);
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
                PlayingController.getInstance().keyReleased(e);
                break;
            default:
                break;
        }

    }
}
