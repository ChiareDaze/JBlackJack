package model.gameStates;

/**
 * The Gamestate enum represents the different states of the game.
 */
public enum Gamestate {

    PLAYING, // The state when the game is being played
    MENU,    // The state when the game is in the menu
    OPTIONS, // The state when the game is in the options menu
    QUIT;    // The state when the game is quitting

    /**
     * The current state of the game, initially set to MENU.
     */
    public static Gamestate state = MENU;
}