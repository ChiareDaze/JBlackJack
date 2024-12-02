package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Load {

    public static final String MENU_BUTTONS = "button_atlas.png";

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
            return "/cards/" + value + "-" + type + ".png";
        }

        public static String GetBackCardPath(){
            return "/cards/BACK.png";
        }

        public enum CardType{
            H, D, C, S
        }

    }

}
