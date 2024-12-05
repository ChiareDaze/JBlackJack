package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gameStates.Gamestate;
import gameStates.Menu;
import gameStates.PlayingModel;
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
                Menu.getInstance().keyPressed(e);
                break;
            case PLAYING:
                PlayingModel.getInstance().keyPressed(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state){
            case MENU:
                Menu.getInstance().keyReleased(e);
                break;
            case PLAYING:
                PlayingModel.getInstance().keyReleased(e);
                break;
            default:
                break;
        }

    }
}
