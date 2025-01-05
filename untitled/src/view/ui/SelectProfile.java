package view.ui;

import controller.PlayingController;
import model.profiles.ProfilesManager;
import model.utilz.Constants;
import java.awt.*;

import static model.utilz.Constants.PauseButtons.SOUND_SIZE;

public class SelectProfile {

    private static SelectProfile instance;
    private ProfilesManager profilesManager = ProfilesManager.getInstance();
    private PlayingController playingController;
    private SelectProfileArrowButton left;
    private SelectProfileArrowButton right;
    private PlayAddButtons add;
    private PlayAddButtons play;
    private boolean profileCreation = false;
    private String creationName = "";


    private SelectProfile() {
        initButtons();
    }

    public static SelectProfile getInstance() {
        if (instance == null) {
            instance = new SelectProfile();
        }
        return instance;
    }

    public void startPlayingController(PlayingController playingController) {
        this.playingController = playingController;
        initButtons();
    }

    private void initButtons() {
        left = new SelectProfileArrowButton(135, 620, SOUND_SIZE, SOUND_SIZE, 0);
        right = new SelectProfileArrowButton(800, 620, SOUND_SIZE, SOUND_SIZE, 1);

        add = new PlayAddButtons(350, 620, 0, playingController);
        play = new PlayAddButtons(650, 620, 1, playingController);
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
            drawProfileCreation(g);
        } else {
            drawCurrentProfileName(g);
        }
    }

    private void drawProfileCreation(Graphics g) {
        String title = "Enter Profile Name";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(title, getCenteredTextPosition(g, title), 300);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (profilesManager.doesProfileExist(creationName)) {
            g.setColor(Color.RED);
        }
        g.drawString(creationName, getCenteredTextPosition(g, creationName), 350);
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
}