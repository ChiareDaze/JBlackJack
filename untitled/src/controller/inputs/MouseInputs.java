package controller.inputs;

import controller.MenuController;
import controller.PlayingController;
import model.gameStates.Gamestate;
import view.GamePanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The MouseInputs class implements the MouseListener and MouseMotionListener interfaces
 * to handle mouse inputs.
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    private MenuController menuController = MenuController.getInstance();
    private PlayingController playingController;

    /**
     * Constructs a MouseInputs object with the specified game panel and playing controller.
     *
     * @param gamePanel the game panel associated with the mouse inputs
     * @param playingController the playing controller associated with the mouse inputs
     */
    public MouseInputs(GamePanel gamePanel, PlayingController playingController) {
        this.gamePanel = gamePanel;
        this.playingController = playingController;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state){
            case PLAYING:
                playingController.mouseClicked(e);
                break;
            default:
                break;
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
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

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
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

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then dragged.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
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
