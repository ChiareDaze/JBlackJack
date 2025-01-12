package view.gamestates;

import controller.PlayingController;
import model.gameStates.PlayingModel;
import view.cards.CardManagerView;
import view.music.MusicManager;
import view.ui.*;

import java.awt.*;

/**
 * The PlayingView class is responsible for managing and rendering the playing view of the game.
 * It handles the drawing and updating of various game components such as buttons, overlays, and card views.
 */
public class PlayingView {

    private PlayingModel playingModel = PlayingModel.getInstance();
    private GameFinished gameFinished = GameFinished.getInstance();
    private SelectProfile selectProfile = SelectProfile.getInstance();
    private static PlayingView instance;
    private PauseOverlay pauseOverlay;
    private PlayerButton hitButton, stayButton;
    private PlayingController playingController;
    private boolean isSongPlaying = false;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private PlayingView() {
    }

    /**
     * Initializes the hit and stay buttons with their positions and the playing controller.
     */
    private void initButtons() {
        hitButton = new PlayerButton(400, 620, 0, playingController);
        stayButton = new PlayerButton(600, 620, 1, playingController);
    }

    /**
     * Returns the singleton instance of the PlayingView class.
     * If the instance is null, it creates a new instance.
     *
     * @return the singleton instance of PlayingView
     */
    public static PlayingView getInstance() {
        if (instance == null) {
            instance = new PlayingView();
        }
        return instance;
    }

    /**
     * Starts the pause overlay by setting the provided PauseOverlay instance.
     *
     * @param pauseOverlay the PauseOverlay instance to set
     */
    public void startPauseOverlay(PauseOverlay pauseOverlay) {
        this.pauseOverlay = pauseOverlay;
    }

    /**
     * Starts the playing controller by setting the provided PlayingController instance
     * and initializing the buttons. Also starts the playing controller in the GameFinished instance.
     *
     * @param playingController the PlayingController instance to set
     */
    public void startPlayingController(PlayingController playingController) {
        this.playingController = playingController;
        initButtons();

        gameFinished.startPlayingController(playingController);
    }

    /**
     * Draws the playing view on the given Graphics context.
     * It includes drawing the card manager view, pause overlay, buttons, and game finished view.
     *
     * @param g the Graphics context to draw on
     */
    public void draw(Graphics g) {
        if (!isSongPlaying) {
            MusicManager.getInstance().playPlayingSong();
            isSongPlaying = true;
        }

        if (playingModel.getSelectProfile()){
            selectProfile.draw(g);
            return;
        }

        CardManagerView.getInstance().draw(g);

        if (playingModel.getPause()) {
            pauseOverlay.draw(g);
        }

        hitButton.draw(g);
        stayButton.draw(g);

        if (playingModel.isGameFinished()) {
            gameFinished.draw(g);
        }
    }

    /**
     * Updates the state of the playing view.
     * It includes updating the select profile, pause overlay, buttons, and game finished view.
     */
    public void update(){
        if (playingModel.getSelectProfile()){
            selectProfile.update();
            return;
        }

        if (!playingModel.getPause()){
            pauseOverlay.update();
        }

        else {
            pauseOverlay.update();
        }

        hitButton.update();
        stayButton.update();

        if (playingModel.isGameFinished()) {
            gameFinished.update();
        }
    }

    /**
     * Resets the state of the hit and stay buttons.
     */
    public void resetButtons() {
        hitButton.resetBools();
        stayButton.resetBools();
    }

    /**
     * Returns the hit button.
     *
     * @return the hit button
     */
    public PlayerButton getHitButton() {
        return hitButton;
    }

    /**
     * Returns the stay button.
     *
     * @return the stay button
     */
    public PlayerButton getStayButton() {
        return stayButton;
    }
}