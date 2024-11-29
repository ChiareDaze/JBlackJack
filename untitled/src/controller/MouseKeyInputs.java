package controller;

import gameStates.Gamestate;
import gameStates.Menu;
import gameStates.Playing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseKeyInputs implements MouseListener {

    private final main.GamePanel gamePanel;


    public MouseKeyInputs(main.GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        switch (Gamestate.state){
            case MENU:
                Menu.getInstance().mouseClicked(e);
                break;
            case PLAYING:
                Playing.getInstance().mouseClicked(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

        switch (Gamestate.state){
            case MENU:
                Menu.getInstance().mousePressed(e);
                break;
            case PLAYING:
                Playing.getInstance().mousePressed(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        switch (Gamestate.state){
            case MENU:
                Menu.getInstance().mouseReleased(e);
                break;
            case PLAYING:
                Playing.getInstance().mouseReleased(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
