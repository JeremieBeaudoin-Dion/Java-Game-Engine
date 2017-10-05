package core;

import coreActions.ActionEvent;
import images.ImageObject;
import images.Position;
import physicalObjects.Camera;
import physicalObjects.PhysicalObjectUpdating;
import physicalObjects.PhysicalObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Contains all the useful objects of the game. It handles
 * the camera and returns all ImageObjects to show on screen
 * on the call of get()
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ObjectHandler {

    private Camera camera;

    private TreeSet<PhysicalObject> allGameObjects;

    private List<ActionEvent> allCurrentActions;

    public ObjectHandler(TreeSet<PhysicalObject> allGameObjects, Camera camera) {
        allCurrentActions = new LinkedList<>();

        this.allGameObjects = allGameObjects;

        this.camera = camera;
        if (camera != null){
            allGameObjects.add(camera); // ensures the camera is updated
        }
    }

    /**
     * Returns all objects to send to ImageHandler.
     */
    TreeSet<ImageObject> get() {
        TreeSet<ImageObject> imagesToShowOnScreen = new TreeSet<>();

        if (camera != null){
            for (PhysicalObject object : allGameObjects) {
                if (camera.isWithinBounds(object)) {
                    imagesToShowOnScreen.addAll(object.getImageObjects(camera.getDimension().getPosition()));
                }
            }
        }

        return imagesToShowOnScreen;
    }

    /**
     * When a click is made with the mouse, a call to doClick will be sent to
     * any object that collides with that position
     *
     * @param mousePositionOnScreen: the position of the mouse relative to the screen
     */
    public void doClick(Position mousePositionOnScreen) throws NoSuchMethodException {

        Position mousePositionInGame = mousePositionOnScreen.add(camera.getDimension().getPosition());

        for (PhysicalObject object : allGameObjects) {

            if (object.isColliding(mousePositionInGame)) {
                object.doClick();
            }
        }

    }

    /**
     * Called every frame
     *
     * Gives every PhysicalObject access to other objects in its vision.
     *
     * In order to be efficient and show objects in the right order,
     * a TreeSet is used.
     *
     * All possible new game objects are returned in PhysicalObjectUpdating.update()
     * and are added at the end of the ObjectHandler's update() loop.
     */
    void update() throws NoSuchMethodException {
        updateAllObjects();
        deleteDisposableObjects();
    }

    private void deleteDisposableObjects() throws NoSuchMethodException {
        allGameObjects.removeIf(PhysicalObject::dispose);
    }

    private void updateAllObjects() {
        TreeSet<PhysicalObject> objectsToAdd = new TreeSet<>();
        allCurrentActions = new LinkedList<>();

        for (PhysicalObject currentObject : allGameObjects) {
            objectsToAdd.addAll(updateObjectAndGetObjectsToAdd(currentObject));
            allCurrentActions.addAll(getActionEventFromObject(currentObject));
        }

        allGameObjects.addAll(objectsToAdd);
    }

    /**
     * Update the current object.
     *
     * Return any new object if they should be added
     */
    private TreeSet<PhysicalObject> updateObjectAndGetObjectsToAdd(PhysicalObject currentObject) {
        TreeSet<PhysicalObject> objectsToAdd = new TreeSet<>();
        TreeSet<PhysicalObject> newObjects;

        if (currentObject instanceof PhysicalObjectUpdating) {
            newObjects = ((PhysicalObjectUpdating) currentObject).update(
                    getSurroundingObjects((PhysicalObjectUpdating) currentObject));

            if (newObjects != null && !newObjects.isEmpty()) {
                objectsToAdd.addAll(newObjects);
            }
        }

        return objectsToAdd;
    }

    /**
     * Returns a TreeSet of all objects that are within a specific PhysicalObject's
     * surroundings, according to its vision.
     */
    private TreeSet<PhysicalObject> getSurroundingObjects(PhysicalObjectUpdating object) {

        TreeSet<PhysicalObject> surroundingObjects = new TreeSet<>();

        surroundingObjects.addAll(getAllPreviousObjectsInSight(object));

        surroundingObjects.addAll(getAllFollowingObjectsInSight(object));

        return surroundingObjects;
    }

    private TreeSet<PhysicalObject> getAllPreviousObjectsInSight(PhysicalObjectUpdating mainObject) {
        TreeSet<PhysicalObject> previousObjects = new TreeSet<>();

        Iterator<PhysicalObject> previousIterator = allGameObjects.headSet(mainObject, false).descendingIterator();
        PhysicalObject iteratedObject;
        boolean iterate = true;

        while (previousIterator.hasNext() && iterate) {
            iteratedObject = previousIterator.next();

            if (mainObject.hasVision(iteratedObject)) {
                previousObjects.add(iteratedObject);
            } else {
                iterate = false;
            }
        }

        return previousObjects;
    }

    private TreeSet<PhysicalObject> getAllFollowingObjectsInSight(PhysicalObjectUpdating mainObject) {
        TreeSet<PhysicalObject> nextObjects = new TreeSet<>();

        Iterator<PhysicalObject> nextIterator = allGameObjects.tailSet(mainObject, false).iterator();
        PhysicalObject iteratedObject;
        boolean iterate = true;

        while (nextIterator.hasNext() && iterate) {
            iteratedObject = nextIterator.next();

            if (mainObject.hasVision(iteratedObject)) {
                nextObjects.add(iteratedObject);
            } else {
                iterate = false;
            }
        }

        return nextObjects;
    }

    /**
     * If necessary, returns the ActionEvent that should be done
     */
    private List<ActionEvent> getActionEventFromObject(PhysicalObject object) {
        List<ActionEvent> actionsToDo = new LinkedList<>();
        List<ActionEvent> actionsFromObject;

        actionsFromObject = object.getAction();

        if (actionsFromObject != null && !actionsFromObject.isEmpty()) {
            actionsToDo.addAll(actionsFromObject);
        }

        return actionsToDo;
    }

    /**
     * Getter
     */
    public Object getObject(Class objectsClass){
        for (PhysicalObject object : allGameObjects){
            if (object.getClass() == objectsClass){
                return object;
            }
        }

        throw new IllegalArgumentException("There are no such class: " + objectsClass + "in ObjectHandler.");
    }

    public Camera getCamera(){
        return camera;
    }

    List<ActionEvent> getAllCurrentActions() {
        return allCurrentActions;
    }

}
