package model.utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Load {

    public static final String MENU_BUTTONS = "/buttonsStuff/button_atlas.png";
    public static final String  MENU_BACKGROUND = "/buttonsStuff/menu_background.png";
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

    public static class Images{
        public static String GetFrontCardPath(String value, CardType type){
            return "/cardsImgs/" + value + "-" + type + ".png";
        }

        public static String GetBackCardPath(){
            return "/cardsImgs/BACK.png";
        }

        public enum CardType{
            H, D, C, S
        }

        public static String GetNumberBotPath(){
            return "/buttonsStuff/bot_number.png";
        }
    }

}