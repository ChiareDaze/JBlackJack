package view.ui;

import controller.PlayingController;
import model.utilz.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static model.utilz.Constants.UI.Buttons.B_HEIGHT;
import static model.utilz.Constants.UI.Buttons.B_WIDTH;

public class PlayAddButtons {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public PlayAddButtons (int xPos, int yPos, int rowIndex){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;

        loadImages();
        initBounds();
    }

    private void loadImages() {
        imgs = new BufferedImage[3];
        BufferedImage temp = Load.ImportImg(Load.SELECT_PROFILE_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH, rowIndex * B_HEIGHT, B_WIDTH, B_HEIGHT);
        }
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    public void draw(java.awt.Graphics g){
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH,  B_HEIGHT, null);
    }

    public void update(){
        index = 0;
        if (mouseOver){
            index = 1;
        }
        if (mousePressed){
            index = 2;
        }
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
}
