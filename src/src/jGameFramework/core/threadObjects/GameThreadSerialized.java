package jGameFramework.core.threadObjects;

import addResourceLoaderHere.ActionLoader;
import addResourceLoaderHere.GameThreadID;
import addResourceLoaderHere.MusicLoader;
import addResourceLoaderHere.SoundLoader;
import jGameFramework.physicalObjects.Camera;
import jGameFramework.physicalObjects.PhysicalObject;

import java.io.Serializable;
import java.util.TreeSet;

/**
 * A serialized version of a GameThread
 *
 * @author Jérémie Beaudoin-Dion
 */
class GameThreadSerialized implements Serializable {

    private GameThreadID id;
    private TreeSet<PhysicalObject> allGameObjects;
    private Camera camera;

    GameThreadSerialized(GameThreadID id, TreeSet<PhysicalObject> allGameObjects, Camera camera) {
        this.id = id;
        this.allGameObjects = allGameObjects;
        this.camera = camera;
    }

    GameThread getThread(GameThreadHandler gameThreadHandler, ActionLoader actionLoader, MusicLoader musicLoader,
                         SoundLoader soundLoader){
        return new GameThread(gameThreadHandler, id, camera, allGameObjects, actionLoader.get(id), musicLoader.get(id),
                soundLoader.get(id));
    }

}
