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

    private JPanel buttonPanel;
    private JButton hitButton, stayButton;

    public GamePanel(Game game) {

        this.game = game;
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);

        setBackground(new Color (53,101,77));
        setPanelSize();
        initButtons();

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

    private void initButtons(){

        buttonPanel = new JPanel();
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");

        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        add(buttonPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PlayingController.getInstance().hitButtonPressed(GamePanel.this);
                repaint(); //calls paintComponent
            }
        });
        repaint();

        stayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);
                PlayingController.getInstance().stayButtonPressed();
                repaint();
            }
        });
    }

    public void deactiveHitButton(){
        hitButton.setEnabled(false);
    }

}
