package ui;

import gameStates.Gamestate;
import gameStates.Playing;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static utilz.Constants.URMButtons.*;

import static utilz.Constants.PauseButtons.SOUND_SIZE;

public class PauseOverlay {

    private BufferedImage background;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replyB, unpausedB;
    private Playing playing;

    public PauseOverlay(Playing playing){

        this.playing = playing;
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
        background = utilz.Load.ImportImg(utilz.Load.PAUSE_BACKGROUND);
        bgW = background.getWidth();
        bgH = background.getHeight();
        bgX = utilz.Constants.WIDTH / 2 - bgW / 2;
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

    public void mouseDragged(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton)){
            musicButton.setMousePressed(true);
        }

        else if (isIn(e, sfxButton)){
            sfxButton.setMousePressed(true);
        }

        else if (isIn(e, menuB)){
            menuB.setMousePressed(true);
        }

        else if (isIn(e, replyB)){
            replyB.setMousePressed(true);
        }

        else if (isIn(e, unpausedB)){
            unpausedB.setMousePressed(true);
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

        else if (isIn(e, menuB)){
            if (menuB.isMousePressed()){
                Gamestate.state = Gamestate.MENU;
                playing.unpauseGame();
            }
        }

        else if (isIn(e, replyB)){
            if (replyB.isMousePressed()){
                //Gamestate.state = Gamestate.PLAYING;
                System.out.println("reply!!");
            }
        }

        else if (isIn(e, unpausedB)){
            if (unpausedB.isMousePressed()){
                playing.unpauseGame();
            }
        }

        musicButton.reset();
        sfxButton.reset();
        menuB.resetBools();
        replyB.resetBools();
        unpausedB.resetBools();

    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replyB.setMouseOver(false);
        unpausedB.setMouseOver(false);

        if (isIn(e, musicButton)){
            musicButton.setMouseOver(true);
        }

        else if (isIn(e, sfxButton)){
            sfxButton.setMouseOver(true);
        }

        else if (isIn(e, menuB)){
            menuB.setMouseOver(true);
        }

        else if (isIn(e, replyB)){
            replyB.setMouseOver(true);
        }

        else if (isIn(e, unpausedB)){
            unpausedB.setMouseOver(true);
        }
    }

    private boolean isIn(MouseEvent e, PauseButton button){
        return (button.getBounds().contains(e.getX(), e.getY()));
    }
}
