package view.ui;

import java.awt.*;

/**
 * The PauseButton class represents a button used to pause the game.
 * It handles the button's position, size, and bounding rectangle.
 */
public class PauseButton {

    protected int x, y, width, height;
    protected Rectangle bounds;

    /**
     * Constructs a PauseButton object with the specified position and size.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param width the width of the button
     * @param height the height of the button
     */
    public PauseButton(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        createBounds();
    }

    /**
     * Creates the bounding rectangle for the button.
     */
    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Returns the x-coordinate of the button.
     *
     * @return the x-coordinate of the button
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the button.
     *
     * @return the y-coordinate of the button
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the width of the button.
     *
     * @return the width of the button
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the button.
     *
     * @return the height of the button
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the bounding rectangle of the button.
     *
     * @return the bounding rectangle of the button
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Sets the x-coordinate of the button.
     *
     * @param x the new x-coordinate of the button
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the button.
     *
     * @param y the new y-coordinate of the button
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the width of the button.
     *
     * @param width the new width of the button
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height of the button.
     *
     * @param height the new height of the button
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the bounding rectangle of the button.
     *
     * @param bounds the new bounding rectangle of the button
     */
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}