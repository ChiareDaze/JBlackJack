package gameStates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import main.Game;
import utilz.Constants;


public class Menu extends State implements StateMethods{

    private static Menu instance;

    private Menu() {
        super();
    }

    public static Menu getInstance(){
        if(instance == null){
            instance = new Menu();
        }
        return instance;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);

        String text1 = "MENU";
        FontMetrics metrics1 = g.getFontMetrics(g.getFont());
        int x1 = (Constants.WIDTH - metrics1.stringWidth(text1)) / 2;
        int y1 = 200;

        g.drawString(text1, x1, y1);

        String text2 = "Press Enter to Play";
        FontMetrics metrics2 = g.getFontMetrics(g.getFont());
        int x2 = (Constants.WIDTH - metrics2.stringWidth(text2)) / 2;
        int y2 = 300;

        g.drawString(text2, x2, y2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            Gamestate.state = Gamestate.PLAYING;
            System.out.println("Playing");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
