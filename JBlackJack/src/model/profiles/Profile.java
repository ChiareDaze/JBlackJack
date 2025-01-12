package model.profiles;

import java.io.*;

/**
 * The Profile class represents a user profile in the game, which includes the user's name, number of wins, number of games, avatar image index, and level.
 */
public class Profile implements Serializable {

    private String name;
    private int numberOfWins;
    private int numberOfGames;
    private int avatarImgIndex;
    private int level;

    /**
     * Constructs a Profile object with the specified name and avatar image index.
     *
     * @param name the name of the user
     * @param avatarImgIndex the index of the avatar image
     */
    public Profile(String name, int avatarImgIndex) {
        this.name = name;
        this.numberOfWins = 0;
        this.numberOfGames = 0;
        this.avatarImgIndex = avatarImgIndex;
        this.level = 0;
    }

    /**
     * Writes the profile data to a file at the specified file path.
     *
     * @param filePath the path of the file to write the profile data to
     */
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

    /**
     * Reads the profile data from a file at the specified file path and returns a Profile object.
     *
     * @param filePath the path of the file to read the profile data from
     * @return the Profile object read from the file, or null if an error occurs
     */
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

    /**
     * Checks if this profile is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the object is a Profile with the same name, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            return name.equals(profile.getName());
        }
        return false;
    }

    /**
     * Returns a string representation of the profile.
     *
     * @return the name of the profile
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Updates the level of the profile based on the number of wins.
     * Each level update requires 5 wins.
     */
    public void updateLevel() {
        level = numberOfWins / 5;
    }

    /**
     * Returns the level of the profile.
     *
     * @return the level of the profile
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the name of the profile.
     *
     * @return the name of the profile
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of wins of the profile.
     *
     * @return the number of wins of the profile
     */
    public int getNumberOfWins() {
        return numberOfWins;
    }

    /**
     * Increases the number of wins by one and updates the level.
     */
    public void increaseWins() {
        numberOfWins++;
        updateLevel();
    }

    /**
     * Returns the number of games of the profile.
     *
     * @return the number of games of the profile
     */
    public int getNumberOfGames() {
        return numberOfGames;
    }

    /**
     * Increases the number of games by one.
     */
    public void increaseGames() {
        numberOfGames++;
    }

    /**
     * Returns the avatar image index of the profile.
     *
     * @return the avatar image index of the profile
     */
    public int getAvatarImgIndex() {
        return avatarImgIndex;
    }

    /**
     * Sets the avatar image index of the profile.
     *
     * @param avatarImgIndex the new avatar image index
     */
    public void setAvatarImgIndex(int avatarImgIndex) {
        this.avatarImgIndex = avatarImgIndex;
    }
}