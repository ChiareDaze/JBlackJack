package model.cards;

import model.utilz.Constants.CardType;

/**
 * The CardModel class represents a card in a deck with a value, type, and hidden status.
 */
public class CardModel {
    private String value;
    private CardType type;
    private boolean isHidden = false;

    /**
     * Constructs a CardModel object with the specified value and type.
     *
     * @param value the value of the card (e.g., "A", "2", "J")
     * @param type the type of the card (e.g., HEARTS, SPADES)
     */
    public CardModel(String value, CardType type) {
        this.value = value;
        this.type = type;
    }

    /**
     * Checks if the card is an Ace.
     *
     * @return true if the card is an Ace, false otherwise
     */
    public boolean isAce(){
        return value.equals("A");
    }

    /**
     * Checks if the card is a figure card (Jack, Queen, or King).
     *
     * @return true if the card is a figure card, false otherwise
     */
    public boolean isFigure(){
        return "JQK".contains(value);
    }

    /**
     * Gets the type of the card.
     *
     * @return the type of the card
     */
    public CardType getType() {
        return type;
    }

    /**
     * Gets the numerical value of the card.
     *
     * @return the numerical value of the card
     */
    public int getNumericalValue(){
        if ("AJQK".contains(value)) { //A, J, Q, K
            if (value.equals("A"))
                return 11;
            return 10;
        }
        return Integer.parseInt(value); //2-10
    }

    /**
     * Gets the value of the card.
     *
     * @return the value of the card
     */
    public String getValue() {
        return value;
    }

    /**
     * Checks if the card is hidden.
     *
     * @return true if the card is hidden, false otherwise
     */
    public boolean getIsHidden() {
        return isHidden;
    }

    /**
     * Sets the hidden status of the card.
     *
     * @param hidden the hidden status to set
     */
    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    /**
     * Returns a string representation of the card.
     *
     * @return a string representation of the card in the format "value-type"
     */
    @Override
    public String toString() {
        return value + "-" + type;
    }
}
