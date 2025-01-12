package view;

import controller.inputs.*;

import javax.swing.*;
import java.awt.*;

import controller.Game;
import model.utilz.Constants;

/**
 * The GamePanel class represents the main panel of the game.
 * It handles the initialization of input listeners and the drawing of game components.
 */
public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    private Game game;

    /**
     * Constructs a GamePanel object with the specified game.
     *
     * @param game the game instance to be associated with this panel
     */
    public GamePanel(Game game) {

        this.game = game;
        mouseInputs = new MouseInputs(this, game.getPlayerController());
        keyboardInputs = new KeyboardInputs(this, game.getPlayerController());

        setBackground(new Color(53, 101, 77));
        setPanelSize();

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Sets the size of the panel based on the constants defined in the Constants class.
     */
    private void setPanelSize() {
        Dimension dimension = new Dimension(Constants.WIDTH, Constants.HEIGHT);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        System.out.println("Main.Main.GamePanel size: " + Constants.WIDTH + "x" + Constants.HEIGHT);
    }

    /**
     * Paints the game components on the panel.
     *
     * @param g the Graphics context to draw on
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);
    }
}