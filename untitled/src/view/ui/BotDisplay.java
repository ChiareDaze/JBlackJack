package view.ui;

import model.gameStates.PlayingModel;
import view.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static model.utilz.Constants.PauseButtons.SOUND_SIZE;
import static model.utilz.Constants.URMButtons.URM_WIDTH;

/**
 * The BotDisplay class is responsible for displaying the number of bots in the game.
 * It handles loading the images for the display and drawing the appropriate image based on the number of bots.
 */
public class BotDisplay {

    protected int x, y, width, height;
    private BufferedImage[] displayImgs;
    private PlayingModel playingModel = PlayingModel.getInstance();

    /**
     * Constructs a BotDisplay object with the specified position and size.
     *
     * @param x the x-coordinate of the display
     * @param y the y-coordinate of the display
     * @param width the width of the display
     * @param height the height of the display
     */
    public BotDisplay(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        loadBotImgs();
    }

    /**
     * Loads the images for the bot display.
     * The images are loaded from a sprite sheet and stored in an array.
     */
    private void loadBotImgs() {
        BufferedImage temp = Load.ImportImg(Load.BOT_COUNT);
        displayImgs = new BufferedImage[4];

        for (int i = 0; i < displayImgs.length; i++) {
            displayImgs[i] = temp.getSubimage(i * SOUND_SIZE, 0, SOUND_SIZE, SOUND_SIZE);
        }
    }

    /**
     * Draws the bot display on the given Graphics context.
     * The image corresponding to the current number of bots is drawn.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(Graphics g){
        g.drawImage(displayImgs[playingModel.getNumberOfBots()], x, y, URM_WIDTH, URM_WIDTH, null);
    }
}