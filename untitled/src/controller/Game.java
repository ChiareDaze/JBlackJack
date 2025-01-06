package controller;

import model.PointManager;
import model.gameStates.Gamestate;
import model.gameStates.PlayingModel;
import view.GamePanel;
import view.GameWindow;
import view.gamestates.MenuView;
import view.gamestates.PlayingView;
import view.music.MusicManager;
import view.ui.PauseOverlay;

import java.awt.*;

public class Game implements Runnable {

    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private Thread thread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private final PlayingModel playingModel = PlayingModel.getInstance();
    private final PauseOverlay pauseOverlay = new PauseOverlay();
    private final PauseOverlayController pauseOverlayController = new PauseOverlayController();
    private PlayingController playingController;
    private final PlayingView playingView = PlayingView.getInstance();
    private MenuView menuView = MenuView.getInstance();
    private final MusicManager musicManager = MusicManager.getInstance();
    private final PointManager pointManager = PointManager.getInstance();

    public Game() {
        playingController = new PlayingController(pauseOverlayController);
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        playingView.startPauseOverlay(pauseOverlay);
        playingView.startPlayingController(playingController);
        startGameLoop();

        //observer observable pattern
        pointManager.addObserver(musicManager);
    }

    private void startGameLoop() {
        thread = new Thread(this);
        thread.start();
    }

    public void update(){
        switch (Gamestate.state){
            case MENU:
                menuView.update();
                break;
            case PLAYING:
                playingModel.update();
                playingView.update();
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g){
        switch (Gamestate.state){
            case MENU:
                menuView.draw(g);
                break;
            case PLAYING:
                playingView.draw(g);
                break;
            case OPTIONS:
                break;
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | " + updates);
                frames = 0;
                updates = 0;

            }
        }
    }

    public void windowFocusLost(){
        if (Gamestate.state == Gamestate.PLAYING){
        }
    }

    public PlayingController getPlayerController() {
        return playingController;
    }
}