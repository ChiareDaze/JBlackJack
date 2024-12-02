package gameStates;

import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {

    public boolean isIn(MouseEvent e, MenuButton mb){
         return mb.getBounds().contains(e.getX(), e.getY());
    }
    /*
    protected Game game;

    public State(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }

     */
}
