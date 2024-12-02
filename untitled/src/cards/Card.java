package cards;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Load.Images.*;
import static utilz.Load.ImportImg;

public class Card{
    private String value;
    private CardType type;
    private boolean isHidden;
    private BufferedImage backImage;
    private BufferedImage frontImage;
    int cardWidth = 110; //ratio should 1/1.4
    int cardHeight = 154;
    int timer = 0;

    public Card(String value, CardType type) {
        this.value = value;
        this.type = type;

        loadAnimations();
    }


    public void update(){

        timer++;
        if (timer == 60){
            isHidden = !isHidden;
            timer = 0;
        }
    }

    public void draw(Graphics g, int pos, int y){
        if (isHidden){
            g.drawImage(backImage, cardWidth + 25 + (cardWidth + 5) * pos, y, cardWidth, cardHeight, null);
        } else {
            g.drawImage(frontImage, cardWidth + 25 + (cardWidth + 5) * pos, y, cardWidth, cardHeight,null);
        }
    }

    private void loadAnimations(){
        backImage = ImportImg(GetBackCardPath());
        frontImage = ImportImg(GetFrontCardPath(value, type));
    }

    public boolean isAce(){
        return value.equals("A");
    }


    public CardType getType() {
        return type;
    }

    public int getValue(){
        if ("AJQK".contains(value)) { //A, J, Q, K
            if (value == "A")
                return 11;
            return 10;
        }
        return Integer.parseInt(value); //2-10
    }
}
