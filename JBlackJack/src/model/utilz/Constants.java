package model.utilz;

/**
 * The Constants class holds various constant values used throughout the game.
 */
public class Constants {

    /**
     * The width of the game window.
     */
    public static final int WIDTH = 1000;

    /**
     * The height of the game window.
     */
    public static final int HEIGHT = 700;

    /**
     * The UI class contains constants related to the user interface.
     */
    public static class UI {

        /**
         * The Buttons class contains constants related to button dimensions.
         */
        public static class Buttons {

            /**
             * The width of the buttons.
             */
            public static final int B_WIDTH = 140;

            /**
             * The height of the buttons.
             */
            public static final int B_HEIGHT = 56;
        }
    }

    /**
     * The CardType enum represents the different types of cards.
     */
    public enum CardType {
        H, D, C, S
    }

    /**
     * The PauseButtons class contains constants related to pause button dimensions.
     */
    public static class PauseButtons {

        /**
         * The size of the sound button.
         */
        public static final int SOUND_SIZE = 42;
    }

    /**
     * The URMButtons class contains constants related to URM button dimensions.
     */
    public static class URMButtons {

        /**
         * The width of the URM buttons.
         */
        public static final int URM_WIDTH = 56;
    }

    /**
     * The Turns enum represents the different turns in the game.
     */
    public enum Turns {
        PLAYER, DEALER, BOT1, BOT2, BOT3, FINISHED
    }

    /**
     * The EntityNames enum represents the different entity names in the game.
     */
    public enum EntityNames {
        PLAYER, DEALER, BOT1, BOT2, BOT3
    }
}