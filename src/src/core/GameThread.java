package core;

import addImageLoaderHere.ImageLoader;
import coreActions.InputActionKeyMap;
import display.Displayable;
import physicalObjects.Camera;
import physicalObjects.PhysicalObject;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.TreeSet;

/**
 * A Game can have multiple threads which consists of an ActionHandler
 * and an ObjectHandler
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameThread {

    private ObjectHandler objectHandler;
    private ActionHandler actionHandler;
    private InputHandler inputHandler;
    private String id;

    public GameThread(String id, Camera camera, TreeSet<PhysicalObject> threadObjects,
                      InputActionKeyMap inputActionKeyMap) {
        this.objectHandler = new ObjectHandler(threadObjects, camera);
        this.actionHandler = new ActionHandler(objectHandler);
        this.inputHandler = new InputHandler(inputActionKeyMap, actionHandler);
        this.id = id;
    }

    void update(Game gameReference) {
        actionHandler.update(gameReference);
        objectHandler.update();
    }

    GameThreadSerialized getSerial(){
        return new GameThreadSerialized(id, objectHandler.getAllObjectsForSave(), objectHandler.getCamera());
    }

    TreeSet<Displayable> getImages(ImageLoader imageLoader) {
        return objectHandler.get(imageLoader);
    }

    InputHandler getInputHandler() {
        return inputHandler;
    }

}
