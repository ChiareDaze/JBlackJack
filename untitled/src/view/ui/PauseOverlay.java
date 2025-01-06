package view.ui;

import view.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.URMButtons.*;
import static model.utilz.Constants.PauseButtons.SOUND_SIZE;

/**
 * The PauseOverlay class represents the overlay displayed when the game is paused.
 * It handles the background, sound buttons, and URM (User Request Management) buttons.
 */
public class PauseOverlay {

    private BufferedImage background;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replyB, unpausedB;

    /**
     * Constructs a PauseOverlay object and initializes the background and buttons.
     */
    public PauseOverlay(){
        loadBackground();
        createSoundButtons();
        createUrmButtons();
    }

    /**
     * Creates the URM buttons (menu, reply, unpause) with their positions.
     */
    private void createUrmButtons() {
        int menuX = 405;
        int replyX = 473;
        int unpausedX = 540;
        int bY = 430;

        menuB = new UrmButton(menuX, bY, URM_WIDTH, URM_WIDTH, 2);
        replyB = new UrmButton(replyX, bY, URM_WIDTH, URM_WIDTH, 1);
        unpausedB = new UrmButton(unpausedX, bY, URM_WIDTH, URM_WIDTH, 0);
    }

    /**
     * Creates the sound buttons (music and SFX) with their positions.
     */
    private void createSoundButtons() {
        musicButton = new SoundButton(535, 265, SOUND_SIZE, 42);
        sfxButton = new SoundButton(535, 313, SOUND_SIZE, 42);
    }

    /**
     * Loads the background image for the pause overlay.
     */
    private void loadBackground() {
        background = Load.ImportImg(Load.PAUSE_BACKGROUND);
        bgW = background.getWidth();
        bgH = background.getHeight();
        bgX = model.utilz.Constants.WIDTH / 2 - bgW / 2;
        bgY = 150;
    }

    /**
     * Updates the state of the sound and URM buttons.
     */
    public void update(){
        musicButton.update();
        sfxButton.update();
        menuB.update();
        replyB.update();
        unpausedB.update();
    }

    /**
     * Draws the pause overlay, including the background, sound buttons, and URM buttons.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(Graphics g){
        // background
        g.drawImage(background, bgX, bgY, bgW, bgH, null);

        // sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);

        // URM buttons
        menuB.draw(g);
        replyB.draw(g);
        unpausedB.draw(g);
    }

    /**
     * Returns the music button.
     *
     * @return the music button
     */
    public SoundButton getMusicButton() {
        return musicButton;
    }

    /**
     * Returns the SFX button.
     *
     * @return the SFX button
     */
    public SoundButton getSfxButton() {
        return sfxButton;
    }

    /**
     * Returns the menu button.
     *
     * @return the menu button
     */
    public UrmButton getMenuB() {
        return menuB;
    }

    /**
     * Returns the reply button.
     *
     * @return the reply button
     */
    public UrmButton getReplyB() {
        return replyB;
    }

    /**
     * Returns the unpause button.
     *
     * @return the unpause button
     */
    public UrmButton getUnpausedB() {
        return unpausedB;
    }
}