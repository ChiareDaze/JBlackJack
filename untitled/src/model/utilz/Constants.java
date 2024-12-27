package model.utilz;

public class Constants {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    public static class UI{
        public static class Buttons{
            public static final int B_WIDTH = 140;
            public static final int B_HEIGHT= 56;
        }
    }

    public static class PauseButtons{
        public static final int SOUND_SIZE = 42;
    }

    public static class URMButtons{
        public static final int URM_WIDTH = 56;
    }

    public enum Turns{
        PLAYER, DEALER, BOT1, BOT2, BOT3, NONE;

    }
}
