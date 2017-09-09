package core;

import images.ImageObject;
import physicalObjects.Camera;
import physicalObjects.PhysicalObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Contains all the useful objects of the game. It handles
 * the camera and returns all ImageObjects to show on screen
 * on the call of get()
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ObjectHandler {

    private Camera camera;

    private List<PhysicalObject> allGameObjects;

    public ObjectHandler() {
        camera = new Camera();
    }

    /**
     * Returns all objects to send to ImageHandler.
     */
    public List<ImageObject> get() {
        List<ImageObject> imagesToShowOnScreen = new LinkedList<>();

        for (PhysicalObject object : allGameObjects) {
            if (camera.isWithinBounds(object)) {
                imagesToShowOnScreen.addAll(object.getImageObjects());
            }
        }

        return imagesToShowOnScreen;
    }

}
