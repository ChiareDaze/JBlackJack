package view;

import model.utilz.Constants.CardType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * The Load class provides methods to load images and paths for various game assets.
 */
public class Load {

    public static final String MENU_BUTTONS = "/buttonsStuff/button_atlas.png";
    public static final String MENU_BACKGROUND = "/buttonsStuff/menu_background.png";
    public static final String PAUSE_BACKGROUND = "/buttonsStuff/pause_menu.png";
    public static final String SOUND_BUTTONS = "/buttonsStuff/sound_button.png";
    public static final String URM_BUTTONS = "/buttonsStuff/urm_buttons.png";
    public static final String BOT_BUTTONS = "/buttonsStuff/bot_button.png";
    public static final String BOT_COUNT = "/buttonsStuff/bot_count.png";
    public static final String PLAYER_BUTTONS = "/buttonsStuff/player_actions.png";
    public static final String END_BUTTONS = "/buttonsStuff/game_finished.png";
    public static final String ARROW_BUTTONS = "/buttonsStuff/arrow_buttons.png";
    public static final String SELECT_PROFILE_BUTTONS = "/buttonsStuff/select_add_buttons.png";
    public static final String AVATAR_0 = "/avatars/0.jpg";
    public static final String AVATAR_1 = "/avatars/1.jpg";
    public static final String AVATAR_2 = "/avatars/2.jpg";

    /**
     * Imports an image from the specified path.
     *
     * @param path the path to the image file
     * @return the loaded BufferedImage
     */
    public static BufferedImage ImportImg(String path) {
        BufferedImage img = null;
        InputStream is = Images.class.getResourceAsStream(path);

        try {
            img = ImageIO.read(is);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * The Images class provides methods to get paths for card and bot images.
     */
    public static class Images {
        /**
         * Returns the path to the front image of a card.
         *
         * @param value the value of the card
         * @param type the type of the card
         * @return the path to the front image of the card
         */
        public static String GetFrontCardPath(String value, CardType type) {
            return "/cardsImgs/" + value + "-" + type + ".png";
        }

        /**
         * Returns the path to the back image of a card.
         *
         * @return the path to the back image of the card
         */
        public static String GetBackCardPath() {
            return "/cardsImgs/BACK.png";
        }

        /**
         * Returns the path to the bot number image.
         *
         * @return the path to the bot number image
         */
        public static String GetNumberBotPath() {
            return "/buttonsStuff/bot_number.png";
        }
    }
}