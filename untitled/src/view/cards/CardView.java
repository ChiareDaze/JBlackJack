package view.cards;

import model.cards.CardModel;

import java.awt.*;
import java.awt.image.BufferedImage;

import static view.Load.Images.GetBackCardPath;
import static view.Load.Images.GetFrontCardPath;
import static view.Load.ImportImg;

public class CardView {
    private CardModel cardModel;
    private BufferedImage backImage;
    private BufferedImage frontImage;
    int cardWidth = 90; //ratio should 1/1.4
    int cardHeight = 134;

    public CardView(CardModel cardModel){
        this.cardModel = cardModel;

        loadAnimations();
    }

    public void draw(Graphics g, int x, int y){
        if (cardModel.getIsHidden())
            g.drawImage(backImage, x, y, cardWidth, cardHeight, null);
        else
            g.drawImage(frontImage, x, y, cardWidth, cardHeight,null);
    }

    private void loadAnimations(){
        backImage = ImportImg(GetBackCardPath());
        frontImage = ImportImg(GetFrontCardPath(cardModel.getValue(), cardModel.getType()));
   }
}