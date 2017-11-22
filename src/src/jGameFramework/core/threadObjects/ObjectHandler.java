package jGameFramework.core.threadObjects;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.*;

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

    private List<GameEvent> allCurrentActions;

    ObjectHandler(TreeSet<PhysicalObject> allGameObjects, Camera camera) {
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
    TreeSet<Displayable> get(ImageLoader imageLoader) {
        TreeSet<Displayable> imagesToShowOnScreen = new TreeSet<>();

        if (camera != null){
            for (PhysicalObject object : allGameObjects) {
                if (camera.isWithinBounds(object)) {
                    addAllIfNotNull(imagesToShowOnScreen, object.getImageObjects(camera.getPosition(), imageLoader));
                }
            }
        }

        return imagesToShowOnScreen;
    }

    private void addAllIfNotNull(TreeSet<Displayable> desiredSet, TreeSet<Displayable> setToAdd){
        if (setToAdd != null){
            desiredSet.addAll(setToAdd);
        }
    }

    /**
     * When a click is made with the mouse, a call to doClick will be sent to
     * any object that collides with that position
     *
     * @param mousePositionOnScreen: the position of the mouse relative to the screen
     */
    public void doLeftMousePressed(Position mousePositionOnScreen) {

        Position mousePositionInGame = getMousePositionAccordingToCamera(mousePositionOnScreen);

        for (PhysicalObject object : getClickableObjects()) {

            if (object.isColliding(mousePositionInGame)) {
                MouseInteractingPhysicalObject clickable = (MouseInteractingPhysicalObject) object;

                clickable.doLeftMousePressed(mousePositionInGame);
            }

        }

    }

    public void doLeftMouseReleased(Position mousePositionOnScreen){

        Position mousePositionInGame = getMousePositionAccordingToCamera(mousePositionOnScreen);

        for (PhysicalObject object : getClickableObjects()) {

            if (object.isColliding(mousePositionInGame)) {
                MouseInteractingPhysicalObject clickable = (MouseInteractingPhysicalObject) object;

                clickable.doLeftMouseReleased(mousePositionInGame);
            }

        }
    }

    public void doRightMousePressed(Position mousePositionOnScreen) {

        Position mousePositionInGame = getMousePositionAccordingToCamera(mousePositionOnScreen);

        for (PhysicalObject object : getClickableObjects()) {

            if (object.isColliding(mousePositionInGame)) {
                MouseInteractingPhysicalObject clickable = (MouseInteractingPhysicalObject) object;

                clickable.doRightMousePressed(mousePositionInGame);
            }

        }

    }

    public void doRightMouseReleased(Position mousePositionOnScreen){

        Position mousePositionInGame = getMousePositionAccordingToCamera(mousePositionOnScreen);

        for (PhysicalObject object : getClickableObjects()) {

            if (object.isColliding(mousePositionInGame)) {
                MouseInteractingPhysicalObject clickable = (MouseInteractingPhysicalObject) object;

                clickable.doRightMouseReleased(mousePositionInGame);
            }

        }
    }

    private TreeSet<PhysicalObject> getClickableObjects(){
        TreeSet<PhysicalObject> clickables = new TreeSet<>();

        for (PhysicalObject object : allGameObjects) {

            if (object instanceof MouseInteractingPhysicalObject){
                clickables.add(object);
            }

        }

        return clickables;
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
     *
     * @param deltaValue : How fast the current FPS is in comparison with the desired value
     */
    void update(double deltaValue) {
        updateAllObjects(deltaValue);
        deleteDisposableObjects();
    }

    private void deleteDisposableObjects() {
        allGameObjects.removeIf(PhysicalObject::dispose);
    }

    private void updateAllObjects(double deltaValue) {
        TreeSet<PhysicalObject> objectsToAdd = new TreeSet<>();
        allCurrentActions = new LinkedList<>();

        for (PhysicalObject currentObject : allGameObjects) {
            objectsToAdd.addAll(updateObjectAndGetObjectsToAdd(currentObject, deltaValue));
            allCurrentActions.addAll(getActionEventFromObject(currentObject));
        }

        allGameObjects.addAll(objectsToAdd);
    }

    /**
     * Update the current object.
     *
     * Return any new object if they should be added
     */
    private TreeSet<PhysicalObject> updateObjectAndGetObjectsToAdd(PhysicalObject currentObject, double deltaValue) {
        TreeSet<PhysicalObject> objectsToAdd = new TreeSet<>();
        TreeSet<PhysicalObject> newObjects;

        if (currentObject instanceof PhysicalObjectUpdating) {
            ((PhysicalObjectUpdating) currentObject).updateTimeValue(deltaValue);
            newObjects = ((PhysicalObjectUpdating) currentObject).update(getSurroundingObjects((PhysicalObjectUpdating) currentObject));

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

        surroundingObjects.addAll(getAllObjectsInSight(object));

        return surroundingObjects;
    }

    private TreeSet<PhysicalObject> getAllObjectsInSight(PhysicalObjectUpdating mainObject) {

        TreeSet<PhysicalObject> objetsInSight = new TreeSet<>();

        for (PhysicalObject object : allGameObjects) {
            if (mainObject.hasVision(object) && object != mainObject) {
                objetsInSight.add(object);
            }
        }

        return objetsInSight;
    }

    /**
     * These methods might be more efficient in finding the surrounding objects
     * of an UpdatingObject, but they are hard to read and sometimes buggy.
     *
     * They have been kept for further implementations, to see if the bug
     * can be fixed and if they are, indeed, faster when there is a lot of
     * different objects.
     */
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
     * If necessary, returns the GameEvent that should be done
     */
    private List<GameEvent> getActionEventFromObject(PhysicalObject object) {
        List<GameEvent> actionsToDo = new LinkedList<>();
        List<GameEvent> actionsFromObject;

        actionsFromObject = object.getAction();

        if (actionsFromObject != null && !actionsFromObject.isEmpty()) {
            actionsToDo.addAll(actionsFromObject);
        }

        return actionsToDo;
    }

    /**
     * Getter
     */
    Object getObject(Class objectsClass){
        for (PhysicalObject object : allGameObjects){
            if (object.getClass() == objectsClass){
                return object;
            }
        }

        throw new IllegalArgumentException("There are no such class: " + objectsClass + " in ObjectHandler.");
    }

    public Camera getCamera(){
        return camera;
    }

    List<GameEvent> getAllCurrentActions() {
        return allCurrentActions;
    }

    TreeSet<PhysicalObject> getAllObjectsForSave(){
        return allGameObjects;
    }

    private Position getMousePositionAccordingToCamera(Position position){
        return position.add(camera.getPosition());
    }

}
