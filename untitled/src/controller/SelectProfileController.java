package controller;

import model.gameStates.PlayingModel;
import model.profiles.Profile;
import model.profiles.ProfilesManager;
import view.ui.PlayAddButtons;
import view.ui.SelectProfile;
import view.ui.SelectProfileArrowButton;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SelectProfileController {
    private SelectProfile selectProfile = SelectProfile.getInstance();
    private PlayingModel playingModel = PlayingModel.getInstance();
    private ProfilesManager profilesManager = ProfilesManager.getInstance();
    private static SelectProfileController instance;


    private SelectProfileController() {
    }

    public static SelectProfileController getInstance(){
        if(instance == null){
            instance = new SelectProfileController();
        }
        return instance;
    }

    public void mousePressed(MouseEvent e) {

        SelectProfileArrowButton right = selectProfile.getLeft();
        SelectProfileArrowButton left = selectProfile.getRight();

        if (isInArrowsButton(e,right))
            right.setMousePressed(true);

        if (isInArrowsButton(e, left))
            left.setMousePressed(true);

        PlayAddButtons add = selectProfile.getAdd();
        PlayAddButtons play = selectProfile.getPlay();

        if (isInPlayAddButton(e, add)){
            add.setMousePressed(true);
        }

        if (isInPlayAddButton(e, play)){
            play.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {


        SelectProfileArrowButton left = selectProfile.getLeft();
        SelectProfileArrowButton right = selectProfile.getRight();

        if (isInArrowsButton(e, left)){
            if (left.isMousePressed())
                profilesManager.previousProfile();

        }

        if (isInArrowsButton(e, right)){
            if (right.isMousePressed())
                profilesManager.nextProfile();
        }

        PlayAddButtons add = selectProfile.getAdd();
        PlayAddButtons play = selectProfile.getPlay();

        if (isInPlayAddButton(e, add)) {
            if (add.isMousePressed()) {
                if (!selectProfile.isProfileCreation())
                    selectProfile.setProfileCreation(true);
                else selectProfile.setProfileCreation(false);
            }
        }

        if (isInPlayAddButton(e, play)) {
            if (play.isMousePressed()) {
                playButtonPressed();
            }
        }

        selectProfile.resetButtons();
    }

    public void mouseMoved(MouseEvent e) {

        SelectProfileArrowButton left = selectProfile.getLeft();
        SelectProfileArrowButton right = selectProfile.getRight();

        right.setMouseOver(false);
        left.setMouseOver(false);

        if (isInArrowsButton(e, right)){
            right.setMouseOver(true);
        }

        if (isInArrowsButton(e, left)){
            left.setMouseOver(true);
        }

        PlayAddButtons add = selectProfile.getAdd();
        PlayAddButtons play = selectProfile.getPlay();

        add.setMouseOver(false);
        play.setMouseOver(false);

        if (isInPlayAddButton(e, add)){
            add.setMouseOver(true);
        }

        if (isInPlayAddButton(e, play)){
            play.setMouseOver(true);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (!selectProfile.isProfileCreation())
            return;

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && selectProfile.getCreationName().length() > 0){
            selectProfile.setCreationName(selectProfile.getCreationName().substring(0, selectProfile.getCreationName().length() - 1));
        }
        else {
            char key = e.getKeyChar();
            if (selectProfile.getCreationName().length() < 10){
                if (Character.isLetterOrDigit(key) || e.getKeyCode() == KeyEvent.VK_SPACE)
                    selectProfile.setCreationName(selectProfile.getCreationName() + key);
            }
        }
    }

    public boolean isInPlayAddButton(MouseEvent e, PlayAddButtons pa){
        return pa.getBounds().contains(e.getX(), e.getY());
    }

    public boolean isInArrowsButton(MouseEvent e, SelectProfileArrowButton sp){
        return sp.getBounds().contains(e.getX(), e.getY());
    }

    public void playButtonPressed() {

        if (!selectProfile.isProfileCreation()) playingModel.setSelectProfile(false);
        else {
            if (!profilesManager.doesProfileExist(selectProfile.getCreationName())) {
                profilesManager.addProfile(new Profile(selectProfile.getCreationName()));
                profilesManager.selectProfile(selectProfile.getCreationName());
                playingModel.setSelectProfile(false);
            }
        }
    }
}