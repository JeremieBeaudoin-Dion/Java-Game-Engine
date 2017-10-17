package core;

import physicalObjects.Camera;
import physicalObjects.PhysicalObject;

import java.io.Serializable;
import java.util.TreeSet;

/**
 * A serialized version of a GameThread
 *
 * @author Jérémie Beaudoin-Dion
 */
class GameThreadSerialized implements Serializable {

    private String id;
    private TreeSet<PhysicalObject> allGameObjects;
    private Camera camera;

    GameThreadSerialized(String id, TreeSet<PhysicalObject> allGameObjects, Camera camera) {
        this.id = id;
        this.allGameObjects = allGameObjects;
        this.camera = camera;
    }

    GameThread getThread(ThreadManager threadManager){
        return new GameThread(id, camera, allGameObjects,
                threadManager.getThread(id).getInputHandler().getInputActionKeyMap());
    }

}
