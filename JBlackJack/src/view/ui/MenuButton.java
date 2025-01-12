package view.ui;

import model.gameStates.Gamestate;
import model.utilz.Constants;
import view.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.UI.Buttons.*;

/**
 * The MenuButton class represents a button in the menu.
 * It handles the button's state, appearance, and interactions.
 */
public class MenuButton {

    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;
    private Gamestate state;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    /**
     * Constructs a MenuButton object with the specified position, row index, and game state.
     *
     * @param xPos the x-coordinate of the button
     * @param yPos the y-coordinate of the button
     * @param rowIndex the row index of the button images
     * @param state the game state associated with the button
     */
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;

        loadImages();
        initBounds();
    }

    /**
     * Initializes the bounds of the button.
     */
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    /**
     * Loads the images for the button states (normal, mouse over, and pressed).
     */
    private void loadImages() {
        imgs = new BufferedImage[3];
        BufferedImage temp = Load.ImportImg(Load.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH, rowIndex * B_HEIGHT, B_WIDTH, B_HEIGHT);
        }
    }

    /**
     * Draws the button on the given Graphics context.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(java.awt.Graphics g){
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH,  B_HEIGHT, null);

        drawTitle(g);
        drawCreator(g);
    }

    private void drawTitle(Graphics g){
        String title = "JBlackjack";
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(title, getCenteredTextPosition(g, title), 80);
    }

    public void drawCreator(Graphics g){
        String creator = "By Chiara Petrucci";
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString(creator, getCenteredTextPosition(g,creator), 100);
    }

    private static int getCenteredTextPosition(Graphics g, String text){
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (Constants.WIDTH - metrics.stringWidth(text)) / 2;
        return x;
    }

    /**
     * Updates the state of the button based on mouse interactions.
     */
    public void update(){
        index = 0;
        if (mouseOver){
            index = 1;
        }
        if (mousePressed){
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
    public Rectangle getBounds(){
        return bounds;
    }

    /**
     * Applies the game state associated with the button.
     */
    public void applyGameState(){
        Gamestate.state = state;
    }

    /**
     * Resets the mouse interaction states of the button.
     */
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
}