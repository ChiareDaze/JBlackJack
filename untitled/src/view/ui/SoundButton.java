package view.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.PauseButtons.*;

public class SoundButton extends PauseButton {

    private BufferedImage[][] soundImgs;
    private boolean mouseOver, mousePressed;
    private boolean muted;
    private int rowIndex, colIndex;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        loadSoundImgs();
    }

    private void loadSoundImgs() {
        BufferedImage temp = model.utilz.Load.ImportImg(model.utilz.Load.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][3];
        for (int j = 0; j < soundImgs.length; j++)
            for (int i = 0; i < soundImgs[j].length; i++) {
                soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE, j * SOUND_SIZE, SOUND_SIZE, SOUND_SIZE);
            }
    }

    public void update(){

        if (muted)
            rowIndex = 1;
        else 
            rowIndex = 0;

        if (mouseOver)
            colIndex = 1;
        else
            colIndex = 0;
    }

    public void reset(){
        mouseOver = false;
        mousePressed = false;
    }

    public void draw(Graphics g){
        //System.out.println(mouseOver);
        g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
