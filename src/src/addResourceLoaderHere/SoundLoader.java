package addResourceLoaderHere;

import jGameFramework.core.Loader;

import javax.sound.sampled.AudioInputStream;
import java.util.Map;

/**
 * Loads music in order to be played.
 *
 * Sound is different from Music because take up MORE memory
 * but the clips are faster to load.
 *
 * This class is necessary for the JGame Framework to work.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class SoundLoader implements Loader<Map<String, AudioInputStream>> {

    public Map<String, AudioInputStream> get(GameThreadID gameThreadID){
        return null;
    }

}
