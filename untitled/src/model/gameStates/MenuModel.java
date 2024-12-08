package model.gameStates;

public class MenuModel extends State{

    private static MenuModel instance;

    private boolean profileSelectionActive = false;
    private boolean profileRanckingActive = false;

    private MenuModel() {
        super();
    }

    public static MenuModel getInstance(){
        if(instance == null){
            instance = new MenuModel();
        }
        return instance;
    }

    public void update() {
        if (profileSelectionActive){
            //update profile selection
            return;
        }

        if (profileRanckingActive){
            //update profile ranking
            return;
        }
    }

    public boolean isProfileSelectionActive() {
        return profileSelectionActive;
    }

    public void setProfileSelectionActive(boolean profileSelectionActive) {
        this.profileSelectionActive = profileSelectionActive;
    }

    public boolean isProfileRanckingActive() {
        return profileRanckingActive;
    }

    public void setProfileRanckingActive(boolean profileRanckingActive) {
        this.profileRanckingActive = profileRanckingActive;
    }
}
