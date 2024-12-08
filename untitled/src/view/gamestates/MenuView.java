package view.gamestates;

import model.gameStates.Gamestate;
import model.gameStates.MenuModel;
import model.utilz.Constants;
import view.ui.MenuButton;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuView {

    private static MenuView instance;
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage background;
    private int menuX, menuY, menuHeight, menuWidth;
    private MenuModel menuModel = MenuModel.getInstance();

    private MenuView() {
        loadButtons();
        loadBackground();
    }

    public static MenuView getInstance(){
        if(instance == null){
            instance = new MenuView();
        }
        return instance;
    }

    private void loadBackground() {
        background = model.utilz.Load.ImportImg(model.utilz.Load.MENU_BACKGROUND);
        menuWidth = background.getWidth();
        menuHeight = background.getHeight();
        menuX = Constants.WIDTH / 2 - menuWidth / 2;
        menuY = 100;
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Constants.WIDTH / 2 , 210, 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Constants.WIDTH / 2 , 275, 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Constants.WIDTH / 2 , 340, 2, Gamestate.QUIT);
    }

    public void update() {
        for (MenuButton button : buttons) {
            button.update();
        }
    }

    public void draw(Graphics g) {

        if (menuModel.isProfileSelectionActive()){
            //draw profile selection
            return;
        }
        if (menuModel.isProfileRanckingActive()){
            //draw profile ranking
            return;
        }

        g.drawImage(background, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton button : buttons) {
            button.draw(g);
        }
    }

    public void resetButtons() {
        for (MenuButton button : buttons)
            button.resetBools();
    }

    public MenuButton[] getButtons() {
        return buttons;
    }
}
