package view.gamestates;

import model.gameStates.Gamestate;
import model.utilz.Constants;
import view.Load;
import view.music.MusicManager;
import view.ui.BotButton;
import view.ui.BotDisplay;
import view.ui.BotNumber;
import view.ui.MenuButton;
import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.PauseButtons.SOUND_SIZE;

/**
 * The MenuView class is responsible for managing and rendering the main menu of the game.
 * It includes buttons for starting the game, accessing options, and quitting the game.
 */
public class MenuView {

    private static MenuView instance;
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage background;
    private int menuX, menuY, menuHeight, menuWidth;
    private BotButton plus, minus;
    private BotDisplay botDisplay;
    private BotNumber botNumber;
    private boolean isSongPlaying = false;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the buttons and background.
     */
    private MenuView() {
        loadButtons();
        loadBackground();
    }

    /**
     * Returns the singleton instance of the MenuView class.
     * If the instance is null, it creates a new instance.
     *
     * @return the singleton instance of MenuView
     */
    public static MenuView getInstance(){
        if(instance == null){
            instance = new MenuView();
        }
        return instance;
    }

    /**
     * Loads the background image for the menu.
     * Sets the dimensions and position of the menu.
     */
    private void loadBackground() {
        background = Load.ImportImg(Load.MENU_BACKGROUND);
        menuWidth = background.getWidth();
        menuHeight = background.getHeight();
        menuX = Constants.WIDTH / 2 - menuWidth / 2;
        menuY = 150;
    }

    /**
     * Loads the buttons for the menu and initializes their positions.
     * Also initializes the buttons for adjusting the number of bots.
     */
    private void loadButtons() {
        buttons[0] = new MenuButton(Constants.WIDTH / 2 , 260, 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Constants.WIDTH / 2 , 325, 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Constants.WIDTH / 2 , 390, 2, Gamestate.QUIT);

        plus = new BotButton(410, 600, SOUND_SIZE, SOUND_SIZE, 0);
        minus = new BotButton(540, 600, SOUND_SIZE, SOUND_SIZE, 1);

        botDisplay = new BotDisplay(475, 600, SOUND_SIZE, SOUND_SIZE);
        botNumber = new BotNumber(465, 600, 300, 42);
    }

    /**
     * Updates the state of the menu buttons.
     */
    public void update() {
        for (MenuButton button : buttons) {
            button.update();
        }

        plus.update();
        minus.update();
    }

    /**
     * Draws the menu, including the background and buttons, on the given Graphics context.
     * Also plays the menu song if it is not already playing.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(Graphics g) {
        if (!isSongPlaying) {
            MusicManager.getInstance().playMenuSong();
            isSongPlaying = true;
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

    /**
     * Resets the state of the menu buttons.
     */
    public void resetButtons() {
        for (MenuButton button : buttons)
            button.resetBools();

        plus.resetBools();
        minus.resetBools();
    }

    /**
     * Returns the array of menu buttons.
     *
     * @return the array of menu buttons
     */
    public MenuButton[] getButtons() {
        return buttons;
    }

    /**
     * Returns the button for increasing the number of bots.
     *
     * @return the button for increasing the number of bots
     */
    public BotButton getPlus() {
        return plus;
    }

    /**
     * Returns the button for decreasing the number of bots.
     *
     * @return the button for decreasing the number of bots
     */
    public BotButton getMinus() {
        return minus;
    }
}