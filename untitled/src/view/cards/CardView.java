package view.cards;

import model.cards.CardModel;
import model.cards.CardsManagerModel;

import java.awt.*;
import java.awt.image.BufferedImage;

import static model.utilz.Load.Images.GetBackCardPath;
import static model.utilz.Load.Images.GetFrontCardPath;
import static model.utilz.Load.ImportImg;

public class CardView {
    private CardModel cardModel;
    private BufferedImage backImage;
    private BufferedImage frontImage;
    int cardWidth = 110; //ratio should 1/1.4
    int cardHeight = 154;

    public CardView(CardModel cardModel){
        this.cardModel = cardModel;

        loadAnimations();
    }

    public void draw(Graphics g, int pos, int y){
        if (cardModel.getIsHidden()){
            g.drawImage(backImage, cardWidth + 25 + (cardWidth + 5) * pos, y, cardWidth, cardHeight, null);
        } else {
            g.drawImage(frontImage, cardWidth + 25 + (cardWidth + 5) * pos, y, cardWidth, cardHeight,null);
        }
    }

    private void loadAnimations(){
        backImage = ImportImg(GetBackCardPath());
        frontImage = ImportImg(GetFrontCardPath(cardModel.getValue(), cardModel.getType()));
   }
}