package jGameFramework.core.threadObjects;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.collections.SetHelper;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.*;

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

    private int numberOfFramesSinceExistence;

    ObjectHandler(TreeSet<PhysicalObject> allGameObjects, Camera camera) {
        allCurrentActions = new LinkedList<>();

        this.allGameObjects = allGameObjects;
        numberOfFramesSinceExistence = 0;

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
                if (object instanceof PhysicalObjectUI || camera.isShownByCamera(object)) {
                    SetHelper.addAllIfNotNull(imagesToShowOnScreen, object.getImageObjects(camera.getPosition(), imageLoader));
                }
            }
        }

        return imagesToShowOnScreen;
    }

    /**
     * When a click is made with the mouse, a call to doClick will be sent to the first
     * object which collides with that position.
     *
     * @param mousePositionOnScreen: the position of the mouse relative to the screen
     */
    public void doLeftMousePressed(Position mousePositionOnScreen) {

        Position mousePositionInGame = getMousePositionAccordingToCamera(mousePositionOnScreen);

        for (PhysicalObject object : getClickableObjects()) {

            if (object.isColliding(mousePositionInGame)) {
                MouseInteractingPhysicalObject clickable = (MouseInteractingPhysicalObject) object;

                clickable.doLeftMousePressed(mousePositionInGame);
                break;
            }

        }

    }

    public void doLeftMouseReleased(Position mousePositionOnScreen){

        Position mousePositionInGame = getMousePositionAccordingToCamera(mousePositionOnScreen);

        for (PhysicalObject object : getClickableObjects()) {

            if (object.isColliding(mousePositionInGame)) {
                MouseInteractingPhysicalObject clickable = (MouseInteractingPhysicalObject) object;

                clickable.doLeftMouseReleased(mousePositionInGame);
                break;
            }

        }
    }

    public void doRightMousePressed(Position mousePositionOnScreen) {

        Position mousePositionInGame = getMousePositionAccordingToCamera(mousePositionOnScreen);

        for (PhysicalObject object : getClickableObjects()) {

            if (object.isColliding(mousePositionInGame)) {
                MouseInteractingPhysicalObject clickable = (MouseInteractingPhysicalObject) object;

                clickable.doRightMousePressed(mousePositionInGame);
                break;
            }

        }

    }

    public void doRightMouseReleased(Position mousePositionOnScreen){

        Position mousePositionInGame = getMousePositionAccordingToCamera(mousePositionOnScreen);

        for (PhysicalObject object : getClickableObjects()) {

            if (object.isColliding(mousePositionInGame)) {
                MouseInteractingPhysicalObject clickable = (MouseInteractingPhysicalObject) object;

                clickable.doRightMouseReleased(mousePositionInGame);
                break;
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

    public void doWheelUp(PlaneDimension dimension){

        Position mousePositionInGame = getMousePositionAccordingToCamera(dimension.getPosition());

        for (PhysicalObject object : getWheelObjects()) {

            if (object.isColliding(mousePositionInGame)) {
                MouseWheelInteractingPhysicalObject wheelObject = (MouseWheelInteractingPhysicalObject) object;

                wheelObject.doWheelUp(mousePositionInGame, dimension.getHeight());
                break;
            }

        }
    }

    public void doWheelDown(PlaneDimension dimension){

        Position mousePositionInGame = getMousePositionAccordingToCamera(dimension.getPosition());

        for (PhysicalObject object : getWheelObjects()) {

            if (object.isColliding(mousePositionInGame)) {
                MouseWheelInteractingPhysicalObject wheelObject = (MouseWheelInteractingPhysicalObject) object;

                wheelObject.doWheelDown(mousePositionInGame, dimension.getHeight());
                break;
            }

        }
    }

    private TreeSet<PhysicalObject> getWheelObjects(){
        TreeSet<PhysicalObject> wheelObjects = new TreeSet<>();

        for (PhysicalObject object : allGameObjects) {

            if (object instanceof MouseWheelInteractingPhysicalObject){
                wheelObjects.add(object);
            }

        }

        return wheelObjects;
    }

    void resize(Position lastScreenSize, Position newScreenSize) {
        for (PhysicalObject object : allGameObjects) {
            object.resize(lastScreenSize, newScreenSize);
        }

        if (camera != null) {
            camera.resize(lastScreenSize, newScreenSize);
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
     *
     * @param deltaValue : How fast the current FPS is in comparison with the desired value
     */
    void update(double deltaValue) {
        updateAllObjects(deltaValue);
        deleteDisposableObjects();

        if (numberOfFramesSinceExistence < Integer.MAX_VALUE) {
            numberOfFramesSinceExistence++;
        }
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

        TreeSet<PhysicalObject> objectsInSight = new TreeSet<>();

        if (mainObject.isAllVision()) {
            objectsInSight.addAll(allGameObjects);
            return objectsInSight;
        }

        if (mainObject.isNoneVision()) {
            return objectsInSight;
        }

        // TODO: Optimize this. Should use head and tail of treeset.
        for (PhysicalObject object : allGameObjects) {
            if (object != mainObject && !(object instanceof PhysicalObjectUI) && mainObject.hasVision(object)) {
                objectsInSight.add(object);
            }
        }

        return objectsInSight;
    }

    /**
     * If necessary, returns the GameEvent that should be done
     */
    private List<GameEvent> getActionEventFromObject(PhysicalObject object) {
        if (numberOfFramesSinceExistence < GameInformation.NUMBER_OF_FRAMES_BEFORE_FIRST_EVENT_IN_THREAD) {
            return new LinkedList<>();
        }

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
