package view.ui;


import view.Load;
import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.PauseButtons.SOUND_SIZE;
import static model.utilz.Constants.URMButtons.URM_WIDTH;

/**
 * The BotButton class represents a button used to control the number of bots in the game.
 * It handles the button's state, appearance, and interactions.
 */
public class BotButton {

    private boolean mouseOver, mousePressed;
    private int rowIndex;
    protected int x, y, width, height, index;
    protected Rectangle bounds;
    private BufferedImage[] botImgs;

    /**
     * Constructs a BotButton object with the specified position, size, and row index.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param width the width of the button
     * @param height the height of the button
     * @param rowIndex the row index of the button images
     */
    public BotButton(int x, int y, int width, int height, int rowIndex){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rowIndex = rowIndex;

        loadBotImgs();
        initBounds();
    }

    /**
     * Initializes the bounds of the button.
     */
    private void initBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Loads the images for the button states (normal, mouse over, and pressed).
     */
    private void loadBotImgs() {
        BufferedImage temp = Load.ImportImg(Load.BOT_BUTTONS);
        botImgs = new BufferedImage[3];

        for (int i = 0; i < botImgs.length; i++) {
            botImgs[i] = temp.getSubimage(i * SOUND_SIZE, rowIndex * SOUND_SIZE, SOUND_SIZE, SOUND_SIZE);
        }
    }

    /**
     * Draws the button on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(Graphics g){
        g.drawImage(botImgs[index], x, y, URM_WIDTH, URM_WIDTH, null);
    }

    /**
     * Updates the state of the button based on mouse interactions.
     */
    public void update(){
        index = 0;
        if (mouseOver) index = 1;
        if (mousePressed) index = 2;
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
    public Rectangle getBounds(){
        return bounds;
    }

    /**
     * Resets the mouse interaction states of the button.
     */
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
}