package view;

import model.utilz.Constants.CardType;

public class Utilz {

    public static int GetCardSpriteIndex(String value, CardType type) {
        int index = 0;
        switch (value) {
            case "A":
                index = 0;
                break;
            case "2":
                index = 1;
                break;
            case "3":
                index = 2;
                break;
            case "4":
                index = 3;
                break;
            case "5":
                index = 4;
                break;
            case "6":
                index = 5;
                break;
            case "7":
                index = 6;
                break;
            case "8":
                index = 7;
                break;
            case "9":
                index = 8;
                break;
            case "10":
                index = 9;
                break;
            case "J":
                index = 10;
                break;
            case "Q":
                index = 11;
                break;
            case "K":
                index = 12;
                break;
        }

        switch (type) {
            case C:
                index += 13;
                break;
            case D:
                index += 26;
                break;
            case H:
                index += 39;
                break;
        }

        return index;
    }

    public static String GetCardValue(int index) {
        String value = "";
        if (index < 13) {
            switch (index) {
                case 0:
                    value = "A";
                    break;
                case 1:
                    value = "2";
                    break;
                case 2:
                    value = "3";
                    break;
                case 3:
                    value = "4";
                    break;
                case 4:
                    value = "5";
                    break;
                case 5:
                    value = "6";
                    break;
                case 6:
                    value = "7";
                    break;
                case 7:
                    value = "8";
                    break;
                case 8:
                    value = "9";
                    break;
                case 9:
                    value = "10";
                    break;
                case 10:
                    value = "J";
                    break;
                case 11:
                    value = "Q";
                    break;
                case 12:
                    value = "K";
                    break;
            }
        } else if (index < 26) {
            switch (index) {
                case 13:
                    value = "A";
                    break;
                case 14:
                    value = "2";
                    break;
                case 15:
                    value = "3";
                    break;
                case 16:
                    value = "4";
                    break;
                case 17:
                    value = "5";
                    break;
                case 18:
                    value = "6";
                    break;
                case 19:
                    value = "7";
                    break;
                case 20:
                    value = "8";
                    break;
                case 21:
                    value = "9";
                    break;
                case 22:
                    value = "10";
                    break;
                case 23:
                    value = "J";
                    break;
                case 24:
                    value = "Q";
                    break;
                case 25:
                    value = "K";
                    break;
            }
        } else if (index < 39) {
            switch (index) {
                case 26:
                    value = "A";
                    break;
                case 27:
                    value = "2";
                    break;
                case 28:
                    value = "3";
                    break;
                case 29:
                    value = "4";
                    break;
                case 30:
                    value = "5";
                    break;
                case 31:
                    value = "6";
                    break;
            }
        }
        return value;
    }

    public static CardType GetCardType(int index) {
        CardType type = CardType.S;
        if (index < 13) {
            type = CardType.S;
        } else if (index < 26) {
            type = CardType.C;
        } else if (index < 39) {
            type = CardType.D;
        } else if (index < 52) {
            type = CardType.H;
        }
        return type;
    }
}
