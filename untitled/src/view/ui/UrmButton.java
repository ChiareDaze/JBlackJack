package view.ui;

import model.utilz.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.URMButtons.*;

public class UrmButton extends PauseButton{
    
    private BufferedImage[] imgs;
    private int rowIndex, index;
    private boolean mouseOver, mousePressed;
    
    public UrmButton(int x, int y, int width, int height, int rowIndex){
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage temp = model.utilz.Load.ImportImg(Load.URM_BUTTONS);
        imgs = new BufferedImage[3];

        for (int i = 0; i < imgs.length; i++) {
                imgs[i] = temp.getSubimage(i * URM_WIDTH, rowIndex * URM_WIDTH, URM_WIDTH, URM_WIDTH);
        }
    }

    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }
        if (mousePressed){
            index = 2;
        }
        
    }
    
    public void draw(Graphics g){

        g.drawImage(imgs[index], x, y, URM_WIDTH, URM_WIDTH, null);
        
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
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
}
