package view.ui;


import model.utilz.Load;
import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.PauseButtons.SOUND_SIZE;
import static model.utilz.Constants.URMButtons.URM_WIDTH;

public class BotButton {

    private boolean mouseOver, mousePressed;
    private int rowIndex;
    protected int x, y, width, height, index;
    protected Rectangle bounds;
    private BufferedImage[] botImgs;

    public BotButton(int x, int y, int width, int height, int rowIndex){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rowIndex = rowIndex;

        loadBotImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    private void loadBotImgs() {
        BufferedImage temp = model.utilz.Load.ImportImg(Load.BOT_BUTTONS);
        botImgs = new BufferedImage[3];

        for (int i = 0; i < botImgs.length; i++) {
            botImgs[i] = temp.getSubimage(i * SOUND_SIZE, rowIndex * SOUND_SIZE, SOUND_SIZE, SOUND_SIZE);
        }
    }

    public void draw(Graphics g){
        g.drawImage(botImgs[index], x, y, URM_WIDTH, URM_WIDTH, null);
    }

    public void update(){
        index = 0;
        if (mouseOver) index = 1;
        if (mousePressed) index = 2;
    }

    public boolean isMouseOver() {
        return mouseOver;
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
