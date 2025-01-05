package model.profiles;

import java.io.*;

public class Profile implements Serializable {

    private String name;
    private int numberOfWins;

    public Profile(String name) {
        this.name = name;
        this.numberOfWins = 0;
    }

    public void write(String filePath){
        File outputFile = new File("res" + File.separator + "profiles" + File.separator + filePath);

        try {
            FileOutputStream fileOut = new FileOutputStream(outputFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(name);
            objectOut.writeObject(numberOfWins);
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
            Profile profile = new Profile(name);
            profile.numberOfWins = numberOfWins;
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

    public String getName() {
        return name;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void increaseWins() {
        numberOfWins++;
    }
}