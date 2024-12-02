package gameStates;

import cards.CardsManager;
import main.Game;
import main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {

    protected static Game game;
    private static Playing instance;
    private CardsManager cardsManager = CardsManager.getInstace();
    private Menu menu;
   private boolean paused;


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
    }

    public void update(){
        cardsManager.update();
    }

    public void draw(Graphics g){
        cardsManager.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

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

}
