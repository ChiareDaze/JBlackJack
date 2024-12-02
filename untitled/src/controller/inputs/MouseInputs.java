package controller.inputs;

import gameStates.Gamestate;
import gameStates.Menu;
import gameStates.Playing;
import main.GamePanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        switch (Gamestate.state){
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        switch (Gamestate.state){
            case MENU:
                Menu.getInstance().mouseMoved(e);
                break;
            case PLAYING:
                Playing.getInstance().mouseMoved(e);
                break;
            default:
                break;
        }

    }
}
