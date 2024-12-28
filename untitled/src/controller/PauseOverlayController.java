package controller;

import model.gameStates.Gamestate;
import model.gameStates.PlayingModel;
import view.music.MusicManager;
import view.ui.PauseButton;
import view.ui.PauseOverlay;

import java.awt.event.MouseEvent;

public class PauseOverlayController {

    private PauseOverlay pauseOverlay = new PauseOverlay();
    private PlayingModel playingModel = PlayingModel.getInstance();


    public void mousePressed(MouseEvent e) {
        if (isIn(e, pauseOverlay.getMusicButton())){
            pauseOverlay.getMusicButton().setMousePressed(true);
        }

        else if (isIn(e, pauseOverlay.getSfxButton())){
            pauseOverlay.getSfxButton().setMousePressed(true);
        }

        else if (isIn(e, pauseOverlay.getMenuB())){
            pauseOverlay.getMenuB().setMousePressed(true);
        }

        else if (isIn(e, pauseOverlay.getReplyB())){
            pauseOverlay.getReplyB().setMousePressed(true);
        }

        else if (isIn(e, pauseOverlay.getUnpausedB())){
            pauseOverlay.getUnpausedB().setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {

        if (isIn(e, pauseOverlay.getMusicButton())){
            if (pauseOverlay.getMusicButton().isMousePressed()){
                pauseOverlay.getMusicButton().setMuted(!pauseOverlay.getMusicButton().isMuted());
            }
        }

        else if (isIn(e, pauseOverlay.getSfxButton())){
            if (pauseOverlay.getSfxButton().isMousePressed()){
                pauseOverlay.getSfxButton().setMuted(!pauseOverlay.getSfxButton().isMuted());
            }
        }

        else if (isIn(e, pauseOverlay.getMenuB())){
            if (pauseOverlay.getMenuB().isMousePressed()){
                MusicManager.getInstance().resetPlayingSong();
                MusicManager.getInstance().stopPlayingSong();
                MusicManager.getInstance().playMenuSong();
                Gamestate.state = Gamestate.MENU;
                playingModel.unpauseGame();
            }
        }

        else if (isIn(e, pauseOverlay.getReplyB())){
            if (pauseOverlay.getReplyB().isMousePressed()){
                //Gamestate.state = Gamestate.PLAYING;
                System.out.println("reply!!");
            }
        }

        else if (isIn(e, pauseOverlay.getUnpausedB())){
            if (pauseOverlay.getUnpausedB().isMousePressed()){
                MusicManager.getInstance().playPlayingSong();
                playingModel.unpauseGame();
            }
        }

        pauseOverlay.getMusicButton().reset();
        pauseOverlay.getSfxButton().reset();
        pauseOverlay.getMenuB().resetBools();
        pauseOverlay.getReplyB().resetBools();
        pauseOverlay.getUnpausedB().resetBools();

    }

    public void mouseMoved(MouseEvent e) {
        pauseOverlay.getMusicButton().setMouseOver(false);
        pauseOverlay.getSfxButton().setMouseOver(false);
        pauseOverlay.getMenuB().setMouseOver(false);
        pauseOverlay.getReplyB().setMouseOver(false);
        pauseOverlay.getUnpausedB().setMouseOver(false);

        if (isIn(e, pauseOverlay.getMusicButton())){
            pauseOverlay.getMusicButton().setMouseOver(true);
        }

        else if (isIn(e, pauseOverlay.getSfxButton())){
            pauseOverlay.getSfxButton().setMouseOver(true);
        }

        else if (isIn(e, pauseOverlay.getMenuB())){
            pauseOverlay.getMenuB().setMouseOver(true);
        }

        else if (isIn(e, pauseOverlay.getReplyB())){
            pauseOverlay.getReplyB().setMouseOver(true);
        }

        else if (isIn(e, pauseOverlay.getUnpausedB())){;
            pauseOverlay.getUnpausedB().setMouseOver(true);
        }
    }
    private boolean isIn(MouseEvent e, PauseButton button){
        return (button.getBounds().contains(e.getX(), e.getY()));
    }
}