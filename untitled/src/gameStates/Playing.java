package gameStates;

import cards.CardsManager;
import main.Game;
import main.GamePanel;
import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {

    protected static Game game;
    private static Playing instance;
    private CardsManager cardsManager = CardsManager.getInstace();
    private Menu menu;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;


    private Playing() {
        initClasses();
    }

    public static Playing getInstance(){
        if(instance == null){
            instance = new Playing();
        }
        return instance;
    }

    private void initClasses(){
        menu = Menu.getInstance();
        pauseOverlay = new PauseOverlay(this);
    }

    public void update(){
        if (!paused){
            cardsManager.update();
            pauseOverlay.update();
        }

        else {
            pauseOverlay.update();
        }

    }

    public void draw(Graphics g){
        cardsManager.draw(g);

        if (paused)
            pauseOverlay.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused)
            pauseOverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused)
            pauseOverlay.mouseReleased(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused)
            pauseOverlay.mouseMoved(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void hitButtonPressed(GamePanel gamePanel){
       cardsManager.hitButtonPressed(gamePanel);
    }

    public void stayButtonPressed(){
        cardsManager.stayButtonPressed();
    }

    public void unpauseGame(){
        paused = false;
    }

}
