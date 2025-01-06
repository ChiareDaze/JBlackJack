package view.ui;

import view.Load;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The BotNumber class is responsible for displaying the number of bots in the game.
 * It handles loading the image for the number display and drawing it at the specified position.
 */
public class BotNumber {

    protected int x, y, width, height;
    private BufferedImage numberImg;

    /**
     * Constructs a BotNumber object with the specified position and size.
     *
     * @param x the x-coordinate of the number display
     * @param y the y-coordinate of the number display
     * @param width the width of the number display
     * @param height the height of the number display
     */
    public BotNumber(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        loadBotNumber();
    }

    /**
     * Draws the number display on the given Graphics context.
     * The image is drawn at the specified position.
     *
     * @param g the Graphics context to draw on
     * @param pos the x-coordinate to draw the image
     * @param y the y-coordinate to draw the image
     */
    public void draw(Graphics g, int pos, int y){
        g.drawImage(numberImg, pos, y, width, height, null);
    }

    /**
     * Loads the image for the number display.
     * The image is loaded from the specified path.
     */
    public void loadBotNumber() {
        numberImg = Load.ImportImg(Load.Images.GetNumberBotPath());
    }
}