package view.ui;

import model.profiles.ProfilesManager;
import model.utilz.Constants;
import view.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static model.utilz.Constants.PauseButtons.SOUND_SIZE;

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


    private SelectProfile() {
        loadImgs();
        initButtons();
    }

    public static SelectProfile getInstance() {
        if (instance == null) {
            instance = new SelectProfile();
        }
        return instance;
    }

    private void loadImgs() {
        avatars = new BufferedImage[3];

        avatars[0] = Load.ImportImg(Load.AVATAR_0);
        avatars[1] = Load.ImportImg(Load.AVATAR_1);
        avatars[2] = Load.ImportImg(Load.AVATAR_2);
    }

    private void initButtons() {
        left = new SelectProfileArrowButton(135, 620, SOUND_SIZE, SOUND_SIZE, 0);
        right = new SelectProfileArrowButton(800, 620, SOUND_SIZE, SOUND_SIZE, 1);

        add = new PlayAddButtons(350, 620, 0);
        play = new PlayAddButtons(650, 620, 1);
    }

    public void update() {
        left.update();
        right.update();
        add.update();
        play.update();
    }

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

    private void drawAvatarSelection(Graphics g) {
        g.drawImage(avatars[profilesManager.getCurrentProfile().getAvatarImgIndex()], 350, 260, 60, 60, null);
    }

    public void drawAvatarCreation(Graphics g) {
        g.drawImage(avatars[creationAvatarIndex], 465, 390, 60, 60, null);
    }

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

    public void drawCurrentProfileName(Graphics g) {
        String currentProfileName = profilesManager.getCurrentProfile().getName();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(currentProfileName,getCenteredTextPosition(g, currentProfileName), 300);
    }

    private void drawTitle(Graphics g) {
        String title = "Select Profile";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(title, getCenteredTextPosition(g, title), 100);
    }

    public void drawButtons(Graphics g) {
        left.draw(g);
        right.draw(g);
        add.draw(g);
        play.draw(g);
    }

    private static int getCenteredTextPosition(Graphics g, String text){
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (Constants.WIDTH - metrics.stringWidth(text)) / 2;
        return x;
    }

    public SelectProfileArrowButton getLeft() {
        return left;
    }

    public SelectProfileArrowButton getRight() {
        return right;
    }

    public PlayAddButtons getAdd() {
        return add;
    }

    public PlayAddButtons getPlay() {
        return play;
    }

    public void resetButtons() {
        left.setMousePressed(false);
        right.setMousePressed(false);
        add.setMousePressed(false);
        play.setMousePressed(false);
    }

    public boolean isProfileCreation() {
        return profileCreation;
    }

    public void setProfileCreation(boolean profileCreation) {
        this.profileCreation = profileCreation;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public int getCreationAvatarIndex() {
        return creationAvatarIndex;
    }

    public void increaseCreationAvatarIndex() {
        creationAvatarIndex++;
        if (creationAvatarIndex >= avatars.length) {
            creationAvatarIndex = 0;
        }
    }

    public void decreaseCreationAvatarIndex() {
        creationAvatarIndex--;
        if (creationAvatarIndex < 0) {
            creationAvatarIndex = avatars.length - 1;
        }
    }
}