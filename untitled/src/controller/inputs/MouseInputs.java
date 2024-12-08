package controller.inputs;

import controller.MenuController;
import controller.PlayingController;
import model.gameStates.Gamestate;
import model.gameStates.MenuModel;
import main.GamePanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    private MenuModel menuModel = MenuModel.getInstance();
    private MenuController menuController = MenuController.getInstance();
    private PlayingController playingController = PlayingController.getInstance();

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        switch (Gamestate.state){
            case PLAYING:
                PlayingController.getInstance().mouseClicked(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

        switch (Gamestate.state){
            case MENU:
                menuController.mousePressed(e);
                break;
            case PLAYING:
                playingController.mousePressed(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        switch (Gamestate.state){
            case MENU:
                menuController.mouseReleased(e);
                break;
            case PLAYING:
                playingController.mouseReleased(e);
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
                menuController.mouseMoved(e);
                break;
            case PLAYING:
                playingController.mouseMoved(e);
                break;
            default:
                break;
        }

    }
}
