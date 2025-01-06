package model.cards;

import model.utilz.Constants.CardType;

public class CardModel {
    private String value;
    private CardType type;
    private boolean isHidden = false;

    public CardModel(String value, CardType type) {
        this.value = value;
        this.type = type;
    }

    public boolean isAce(){
        return value.equals("A");
    }

    public boolean isFigure(){
        return "JQK".contains(value);
    }

    public CardType getType() {
        return type;
    }

    public int getNumericalValue(){
        if ("AJQK".contains(value)) { //A, J, Q, K
            if (value == "A")
                return 11;
            return 10;
        }
        return Integer.parseInt(value); //2-10
    }

    public String getValue() {
        return value;
    }

    public boolean getIsHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    @Override
    public String toString() {
        return value + "-" + type;
    }
}
