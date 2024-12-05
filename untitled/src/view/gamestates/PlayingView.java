package view.gamestates;

import gameStates.PlayingModel;
import view.cards.CardManagerView;

import java.awt.*;

public class PlayingView {

    private PlayingModel playingModel = PlayingModel.getInstance();
    private static PlayingView instance;

    private PlayingView() {
    }

    public static PlayingView getInstance() {
        if (instance == null) {
            instance = new PlayingView();
        }
        return instance;
    }

    public void draw(Graphics g) {
        CardManagerView.getInstance().draw(g);

        if (playingModel.getPause()) {
            playingModel.getPauseOverlay().draw(g);
        }
    }
}