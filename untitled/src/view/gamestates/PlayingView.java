package view.gamestates;

import gameStates.PlayingModel;
import view.cards.CardManagerView;
import view.ui.PauseOverlay;

import java.awt.*;

public class PlayingView {

    private PlayingModel playingModel = PlayingModel.getInstance();
    private static PlayingView instance;
    private PauseOverlay pauseOverlay;

    private PlayingView(PauseOverlay pauseOverlay) {
        this.pauseOverlay = pauseOverlay; ;
    }

    public static PlayingView getInstance(PauseOverlay pauseOverlay) {
        if (instance == null) {
            instance = new PlayingView(pauseOverlay);
        }
        return instance;
    }

    public static PlayingView getInstance() {
        if (instance == null) {
            throw new IllegalStateException("PlayingView instance is null, use getInstance(PauseOverlay pauseOverlay) instead");
        }
        return instance;
    }

    public void draw(Graphics g) {
        CardManagerView.getInstance().draw(g);

        if (playingModel.getPause()) {
            pauseOverlay.draw(g);
        }
    }

    public void update(){
        if (!playingModel.getPause()){
            pauseOverlay.update();
        }

        else {
            pauseOverlay.update();
        }
    }
}