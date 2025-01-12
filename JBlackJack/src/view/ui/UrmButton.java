package view.ui;

import view.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.URMButtons.*;

/**
 * The UrmButton class represents a button used in the pause menu.
 * It handles the button's state, appearance, and interactions.
 */
public class UrmButton extends PauseButton{

    private BufferedImage[] imgs;
    private int rowIndex, index;
    private boolean mouseOver, mousePressed;

    /**
     * Constructs a UrmButton object with the specified position, size, and row index.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param width the width of the button
     * @param height the height of the button
     * @param rowIndex the row index of the button images
     */
    public UrmButton(int x, int y, int width, int height, int rowIndex){
        super(x, y, width, height);
        this.rowIndex = rowIndex;

        loadImgs();
    }

    /**
     * Loads the images for the button states (normal, mouse over, and pressed).
     */
    private void loadImgs() {
        BufferedImage temp = Load.ImportImg(Load.URM_BUTTONS);
        imgs = new BufferedImage[3];

        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * URM_WIDTH, rowIndex * URM_WIDTH, URM_WIDTH, URM_WIDTH);
        }
    }

    /**
     * Updates the state of the button based on mouse interactions.
     */
    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }
        if (mousePressed){
            index = 2;
        }
    }

    /**
     * Draws the button on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(Graphics g){
        g.drawImage(imgs[index], x, y, URM_WIDTH, URM_WIDTH, null);
    }

    /**
     * Resets the mouse interaction states of the button.
     */
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }

    /**
     * Returns whether the mouse is pressing the button.
     *
     * @return true if the mouse is pressing the button, false otherwise
     */
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Sets whether the mouse is over the button.
     *
     * @param mouseOver true if the mouse is over the button, false otherwise
     */
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * Sets whether the mouse is pressing the button.
     *
     * @param mousePressed true if the mouse is pressing the button, false otherwise
     */
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}