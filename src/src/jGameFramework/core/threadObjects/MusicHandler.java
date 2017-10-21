package jGameFramework.core.threadObjects;

import javax.sound.sampled.*;
import java.util.HashMap;

/**
 * Plays music files.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class MusicHandler {

    private HashMap<String, Clip> musicMap;

    public MusicHandler(HashMap<String, Clip> music){

    }

    /**
     *
     *
     * @param loop how many times the clips will be played. Can be set to -1 for continuous loop.
     */
    void play(String id, int loop) {
        stop(id);


    }

    void open(String id){
        if (musicMap.containsKey(id)){
            Clip desiredClip = musicMap.get(id);
            if (!desiredClip.isOpen()){

            }

        }
    }

    void stop(String id){
        if (musicMap.containsKey(id)){
            Clip desiredClip = musicMap.get(id);
            if (desiredClip.isOpen()){


            }
        }
    }

    void stopAll() {

        for(Clip clip : musicMap.values()){
            if (clip.isRunning()){
                clip.stop();
            }
        }

    }


}

