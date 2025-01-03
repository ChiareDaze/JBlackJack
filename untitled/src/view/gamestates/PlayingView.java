package view.gamestates;

import controller.PlayingController;
import model.gameStates.PlayingModel;
import model.utilz.Constants;
import view.cards.CardManagerView;
import view.music.MusicManager;
import view.ui.PauseOverlay;
import view.ui.PlayerButton;
import java.awt.*;

public class PlayingView {

    private PlayingModel playingModel = PlayingModel.getInstance();
    private static PlayingView instance;
    private PauseOverlay pauseOverlay;
    private PlayerButton hitButton, stayButton;
    private PlayingController playingController;
    private boolean isSongPlaying = false;

    private PlayingView() {
    }

    private void initButtons() {
        hitButton = new PlayerButton(400, 620, 0, playingController);
        stayButton = new PlayerButton(600, 620, 1, playingController);
    }

    public static PlayingView getInstance() {
        if (instance == null) {
            instance = new PlayingView();
        }
        return instance;
    }

    public void startPauseOverlay(PauseOverlay pauseOverlay) {
        this.pauseOverlay = pauseOverlay;
    }

    public void startPlayingController(PlayingController playingController) {
        this.playingController = playingController;
        initButtons();
    }

    public void draw(Graphics g) {
        if (!isSongPlaying) {
            MusicManager.getInstance().playPlayingSong();
            isSongPlaying = true;
        }

        CardManagerView.getInstance().draw(g);

        if (playingModel.getPause()) {
            pauseOverlay.draw(g);
        }

        hitButton.draw(g);
        stayButton.draw(g);

        if (playingModel.isGameFinished()) {
            drawGameFinished(g);
        }
    }

    private void drawGameFinished(Graphics g) {
        g.setColor(new Color(0,0,0,220));
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
    }

    public void update(){
        if (!playingModel.getPause()){
            pauseOverlay.update();
        }

        else {
            pauseOverlay.update();
        }

        hitButton.update();
        stayButton.update();
    }

    public void resetButtons() {
        hitButton.resetBools();
        stayButton.resetBools();
    }

    public PlayerButton getHitButton() {
        return hitButton;
    }

    public PlayerButton getStayButton() {
        return stayButton;
    }
}