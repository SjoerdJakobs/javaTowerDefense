package OOFramework.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static OOFramework.Modules.CONSTANTS.STANDARD_SOUND_FILENAME_PREFIX;

public class SoundPlayer
{

    private static final List<Clip> clipList = new CopyOnWriteArrayList<>();

    public static synchronized void Play(final String fileName)
    {
        Play(fileName,1);
    }

    public static synchronized void Play(final String fileName, final float volume) {
        new Thread(() -> {
            try {
                Clip clip = GetClip(fileName);
                SetVolume(clip, volume);
                clip.start();
            } catch (Exception e) {
                System.out.println("play sound error: " + e.getMessage() + " for "+ STANDARD_SOUND_FILENAME_PREFIX + fileName);
            }
        }).start();
    }

    public static synchronized void Loop(final String fileName)
    {
        Loop(fileName,1);
    }

    public static synchronized void Loop(final String fileName, final float volume)
    {
        new Thread(() -> {
            try {
                Clip clip = GetClip(fileName);
                SetVolume(clip, volume);
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                clipList.add(clip);
            } catch (Exception e) {
                System.out.println("play sound error: " + e.getMessage() + " for "+STANDARD_SOUND_FILENAME_PREFIX+ fileName);
            }
        }).start();
    }

    private static Clip GetClip(String fileName) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
/*
        Clip clip = AudioSystem.getClip();
        URL url = SoundPlayer.class.getClassLoader().getResource("sounds/"+fileName);
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
        clip.open(inputStream);
        return clip;
        */

        
        Clip clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(SoundPlayer.class.getClassLoader().getResourceAsStream(STANDARD_SOUND_FILENAME_PREFIX + fileName));
        clip.open(inputStream);
        if(clip != null) {
            return clip;
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        //if you place the files next to where the jar will be this will work.                //
        //until the other methods work to get sound from a jar file this will have to do sadly//
        ////////////////////////////////////////////////////////////////////////////////////////
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./"+fileName));
        Clip jarClip = AudioSystem.getClip();
        jarClip.open(audioInputStream);
        return jarClip;

    }

    private static void SetVolume(Clip clip, float volume) {
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = control.getMaximum() - control.getMinimum();
        float gain = (range * volume) + control.getMinimum();
        control.setValue(gain);
    }

    public static synchronized void Stop() {
        for (Clip clip : clipList) {
            clip.stop();
        }
        clipList.clear();
    }
}
