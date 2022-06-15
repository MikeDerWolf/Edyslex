package org.edyslex.utils;

import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer {

    private static Clip clip = null;
    private static AudioInputStream audioInputStream = null;

    public static void loadAudio(String filePath) throws Exception
    {
        if(clip != null){
            clip.stop();
            clip.flush();
        }

        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public static void play(String filePath)
    {
        try{
            loadAudio(filePath);
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void stop()
    {
        if(clip == null){
            return;
        }
        clip.stop();
        clip.flush();
    }

}
