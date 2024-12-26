package controller;

import model.gameStates.Gamestate;
import model.gameStates.MenuModel;
import model.gameStates.PlayingModel;
import view.gamestates.MenuView;
import view.ui.BotButton;
import view.ui.MenuButton;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuController {

    private MenuModel menuModel = MenuModel.getInstance();
    private MenuView menuView = MenuView.getInstance();
    private PlayingModel playingModel = PlayingModel.getInstance();
    private static MenuController instance;

    private MenuController() {
    }

    public static MenuController getInstance(){
        if(instance == null){
            instance = new MenuController();
        }
        return instance;
    }

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

    public void mouseReleased(MouseEvent e) {
        for (MenuButton button : menuView.getButtons()) {
            if (isInMenuButton(e,button)){
                if (button.isMousePressed())
                    button.applyGameState();
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

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public boolean isInMenuButton(MouseEvent e, MenuButton mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public boolean isInBotButton(MouseEvent e, BotButton bb){
        return bb.getBounds().contains(e.getX(), e.getY());
    }
}
