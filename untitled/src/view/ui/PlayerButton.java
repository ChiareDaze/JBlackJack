package view.ui;

import controller.PlayingController;
import model.gameStates.PlayingModel;
import model.utilz.Constants;
import view.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.UI.Buttons.*;

/**
 * The PlayerButton class represents a button used by the player in the game.
 * It handles the button's state, appearance, and interactions.
 */
public class PlayerButton {

    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;
    private PlayingController playingController;

    /**
     * Constructs a PlayerButton object with the specified position, row index, and playing controller.
     *
     * @param xPos the x-coordinate of the button
     * @param yPos the y-coordinate of the button
     * @param rowIndex the row index of the button images
     * @param playingController the playing controller associated with the button
     */
    public PlayerButton(int xPos, int yPos, int rowIndex, PlayingController playingController) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.playingController = playingController;

        loadImages();
        initBounds();
    }

    /**
     * Loads the images for the button states (normal, mouse over, and pressed).
     */
    private void loadImages() {
        imgs = new BufferedImage[3];
        BufferedImage temp = Load.ImportImg(Load.PLAYER_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH, rowIndex * B_HEIGHT, B_WIDTH, B_HEIGHT);
        }
    }

    /**
     * Initializes the bounds of the button.
     */
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    /**
     * Draws the button on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(java.awt.Graphics g) {
        if (PlayingModel.getInstance().getCurrentTurn() != Constants.Turns.PLAYER) {
            g.drawImage(imgs[2], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
            return;
        }

        if (playingController.isHitButtonDeactivated() && rowIndex == 0)
            g.drawImage(imgs[2], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
        else
            g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    /**
     * Updates the state of the button based on mouse interactions.
     */
    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
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

    /**
     * Returns the bounds of the button.
     *
     * @return the bounds of the button
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Resets the mouse interaction states of the button.
     */
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}