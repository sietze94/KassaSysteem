package KassaSysteem;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Controller {

    static public void playSound(String filename){
        Thread thread = new Thread(new Runnable(){
            public void run(){
                try{
                    String soundName = "/Users/sietzemin/IdeaProjects/fxtest2/src/media.io_beep.wav";
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
//                    clip.drain();
//                    clip.close();
                    Thread.sleep(2000);
                } catch(IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    } // end of play sound method
}
