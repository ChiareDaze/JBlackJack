package gameStates;

import model.cards.CardsManagerModel;
import main.Game;

public class PlayingModel extends State {

    protected static Game game;
    private static PlayingModel instance;
    private CardsManagerModel cardsManagerModel = CardsManagerModel.getInstace();
    private MenuModel menuModel;

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
        menuModel = MenuModel.getInstance();
    }

    public void update(){
        if (!paused){
            cardsManagerModel.update();
        }
    }

    public void unpauseGame(){
        paused = false;
    }

    public boolean getPause(){
        return paused;
    }

    public void setPaused(boolean paused){
        this.paused = paused;
    }
}
