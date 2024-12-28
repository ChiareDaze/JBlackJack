package view.gamestates;

import model.gameStates.Gamestate;
import model.gameStates.MenuModel;
import model.utilz.Constants;
import view.music.MusicManager;
import view.ui.BotButton;
import view.ui.BotDisplay;
import view.ui.BotNumber;
import view.ui.MenuButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.PauseButtons.SOUND_SIZE;

public class MenuView {

    private static MenuView instance;
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage background;
    private int menuX, menuY, menuHeight, menuWidth;
    private MenuModel menuModel = MenuModel.getInstance();
    private BotButton plus, minus;
    private BotDisplay botDisplay;
    private BotNumber botNumber;
    private boolean isSongPlaying = false;

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
        menuY = 150;
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Constants.WIDTH / 2 , 260, 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Constants.WIDTH / 2 , 325, 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Constants.WIDTH / 2 , 390, 2, Gamestate.QUIT);

        plus = new BotButton(410, 600, SOUND_SIZE, SOUND_SIZE, 0);
        minus = new BotButton(540, 600, SOUND_SIZE, SOUND_SIZE, 1);

        botDisplay = new BotDisplay(475, 600, SOUND_SIZE, SOUND_SIZE);
        botNumber = new BotNumber(465, 600, 300, 42);
    }

    public void update() {
        for (MenuButton button : buttons) {
            button.update();
        }

        plus.update();
        minus.update();
    }

    public void draw(Graphics g) {
        if (!isSongPlaying) {
            MusicManager.getInstance().playMenuSong();
            isSongPlaying = true;
        }

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

        int y = 530;
        int pos = 352;

        plus.draw(g);
        minus.draw(g);
        botDisplay.draw(g);
        botNumber.draw(g,pos,y);

    }

    public void resetButtons() {
        for (MenuButton button : buttons)
            button.resetBools();

        plus.resetBools();
        minus.resetBools();
    }

    public MenuButton[] getButtons() {
        return buttons;
    }

    public BotButton getPlus() {
        return plus;
    }

    public BotButton getMinus() {
        return minus;
    }
}