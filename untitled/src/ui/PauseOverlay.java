package ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.PauseButtons.SOUND_SIZE;

public class PauseOverlay {

    private BufferedImage background;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;

    public PauseOverlay(){

        loadBackground();
        createSoundButtons();

    }


    private void createSoundButtons() {
        musicButton = new SoundButton(335, 215, SOUND_SIZE, 42);
        sfxButton = new SoundButton(335, 263, SOUND_SIZE, 42);
    }

    private void loadBackground() {
        background = utilz.Load.ImportImg(utilz.Load.PAUSE_BACKGROUND);
        bgW = background.getWidth();
        bgH = background.getHeight();
        bgX = utilz.Constants.WIDTH / 2 - bgW / 2;
        bgY = 100;
    }

    public void update(){

        musicButton.update();
        sfxButton.update();
    }

    public void draw(Graphics g){
        //background
        g.drawImage(background, bgX, bgY, bgW, bgH, null);

        //sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton)){
            musicButton.setMousePressed(true);
        }

        else if (isIn(e, sfxButton)){
            sfxButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {

        if (isIn(e, musicButton)){
            if (musicButton.isMousePressed()){
                musicButton.setMuted(!musicButton.isMuted());
            }
        }

        else if (isIn(e, sfxButton)){
            if (sfxButton.isMousePressed()){
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        }

    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);

        if (isIn(e, musicButton)){
            musicButton.setMouseOver(true);
        }

        else if (isIn(e, sfxButton)){
            sfxButton.setMouseOver(true);
        }
    }

    private boolean isIn(MouseEvent e, PauseButton button){
        return (button.getBounds().contains(e.getX(), e.getY()));
    }
}
