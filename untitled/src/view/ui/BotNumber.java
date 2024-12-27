package view.ui;

import model.utilz.Load;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BotNumber {

    protected int x, y, width, height;
    private BufferedImage numberImg;

    public BotNumber(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        loadBotNumber();
    }

    public void draw(Graphics g, int pos, int y){
        g.drawImage(numberImg, pos, y, width, height, null);
    }

    public void loadBotNumber() {
        numberImg = Load.ImportImg(Load.Images.GetNumberBotPath());
    }
}