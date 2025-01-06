package view.cards;

import model.cards.CardModel;

import java.awt.*;
import java.awt.image.BufferedImage;

import static view.Load.Images.GetBackCardPath;
import static view.Load.Images.GetFrontCardPath;
import static view.Load.ImportImg;

/**
 * The CardView class represents the visual representation of a card in the game.
 * It handles loading the card images and drawing the card on the screen.
 */
public class CardView {
    private CardModel cardModel;
    private BufferedImage backImage;
    private BufferedImage frontImage;
    int cardWidth = 90;
    int cardHeight = 134;

    /**
     * Constructs a CardView object with the specified CardModel.
     *
     * @param cardModel the model of the card
     */
    public CardView(CardModel cardModel){
        this.cardModel = cardModel;
        loadAnimations();
    }

    /**
     * Draws the card on the given Graphics context at the specified coordinates.
     *
     * @param g the Graphics context to draw on
     * @param x the x-coordinate to draw the card at
     * @param y the y-coordinate to draw the card at
     */
    public void draw(Graphics g, int x, int y){
        if (cardModel.getIsHidden())
            g.drawImage(backImage, x, y, cardWidth, cardHeight, null);
        else
            g.drawImage(frontImage, x, y, cardWidth, cardHeight, null);
    }

    /**
     * Loads the images for the front and back of the card.
     */
    private void loadAnimations(){
        backImage = ImportImg(GetBackCardPath());
        frontImage = ImportImg(GetFrontCardPath(cardModel.getValue(), cardModel.getType()));
    }
}