package view.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.URMButtons.*;

import static model.utilz.Constants.PauseButtons.SOUND_SIZE;

public class PauseOverlay {

    private BufferedImage background;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replyB, unpausedB;

    public PauseOverlay(){

        loadBackground();
        createSoundButtons();
        createUrmButtons();

    }

    private void createUrmButtons() {

        int menuX = 205;
        int replyX = 273;
        int unpausedX = 340;
        int bY = 380;

        menuB = new UrmButton(menuX, bY,URM_WIDTH, URM_WIDTH, 2);
        replyB = new UrmButton(replyX, bY,URM_WIDTH, URM_WIDTH, 1);
        unpausedB = new UrmButton(unpausedX, bY,URM_WIDTH, URM_WIDTH, 0);

    }


    private void createSoundButtons() {
        musicButton = new SoundButton(335, 215, SOUND_SIZE, 42);
        sfxButton = new SoundButton(335, 263, SOUND_SIZE, 42);
    }

    private void loadBackground() {
        background = model.utilz.Load.ImportImg(model.utilz.Load.PAUSE_BACKGROUND);
        bgW = background.getWidth();
        bgH = background.getHeight();
        bgX = model.utilz.Constants.WIDTH / 2 - bgW / 2;
        bgY = 100;
    }

    public void update(){

        musicButton.update();
        sfxButton.update();

        menuB.update();
        replyB.update();
        unpausedB.update();
    }

    public void draw(Graphics g){
        //background
        g.drawImage(background, bgX, bgY, bgW, bgH, null);

        //sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);

        //urm buttons
        menuB.draw(g);
        replyB.draw(g);
        unpausedB.draw(g);
    }

    public SoundButton getMusicButton() {
        return musicButton;
    }

    public SoundButton getSfxButton() {
        return sfxButton;
    }

    public UrmButton getMenuB() {
        return menuB;
    }

    public UrmButton getReplyB() {
        return replyB;
    }

    public UrmButton getUnpausedB() {
        return unpausedB;
    }
}
