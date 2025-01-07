package view.music;

import model.PointManager;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The MusicManager class is responsible for managing and playing various background music tracks in the game.
 * It implements the Observer interface to react to changes in the game state.
 */
public class MusicManager implements Observer {

    private static MusicManager instance;
    private Clip playingSong;
    private Clip menuSong;
    private Clip winningSong;
    private Clip losingSong;
    private boolean endSoundPlayed = false;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private MusicManager() {
    }

    /**
     * Returns the singleton instance of the MusicManager class.
     * If the instance is null, it creates a new instance.
     *
     * @return the singleton instance of MusicManager
     */
    public static MusicManager getInstance(){
        if(instance == null){
            instance = new MusicManager();
        }
        return instance;
    }

    /**
     * Plays the background music for the playing state.
     * If the music is not already loaded, it loads the music file and starts playing it in a loop.
     */
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

    /**
     * Plays the background music for the menu state.
     * If the music is not already loaded, it loads the music file and starts playing it in a loop.
     */
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

    /**
     * Plays the losing music.
     * If the music is not already loaded, it loads the music file and starts playing it.
     */
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

    /**
     * Plays the winning music.
     * If the music is not already loaded, it loads the music file and starts playing it.
     */
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

    /**
     * Stops the playing song.
     */
    public void stopPlayingSong(){
        playingSong.stop();
    }

    /**
     * Stops the menu song.
     */
    public void stopMenuSong(){
        menuSong.stop();
    }

    /**
     * Resets the playing song to the beginning.
     */
    public void resetPlayingSong(){
        playingSong.setFramePosition(0);
    }

    /**
     * Resets the menu song to the beginning.
     */
    public void resetMenuSong(){
        menuSong.setFramePosition(0);
    }

    /**
     * Updates the sound effects based on the game state.
     * If the player wins, it plays the winning song, otherwise it plays the losing song.
     *
     * @param o the observable object
     * @param arg an argument passed to the notifyObservers method
     */
    @Override
    public void update(Observable o, Object arg) {
        if (endSoundPlayed) {
            return;
        }

        endSoundPlayed = true;

        PointManager pointManager = (PointManager) o;
        if (pointManager.didPlayerWin())
            winningSong();
        else
            losingSong();
    }
}