package main;

import controller.PlayingController;
import controller.inputs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.utilz.Constants;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    private Game game;

    public GamePanel(Game game) {

        this.game = game;
        mouseInputs = new MouseInputs(this, game.getPlayerController());
        keyboardInputs = new KeyboardInputs(this, game.getPlayerController());

        setBackground(new Color (53,101,77));
        setPanelSize();

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setPanelSize() {
        Dimension dimension = new Dimension(Constants.WIDTH, Constants.HEIGHT);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        System.out.println("Main.Main.GamePanel size: " + Constants.WIDTH + "x" + Constants.HEIGHT);
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        game.draw(g);

    }
}
