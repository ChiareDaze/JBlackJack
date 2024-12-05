package gameStates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import ui.MenuButton;
import model.utilz.Constants;


public class Menu extends State implements StateMethods{

    private static Menu instance;
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage background;
    private int menuX, menuY, menuHeight, menuWidth;


    private Menu() {
        super();
        loadButtons();
        loadBackground();

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

    public static Menu getInstance(){
        if(instance == null){
            instance = new Menu();
        }
        return instance;
    }

    @Override
    public void update() {
        for (MenuButton button : buttons) {
            button.update();
        }
    }

    public void draw(Graphics g) {

        g.drawImage(background, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton button : buttons) {
            button.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton button : buttons) {
            if (isIn(e,button)){
                button.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton button : buttons) {
            if (isIn(e,button)){
                if (button.isMousePressed())
                    button.applyGameState();
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButton button : buttons)
            button.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton button : buttons)
            button.setMouseOver(false);

        for (MenuButton button : buttons)
            if (isIn(e,button)) {
                button.setMouseOver(true);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
