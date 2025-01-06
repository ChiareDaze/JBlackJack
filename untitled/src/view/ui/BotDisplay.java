package view.ui;

import model.gameStates.PlayingModel;
import view.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static model.utilz.Constants.PauseButtons.SOUND_SIZE;
import static model.utilz.Constants.URMButtons.URM_WIDTH;

public class BotDisplay {

    protected int x, y, width, height;
    private BufferedImage[] displayImgs;
    private PlayingModel playingModel = PlayingModel.getInstance();

    public BotDisplay(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        loadBotImgs();
    }

    private void loadBotImgs() {
        BufferedImage temp = Load.ImportImg(Load.BOT_COUNT);
        displayImgs = new BufferedImage[4];

        for (int i = 0; i < displayImgs.length; i++) {
            displayImgs[i] = temp.getSubimage(i * SOUND_SIZE, 0, SOUND_SIZE, SOUND_SIZE);
        }
    }

    public void draw(Graphics g){
        g.drawImage(displayImgs[playingModel.getNumberOfBots()], x, y, URM_WIDTH, URM_WIDTH, null);
    }
}