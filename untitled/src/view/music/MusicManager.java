package view.music;

import model.PointManager;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

public class MusicManager implements Observer {

    private static MusicManager instance;
    private Clip playingSong;
    private Clip menuSong;
    private Clip winningSong;
    private Clip losingSong;

    private MusicManager() {
    }

    public static MusicManager getInstance(){
        if(instance == null){
            instance = new MusicManager();
        }
        return instance;
    }

    public void playPlayingSong(){
        if(playingSong == null){
            try {
                InputStream in = new BufferedInputStream(new FileInputStream("res/music/Luigis_Casino.wav"));
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
                playingSong = AudioSystem.getClip();
                playingSong.open(audioIn);
                playingSong.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
        }
        playingSong.start();
    }

    public void playMenuSong() {
        if (menuSong == null) {
            try {
                InputStream in = new BufferedInputStream(new FileInputStream("res/music/UndertaleHotel_ost.wav"));
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
                menuSong = AudioSystem.getClip();
                menuSong.open(audioIn);
                menuSong.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
        }
        menuSong.start();
    }

    private void losingSong() {
        if (losingSong == null) {
            try {
                InputStream in = new BufferedInputStream(new FileInputStream("res/music/lose.wav"));
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
                losingSong = AudioSystem.getClip();
                losingSong.open(audioIn);
                losingSong.start();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
        }
        losingSong.start();
    }

    private void winningSong() {
        if (winningSong == null) {
            try {
                InputStream in = new BufferedInputStream(new FileInputStream("res/music/win.wav"));
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
                winningSong = AudioSystem.getClip();
                winningSong.open(audioIn);
                winningSong.start();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
        }
        winningSong.start();
    }

    public void stopPlayingSong(){
        playingSong.stop();
    }

    public void stopMenuSong(){
        menuSong.stop();
    }

    public void resetPlayingSong(){
        playingSong.setFramePosition(0);
    }

    public void resetMenuSong(){
        menuSong.setFramePosition(0);
    }

    @Override
    public void update(Observable o, Object arg) {
        PointManager pointManager = (PointManager) o;
        if (pointManager.isPlayerWins())
            winningSong();
        else
            losingSong();
    }
}