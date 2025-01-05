package view.ui;

import model.profiles.Profile;
import model.profiles.ProfilesManager;
import model.utilz.Constants;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ScoreBoard {

    private static ScoreBoard instance;
    private ArrayList <Profile> scores;

    private ScoreBoard(){

    }

    public static ScoreBoard getInstance(){
        if (instance == null){
            instance = new ScoreBoard();
        }
        return instance;
    }

    public void draw(Graphics g) {

        drawTitle(g);
        drawScores(g);
    }

    private void drawScores(Graphics g) {
        int position = 1;

        for (Profile p : scores) {
            String name = p.getName();
            String score = String.valueOf(p.getNumberOfWins());
            String txt = position + "° " + name + " numeber of wins: " + score;
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString(txt, getCenteredTextPosition(g, txt), 200 + scores.indexOf(p) * 50);
            position++;
        }

    }

    private void drawTitle(Graphics g){
        String title = "Top 5 Scores";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(title, getCenteredTextPosition(g, title), 60);
    }

    public void updateScores(){
        ArrayList <Profile> allProfiles = ProfilesManager.getInstance().getProfiles();
        scores = (ArrayList<Profile>) allProfiles.stream()
                .sorted(Comparator.comparing(Profile::getNumberOfWins).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    private static int getCenteredTextPosition(Graphics g, String text){
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (Constants.WIDTH - metrics.stringWidth(text)) / 2;
        return x;
    }
}