package addResourceLoaderHere;

import jGameFramework.core.Loader;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Loads music in order to be played.
 *
 * Music is different from Sound because take up less memory
 * but the clips are longer to load.
 *
 * This class is necessary for the JGame Framework to work.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class MusicLoader implements Loader<Map<String, AudioInputStream>> {

    private AudioInputStream music;

    public MusicLoader() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //loadAllSounds();
    }

    private void loadAllSounds() throws IOException, UnsupportedAudioFileException {
        music = AudioSystem.getAudioInputStream(new File("Music.wav"));
    }

    public Map<String, AudioInputStream> get(GameThreadID gameThreadID){
        return null;
    }

}
