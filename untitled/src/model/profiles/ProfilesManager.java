package model.profiles;

import java.io.File;
import java.util.ArrayList;

public class ProfilesManager {

    private static ProfilesManager instance;
    private ArrayList<Profile> profiles = new ArrayList<>();
    private int currentProfileIndex = 0;

    private ProfilesManager() {
        readProfile();
    }

    public static ProfilesManager getInstance() {
        if (instance == null) {
            instance = new ProfilesManager();
        }
        return instance;
    }

    public void addProfile(Profile profile) {
        if (!profiles.contains(profile)) {
            profiles.add(profile);
            saveProfiles();
        }
    }

    private void saveProfiles() {
        for (Profile profile : profiles) {
            profile.write(profile.getName() + ".profile");
        }
    }

    public Profile getCurrentProfile() {
        return profiles.get(currentProfileIndex);
    }

    public void selectProfile(String profileName) {
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getName().equals(profileName)) {
                currentProfileIndex = i;
                break;
            }
        }
    }

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

    public void increaseWins() {
        getCurrentProfile().increaseWins();
        saveProfiles();
    }

    public void defaultProfile(){
        addProfile(new Profile("Player"));
        addProfile(new Profile("Gerard Way"));
        addProfile(new Profile("Frank Iero"));
    }

    public void nextProfile() {
        currentProfileIndex++;
        if (currentProfileIndex >= profiles.size()) {
            currentProfileIndex = 0;
        }
    }

    public void previousProfile() {
        currentProfileIndex--;
        if (currentProfileIndex < 0) {
            currentProfileIndex = profiles.size() - 1;
        }
    }

    public boolean doesProfileExist(String profileName) {
        for (Profile profile : profiles) {
            if (profile.getName().equals(profileName)) {
                return true;
            }
        }
        return false;
    }


    public ArrayList<Profile> getProfiles() {
        return profiles;
    }
}