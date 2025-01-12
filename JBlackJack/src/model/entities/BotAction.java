package model.entities;

/**
 * The BotAction interface defines the actions that a bot can perform in the game.
 */
public interface BotAction {

    /**
     * Performs the bot's turn, including any actions the bot should take.
     */
    void turn();
}
