package model.profiles;

import java.io.*;

public class Profile implements Serializable {

    private String name;
    private int numberOfWins;
    private int numberOfGames;
    private int avatarImgIndex;
    private int level;

    public Profile(String name, int avatarImgIndex) {
        this.name = name;
        this.numberOfWins = 0;
        this.numberOfGames = 0;
        this.avatarImgIndex = avatarImgIndex;
        this.level = 0;
    }

    public void write(String filePath){
        File outputFile = new File("res" + File.separator + "profiles" + File.separator + filePath);

        try {
            FileOutputStream fileOut = new FileOutputStream(outputFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(name);
            objectOut.writeObject(numberOfWins);
            objectOut.writeObject(numberOfGames);
            objectOut.writeObject(avatarImgIndex);
            objectOut.writeObject(level);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Profile read(String filePath){

        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            String name = (String) objectIn.readObject();
            int numberOfWins = (int) objectIn.readObject();
            int numberOfGames = (int) objectIn.readObject();
            int avatarImgIndex = (int) objectIn.readObject();
            int level = (int) objectIn.readObject();
            Profile profile = new Profile(name, avatarImgIndex);
            profile.numberOfWins = numberOfWins;
            profile.numberOfGames = numberOfGames;
            profile.avatarImgIndex = avatarImgIndex;
            profile.level = level;
            objectIn.close();
            return profile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            return name.equals(profile.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    public void updateLevel() {
        level = numberOfWins / 5;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void increaseWins() {
        numberOfWins++;
        updateLevel();
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void increaseGames() {
        numberOfGames++;
    }

    public int getAvatarImgIndex() {
        return avatarImgIndex;
    }

    public void setAvatarImgIndex(int avatarImgIndex) {
        this.avatarImgIndex = avatarImgIndex;
    }
}