package view.music;

import javax.sound.sampled.*;
import java.io.*;

public class MusicManager {

    private static MusicManager instance;
    private Clip playingSong;
    private Clip menuSong;

    private MusicManager() {
        //playMenuSong();
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

}
