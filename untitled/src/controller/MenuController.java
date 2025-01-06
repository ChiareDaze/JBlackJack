package controller;

import model.gameStates.Gamestate;
import model.gameStates.PlayingModel;
import view.gamestates.MenuView;
import view.music.MusicManager;
import view.ui.BotButton;
import view.ui.MenuButton;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The MenuController class handles the mouse and keyboard events for the menu screen.
 */
public class MenuController {

    private MenuView menuView = MenuView.getInstance();
    private PlayingModel playingModel = PlayingModel.getInstance();
    private static MenuController instance;

    /**
     * Private constructor to prevent instantiation.
     */
    private MenuController() {
    }

    /**
     * Returns the singleton instance of the MenuController.
     *
     * @return the singleton instance of the MenuController
     */
    public static MenuController getInstance(){
        if(instance == null){
            instance = new MenuController();
        }
        return instance;
    }

    /**
     * Handles the mouse pressed event.
     *
     * @param e the MouseEvent to be processed
     */
    public void mousePressed(MouseEvent e) {
        for (MenuButton button : menuView.getButtons()) {
            if (isInMenuButton(e,button)){
                button.setMousePressed(true);
                break;
            }
        }

        BotButton plus = menuView.getPlus();
        BotButton minus = menuView.getMinus();

        if (isInBotButton(e,plus))
            plus.setMousePressed(true);

        if (isInBotButton(e, minus))
            minus.setMousePressed(true);
    }

    /**
     * Handles the mouse released event.
     *
     * @param e the MouseEvent to be processed
     */
    public void mouseReleased(MouseEvent e) {
        for (MenuButton button : menuView.getButtons()) {
            if (isInMenuButton(e,button)){
                if (button.isMousePressed()) {
                    button.applyGameState();
                    MusicManager.getInstance().resetMenuSong();
                    MusicManager.getInstance().stopMenuSong();
                    MusicManager.getInstance().playPlayingSong();
                }
            }
        }

        BotButton plus = menuView.getPlus();
        BotButton minus = menuView.getMinus();

        if (isInBotButton(e, plus)){
            if (plus.isMousePressed())
                playingModel.increaseBotCount();
        }

        if (isInBotButton(e, minus)){
            if (minus.isMousePressed())
                playingModel.decreaseBotCount();
        }

        menuView.resetButtons();
    }

    /**
     * Handles the mouse moved event.
     *
     * @param e the MouseEvent to be processed
     */
    public void mouseMoved(MouseEvent e) {
        for (MenuButton button : menuView.getButtons())
            button.setMouseOver(false);

        for (MenuButton button : menuView.getButtons())
            if (isInMenuButton(e,button)) {
                button.setMouseOver(true);
                break;
            }

        BotButton plus = menuView.getPlus();
        BotButton minus = menuView.getMinus();

        plus.setMouseOver(false);
        minus.setMouseOver(false);

        if (isInBotButton(e, plus)){
            plus.setMouseOver(true);
        }

        if (isInBotButton(e, minus)){
            minus.setMouseOver(true);
        }
    }

    /**
     * Handles the key pressed event.
     *
     * @param e the KeyEvent to be processed
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            Gamestate.state = Gamestate.PLAYING;
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
     * Checks if the mouse event is within the bounds of the specified menu button.
     *
     * @param e the MouseEvent to be checked
     * @param mb the MenuButton to be checked
     * @return true if the mouse event is within the bounds of the button, false otherwise
     */
    public boolean isInMenuButton(MouseEvent e, MenuButton mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * Checks if the mouse event is within the bounds of the specified bot button.
     *
     * @param e the MouseEvent to be checked
     * @param bb the BotButton to be checked
     * @return true if the mouse event is within the bounds of the button, false otherwise
     */
    public boolean isInBotButton(MouseEvent e, BotButton bb){
        return bb.getBounds().contains(e.getX(), e.getY());
    }
}