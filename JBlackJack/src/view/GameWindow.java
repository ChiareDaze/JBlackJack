package view;

import javax.swing.*;

/**
 * The GameWindow class represents the main window of the game.
 * It initializes the JFrame and sets up the game panel.
 */
public class GameWindow extends JFrame {
    private JFrame jFrame;

    /**
     * Constructs a GameWindow object with the specified game panel.
     *
     * @param gamePanel the game panel to be added to the window
     */
    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame("main.BlackJack");
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}