package view.ui;

import model.profiles.ProfilesManager;
import model.utilz.Constants;
import view.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static model.utilz.Constants.PauseButtons.SOUND_SIZE;

/**
 * The SelectProfile class represents the UI for selecting a profile in the game.
 * It handles the display and interaction with profile selection, creation, and navigation buttons.
 */
public class SelectProfile {

    private static SelectProfile instance;
    private ProfilesManager profilesManager = ProfilesManager.getInstance();
    private SelectProfileArrowButton left;
    private SelectProfileArrowButton right;
    private PlayAddButtons add;
    private PlayAddButtons play;
    private boolean profileCreation = false;
    private String creationName = "";
    private int creationAvatarIndex = 0;
    private BufferedImage[] avatars;

    /**
     * Private constructor to prevent instantiation.
     * Initializes images and buttons.
     */
    private SelectProfile() {
        loadImgs();
        initButtons();
    }

    /**
     * Returns the singleton instance of the SelectProfile.
     *
     * @return the singleton instance of the SelectProfile
     */
    public static SelectProfile getInstance() {
        if (instance == null) {
            instance = new SelectProfile();
        }
        return instance;
    }

    /**
     * Loads the avatar images.
     */
    private void loadImgs() {
        avatars = new BufferedImage[3];

        avatars[0] = Load.ImportImg(Load.AVATAR_0);
        avatars[1] = Load.ImportImg(Load.AVATAR_1);
        avatars[2] = Load.ImportImg(Load.AVATAR_2);
    }

    /**
     * Initializes the navigation and action buttons.
     */
    private void initButtons() {
        left = new SelectProfileArrowButton(135, 620, SOUND_SIZE, SOUND_SIZE, 0);
        right = new SelectProfileArrowButton(800, 620, SOUND_SIZE, SOUND_SIZE, 1);

        add = new PlayAddButtons(350, 620, 0);
        play = new PlayAddButtons(650, 620, 1);
    }

    /**
     * Updates the state of the buttons.
     */
    public void update() {
        left.update();
        right.update();
        add.update();
        play.update();
    }

    /**
     * Draws the profile selection UI.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0,220));
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        drawButtons(g);
        drawTitle(g);
        if (profileCreation) {
            drawAvatarCreation(g);
            drawProfileCreation(g);
        } else {
            drawAvatarSelection(g);
            drawCurrentProfileName(g);
        }
    }

    /**
     * Draws the selected avatar.
     *
     * @param g the Graphics context to draw on
     */
    private void drawAvatarSelection(Graphics g) {
        g.drawImage(avatars[profilesManager.getCurrentProfile().getAvatarImgIndex()], 350, 260, 60, 60, null);
    }

    /**
     * Draws the avatar creation UI.
     *
     * @param g the Graphics context to draw on
     */
    public void drawAvatarCreation(Graphics g) {
        g.drawImage(avatars[creationAvatarIndex], 465, 390, 60, 60, null);
    }

    /**
     * Draws the profile creation UI.
     *
     * @param g the Graphics context to draw on
     */
    private void drawProfileCreation(Graphics g) {
        String title = "Enter Profile Name";
        String description = "Press the arrows keys to change the avatar";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(title, getCenteredTextPosition(g, title), 300);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (profilesManager.doesProfileExist(creationName)) {
            g.setColor(Color.RED);
        }
        g.drawString(creationName, getCenteredTextPosition(g, creationName), 350);
        g.setColor(Color.GRAY);
        g.drawString(description, getCenteredTextPosition(g, description), 520);
    }

    /**
     * Draws the current profile name.
     *
     * @param g the Graphics context to draw on
     */
    public void drawCurrentProfileName(Graphics g) {
        String currentProfileName = profilesManager.getCurrentProfile().getName();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(currentProfileName,getCenteredTextPosition(g, currentProfileName), 300);
    }

    /**
     * Draws the title of the profile selection UI.
     *
     * @param g the Graphics context to draw on
     */
    private void drawTitle(Graphics g) {
        String title = "Select Profile";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(title, getCenteredTextPosition(g, title), 100);
    }

    /**
     * Draws the navigation and action buttons.
     *
     * @param g the Graphics context to draw on
     */
    public void drawButtons(Graphics g) {
        left.draw(g);
        right.draw(g);
        add.draw(g);
        play.draw(g);
    }

    /**
     * Returns the x-coordinate for centering the text on the screen.
     *
     * @param g the Graphics context
     * @param text the text to be centered
     * @return the x-coordinate for centering the text
     */
    private static int getCenteredTextPosition(Graphics g, String text){
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (Constants.WIDTH - metrics.stringWidth(text)) / 2;
        return x;
    }

    /**
     * Returns the left arrow button.
     *
     * @return the left arrow button
     */
    public SelectProfileArrowButton getLeft() {
        return left;
    }

    /**
     * Returns the right arrow button.
     *
     * @return the right arrow button
     */
    public SelectProfileArrowButton getRight() {
        return right;
    }

    /**
     * Returns the add button.
     *
     * @return the add button
     */
    public PlayAddButtons getAdd() {
        return add;
    }

    /**
     * Returns the play button.
     *
     * @return the play button
     */
    public PlayAddButtons getPlay() {
        return play;
    }

    /**
     * Resets the state of the buttons.
     */
    public void resetButtons() {
        left.setMousePressed(false);
        right.setMousePressed(false);
        add.setMousePressed(false);
        play.setMousePressed(false);
    }

    /**
     * Returns whether profile creation mode is active.
     *
     * @return true if profile creation mode is active, false otherwise
     */
    public boolean isProfileCreation() {
        return profileCreation;
    }

    /**
     * Sets the profile creation mode.
     *
     * @param profileCreation true to activate profile creation mode, false to deactivate
     */
    public void setProfileCreation(boolean profileCreation) {
        this.profileCreation = profileCreation;
    }

    /**
     * Returns the name of the profile being created.
     *
     * @return the name of the profile being created
     */
    public String getCreationName() {
        return creationName;
    }

    /**
     * Sets the name of the profile being created.
     *
     * @param creationName the name of the profile being created
     */
    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    /**
     * Returns the index of the avatar being created.
     *
     * @return the index of the avatar being created
     */
    public int getCreationAvatarIndex() {
        return creationAvatarIndex;
    }

    /**
     * Increases the index of the avatar being created.
     */
    public void increaseCreationAvatarIndex() {
        creationAvatarIndex++;
        if (creationAvatarIndex >= avatars.length) {
            creationAvatarIndex = 0;
        }
    }

    /**
     * Decreases the index of the avatar being created.
     */
    public void decreaseCreationAvatarIndex() {
        creationAvatarIndex--;
        if (creationAvatarIndex < 0) {
            creationAvatarIndex = avatars.length - 1;
        }
    }
}