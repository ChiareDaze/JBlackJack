package model.profiles;

import java.io.File;
import java.util.ArrayList;

/**
 * The ProfilesManager class manages user profiles in the game.
 * It handles adding, saving, reading, and selecting profiles.
 */
public class ProfilesManager {

    private static ProfilesManager instance;
    private ArrayList<Profile> profiles = new ArrayList<>();
    private int currentProfileIndex = 0;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Reads the profiles from the file system.
     */
    private ProfilesManager() {
        readProfile();
    }

    /**
     * Returns the singleton instance of the ProfilesManager class.
     * If the instance is null, it creates a new instance.
     *
     * @return the singleton instance of ProfilesManager
     */
    public static ProfilesManager getInstance() {
        if (instance == null) {
            instance = new ProfilesManager();
        }
        return instance;
    }

    /**
     * Adds a profile to the list of profiles if it does not already exist.
     * Saves the profiles to the file system.
     *
     * @param profile the profile to add
     */
    public void addProfile(Profile profile) {
        if (!profiles.contains(profile)) {
            profiles.add(profile);
            saveProfiles();
        }
    }

    /**
     * Saves all profiles to the file system.
     */
    private void saveProfiles() {
        for (Profile profile : profiles) {
            profile.write(profile.getName() + ".profile");
        }
    }

    /**
     * Returns the current profile.
     *
     * @return the current profile
     */
    public Profile getCurrentProfile() {
        return profiles.get(currentProfileIndex);
    }

    /**
     * Selects a profile by name and sets it as the current profile.
     *
     * @param profileName the name of the profile to select
     */
    public void selectProfile(String profileName) {
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getName().equals(profileName)) {
                currentProfileIndex = i;
                break;
            }
        }
    }

    /**
     * Reads profiles from the file system and adds them to the list of profiles.
     * If no profiles are found, creates default profiles.
     */
    public void readProfile(){
        File profilesFolder = new File("res" + File.separator + "profiles");
        File[] files = profilesFolder.listFiles();
        for (File file : files) {
            Profile profile = Profile.read(file.getPath());
            if (profile != null) {
                profiles.add(profile);
            }
        }
        if (profiles.isEmpty()) {
            defaultProfile();
        }
    }

    /**
     * Increases the number of games for the current profile and saves the profiles.
     */
    public void increaseGames() {
        getCurrentProfile().increaseGames();
        saveProfiles();
    }

    /**
     * Increases the number of wins for the current profile and saves the profiles.
     */
    public void increaseWins() {
        getCurrentProfile().increaseWins();
        saveProfiles();
    }

    /**
     * Creates default profiles and adds them to the list of profiles.
     */
    public void defaultProfile(){
        addProfile(new Profile("Shy Guy",0));
        addProfile(new Profile("King Dedede",1));
        addProfile(new Profile("Yoshi",2));
    }

    /**
     * Advances to the next profile in the list.
     * If the end of the list is reached, wraps around to the first profile.
     */
    public void nextProfile() {
        currentProfileIndex++;
        if (currentProfileIndex >= profiles.size()) {
            currentProfileIndex = 0;
        }
    }

    /**
     * Moves to the previous profile in the list.
     * If the beginning of the list is reached, wraps around to the last profile.
     */
    public void previousProfile() {
        currentProfileIndex--;
        if (currentProfileIndex < 0) {
            currentProfileIndex = profiles.size() - 1;
        }
    }

    /**
     * Checks if a profile with the specified name exists.
     *
     * @param profileName the name of the profile to check
     * @return true if the profile exists, false otherwise
     */
    public boolean doesProfileExist(String profileName) {
        for (Profile profile : profiles) {
            if (profile.getName().equals(profileName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the list of profiles.
     *
     * @return the list of profiles
     */
    public ArrayList<Profile> getProfiles() {
        return profiles;
    }
}