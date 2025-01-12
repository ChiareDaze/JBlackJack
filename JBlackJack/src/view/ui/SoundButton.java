package view.ui;

import view.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.PauseButtons.*;

/**
 * The SoundButton class represents a button used to control sound in the game.
 * It handles the button's state, appearance, and interactions.
 */
public class SoundButton extends PauseButton {

    private BufferedImage[][] soundImgs;
    private boolean mouseOver, mousePressed;
    private boolean muted;
    private int rowIndex, colIndex;

    /**
     * Constructs a SoundButton object with the specified position and size.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param width the width of the button
     * @param height the height of the button
     */
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        loadSoundImgs();
    }

    /**
     * Loads the images for the button states (normal, mouse over, and pressed).
     */
    private void loadSoundImgs() {
        BufferedImage temp = Load.ImportImg(Load.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][3];
        for (int j = 0; j < soundImgs.length; j++)
            for (int i = 0; i < soundImgs[j].length; i++) {
                soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE, j * SOUND_SIZE, SOUND_SIZE, SOUND_SIZE);
            }
    }

    /**
     * Updates the state of the button based on mouse interactions and mute status.
     */
    public void update() {
        if (muted)
            rowIndex = 1;
        else
            rowIndex = 0;

        if (mouseOver)
            colIndex = 1;
        else
            colIndex = 0;
    }

    /**
     * Resets the mouse interaction states of the button.
     */
    public void reset() {
        mouseOver = false;
        mousePressed = false;
    }

    /**
     * Draws the button on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(Graphics g) {
        g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);
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
     * Returns whether the mouse is over the button.
     *
     * @return true if the mouse is over the button, false otherwise
     */
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * Returns whether the button is muted.
     *
     * @return true if the button is muted, false otherwise
     */
    public boolean isMuted() {
        return muted;
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
     * Sets whether the mouse is over the button.
     *
     * @param mouseOver true if the mouse is over the button, false otherwise
     */
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * Sets whether the button is muted.
     *
     * @param muted true to mute the button, false to unmute
     */
    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}