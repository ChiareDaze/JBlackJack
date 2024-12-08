package controller;

import model.gameStates.Gamestate;
import model.gameStates.MenuModel;
import view.gamestates.MenuView;
import view.ui.MenuButton;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuController {

    private MenuModel menuModel = MenuModel.getInstance();
    private MenuView menuView = MenuView.getInstance();
    private static MenuController instance;

    private MenuController() {
    }

    public static MenuController getInstance(){
        if(instance == null){
            instance = new MenuController();
        }
        return instance;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        for (MenuButton button : menuView.getButtons()) {
            if (isIn(e,button)){
                button.setMousePressed(true);
                break;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (MenuButton button : menuView.getButtons()) {
            if (isIn(e,button)){
                if (button.isMousePressed())
                    button.applyGameState();
            }
        }
        menuView.resetButtons();
    }

    public void mouseMoved(MouseEvent e) {
        for (MenuButton button : menuView.getButtons())
            button.setMouseOver(false);

        for (MenuButton button : menuView.getButtons())
            if (isIn(e,button)) {
                button.setMouseOver(true);
                break;
            }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public boolean isIn(MouseEvent e, MenuButton mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }
}
