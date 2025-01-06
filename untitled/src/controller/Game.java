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

/**
 * The Game class implements the Runnable interface to manage the game loop and game state.
 */
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

    /**
     * Constructs a Game object and initializes the game components.
     */
    public Game() {
        playingController = new PlayingController(pauseOverlayController);
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        playingView.startPauseOverlay(pauseOverlay);
        playingView.startPlayingController(playingController);
        startGameLoop();

        // observer observable pattern
        pointManager.addObserver(musicManager);
    }

    /**
     * Starts the game loop in a new thread.
     */
    private void startGameLoop() {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Updates the game state based on the current game state.
     */
    public void update() {
        switch (Gamestate.state) {
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

    /**
     * Draws the game components based on the current game state.
     *
     * @param g the Graphics object to draw on
     */
    public void draw(Graphics g) {
        switch (Gamestate.state) {
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

    /**
     * The main game loop that updates and repaints the game at a fixed rate.
     */
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

            if (deltaU >= 1) {
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

    /**
     * Handles the event when the game window loses focus.
     */
    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
        }
    }

    /**
     * Returns the PlayingController associated with the game.
     *
     * @return the PlayingController
     */
    public PlayingController getPlayerController() {
        return playingController;
    }
}