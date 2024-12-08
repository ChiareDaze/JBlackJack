package model;

import model.cards.CardModel;
import java.util.List;

public interface Strategy {

    public void hit(List<CardModel> hand, int handValue);
}
