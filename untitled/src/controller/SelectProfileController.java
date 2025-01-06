package controller;

import model.gameStates.PlayingModel;
import model.profiles.Profile;
import model.profiles.ProfilesManager;
import view.ui.PlayAddButtons;
import view.ui.SelectProfile;
import view.ui.SelectProfileArrowButton;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The SelectProfileController class handles the mouse and keyboard events for the profile selection screen.
 */
public class SelectProfileController {

    private static SelectProfileController instance;
    private SelectProfile selectProfile = SelectProfile.getInstance();
    private PlayingModel playingModel = PlayingModel.getInstance();
    private ProfilesManager profilesManager = ProfilesManager.getInstance();

    /**
     * Private constructor to prevent instantiation.
     */
    private SelectProfileController() {
    }

    /**
     * Returns the singleton instance of the SelectProfileController.
     *
     * @return the singleton instance of the SelectProfileController
     */
    public static SelectProfileController getInstance(){
        if(instance == null){
            instance = new SelectProfileController();
        }
        return instance;
    }

    /**
     * Handles the mouse pressed event.
     *
     * @param e the MouseEvent to be processed
     */
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

    /**
     * Handles the mouse released event.
     *
     * @param e the MouseEvent to be processed
     */
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

    /**
     * Handles the mouse moved event.
     *
     * @param e the MouseEvent to be processed
     */
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

    /**
     * Handles the key pressed event.
     *
     * @param e the KeyEvent to be processed
     */
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

        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            selectProfile.increaseCreationAvatarIndex();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            selectProfile.decreaseCreationAvatarIndex();
        }
    }

    /**
     * Checks if the mouse event is within the bounds of the specified play/add button.
     *
     * @param e the MouseEvent to be checked
     * @param pa the PlayAddButtons to be checked
     * @return true if the mouse event is within the bounds of the button, false otherwise
     */
    public boolean isInPlayAddButton(MouseEvent e, PlayAddButtons pa){
        return pa.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * Checks if the mouse event is within the bounds of the specified arrow button.
     *
     * @param e the MouseEvent to be checked
     * @param sp the SelectProfileArrowButton to be checked
     * @return true if the mouse event is within the bounds of the button, false otherwise
     */
    public boolean isInArrowsButton(MouseEvent e, SelectProfileArrowButton sp){
        return sp.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * Handles the action when the play button is pressed.
     */
    public void playButtonPressed() {
        if (!selectProfile.isProfileCreation()) playingModel.setSelectProfile(false);
        else {
            if (!profilesManager.doesProfileExist(selectProfile.getCreationName())) {
                profilesManager.addProfile(new Profile(selectProfile.getCreationName(), selectProfile.getCreationAvatarIndex()));
                profilesManager.selectProfile(selectProfile.getCreationName());
                playingModel.setSelectProfile(false);
            }
        }
    }
}