package main;

import gameStates.Gamestate;
import gameStates.Menu;
import gameStates.Playing;
import java.awt.*;

public class Game implements Runnable {

    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private Thread thread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private final Playing playing = Playing.getInstance();
    private Menu menu = Menu.getInstance();

    public Game() {
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        thread = new Thread(this);
        thread.start();
    }

    public void update(){
        switch (Gamestate.state){
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g){
        switch (Gamestate.state){
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                //menu.draw(g);
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
}