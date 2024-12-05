package model.cards;

import static model.utilz.Load.Images.*;


public class CardModel {
    private String value;
    private CardType type;
    private boolean isHidden;
    int timer = 0;

    public CardModel(String value, CardType type) {
        this.value = value;
        this.type = type;
    }


    public void update(){

        timer++;
        if (timer == 60){
            isHidden = !isHidden;
            timer = 0;
        }
    }

    public boolean isAce(){
        return value.equals("A");
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

}
