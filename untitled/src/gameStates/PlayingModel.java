package gameStates;

import model.cards.CardsManagerModel;
import main.Game;
import main.GamePanel;
import ui.PauseOverlay;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayingModel extends State implements StateMethods {

    protected static Game game;
    private static PlayingModel instance;
    private CardsManagerModel cardsManagerModel = CardsManagerModel.getInstace();
    private Menu menu;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;


    private PlayingModel() {
        initClasses();
    }

    public static PlayingModel getInstance(){
        if(instance == null){
            instance = new PlayingModel();
        }
        return instance;
    }

    private void initClasses(){
        menu = Menu.getInstance();
        pauseOverlay = new PauseOverlay(this);
    }

    public void update(){
        if (!paused){
            cardsManagerModel.update();
            pauseOverlay.update();
        }

        else {
            pauseOverlay.update();
        }

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
       cardsManagerModel.hitButtonPressed(gamePanel);
    }

    public void stayButtonPressed(){
        cardsManagerModel.stayButtonPressed();
    }

    public void unpauseGame(){
        paused = false;
    }

    public boolean getPause(){
        return paused;
    }

    //todo: remove when mvc is implemented

    public CardsManagerModel getCardsManager() {
        return cardsManagerModel;
    }

    public PauseOverlay getPauseOverlay() {
        return pauseOverlay;
    }
}
