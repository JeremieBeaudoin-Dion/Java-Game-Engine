package core;

import addImageLoaderHere.ImageLoader;
import coreActions.InputActionKeyMap;
import display.Displayable;
import physicalObjects.Camera;
import physicalObjects.PhysicalObject;

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

    public GameThread(Camera camera, TreeSet<PhysicalObject> threadObjects,
                      InputActionKeyMap inputActionKeyMap) {
        this.objectHandler = new ObjectHandler(threadObjects, camera);
        this.actionHandler = new ActionHandler(objectHandler);
        this.inputHandler = new InputHandler(inputActionKeyMap, actionHandler);
    }

    void update(Game gameReference) {
        actionHandler.update(gameReference);
        objectHandler.update();
    }

    TreeSet<Displayable> getImages(ImageLoader imageLoader) {
        return objectHandler.get(imageLoader);
    }

    InputHandler getInputHandler() {
        return inputHandler;
    }
}
