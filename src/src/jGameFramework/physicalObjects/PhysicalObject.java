package jGameFramework.physicalObjects;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.core.ObjectWithID;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;

import java.awt.geom.Area;
import java.io.Serializable;
import java.util.List;
import java.util.TreeSet;

/**
 * A physical object has a Displayable representation of itself
 * that can be put on screen.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class PhysicalObject extends ObjectWithID implements Serializable {

    private BoundingArea boundingArea;
    private DisplayableDepth depth;
    private boolean obstacle;

    /**
     * Constructor
     */
    public PhysicalObject(BoundingArea boundingArea, boolean isObstacle, DisplayableDepth depth) {
        this.boundingArea = boundingArea;
        this.obstacle = isObstacle;
        this.depth = depth;
    }

    /**
     * Returns the image representation of this physical object
     *
     * The ImageObject will have a relative position depending
     * on the position of the CameraWithEdges.
     */
    public abstract TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader);

    /**
     * Returns any action that the GameThread should handle
     */
    public abstract List<GameEvent> getAction();

    /**
     * Returns true if the object should be disposed of
     */
    public abstract boolean dispose();

    public void resize(Position lastScreenSize, Position newScreenSize) {
        double widthMultiplier = newScreenSize.getX() / (double) lastScreenSize.getX();
        double heightMultiplier = newScreenSize.getY() / (double) lastScreenSize.getY();

        resizeWidthAndHeight(widthMultiplier, heightMultiplier);
        resizePosition(widthMultiplier, heightMultiplier);
    }

    protected void resizeWidthAndHeight(double widthMultiplier, double heightMultiplier) {
        setWidthAndHeight(widthMultiplier * getWidth(), heightMultiplier * getHeight());
    }

    protected void resizePosition(double xMultiplier, double yMultiplier) {
        Position newPosition = new Position(getPosition().getX() * xMultiplier, getPosition().getY() * yMultiplier);

        setPositionTo(newPosition);
    }

    /**
     * PhysicalObjects are compared first according to depth, then according to their position on the screen.
     *
     * If those are equals, they are compared in order of appearance in the game.
     */
    @Override
    public int compareTo(ObjectWithID objectWithID) {
        if (!(objectWithID instanceof PhysicalObject)){
            return super.compareTo(objectWithID);
        }

        PhysicalObject other = (PhysicalObject) objectWithID;

        if (other.depth.compareTo(this.depth) != 0) {
            return other.depth.compareTo(this.depth);
        }

        if (boundingArea.getPosition().compareTo(other.getPosition()) == 0){
            return super.compareTo(objectWithID);
        }

        return boundingArea.getPosition().compareTo(other.getPosition());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof PhysicalObject && compareTo((PhysicalObject) other) == 0;
    }

    /**
     * Places the PhysicalObject at the desiredPosition
     */
    public void setPositionTo(Position newPosition) {
        boundingArea.setPosition(newPosition);
    }

    public boolean isColliding(Position position) {
        return  boundingArea.collidesWith(position);
    }

    public boolean isColliding(PhysicalObject other) {
        return boundingArea.collidesWith(other.getBoundingArea());
    }

    boolean isColliding(BoundingArea areaOther){
        return boundingArea.collidesWith(areaOther);
    }

    /**
     * Returns true if this object fully encompasses the other
     */
    public boolean isWithinBounds(PhysicalObject other) {
        return isWithinBounds(other.getBoundingArea());
    }

    protected boolean isWithinBounds(BoundingArea areaOther) {
        return this.boundingArea.isWithinBounds(areaOther);
    }

    /**
     * If an other object can pass through this one
     */
    boolean isObstacle(){
        return obstacle;
    }

    /**
     * Useful method to find the closest object from this one
     */
    public PhysicalObject getClosestObject(TreeSet<PhysicalObject> surroundings) {

        double closestDistance = Double.MAX_VALUE;
        double currentDist;
        PhysicalObject closestObj = null;

        for (PhysicalObject obj : surroundings) {
            currentDist = obj.getBoundingArea().getCenterPosition().getSquaredDistance(
                    this.getBoundingArea().getCenterPosition());

            if (currentDist < closestDistance) {
                closestDistance = currentDist;
                closestObj = obj;
            }
        }

        return closestObj;
    }

    /**
     * Getter of the base depth of this object
     */
    public DisplayableDepth getDepth() {
        return depth;
    }

    /**
     * Useful method to get the value of the current position according
     * to the camera's. It is mostly useful when creating the TreeSet of
     * the ImageObjects of this object.
     */
    public Position getPositionAccordingToCamera(Position cameraPosition) {
        return boundingArea.getPosition().add(cameraPosition.reverse());
    }

    public Area getAreaAccordingToCamera(Position cameraPosition) {
        BoundingArea copyArea = boundingArea.clone();

        copyArea.setPosition(copyArea.getPosition().add(cameraPosition.reverse()));

        return copyArea.getArea();
    }

    public BoundingArea getObjectAreaAtPosition(Position position){
        BoundingArea objectAreaAtPosition = boundingArea.clone();
        objectAreaAtPosition.setPosition(position);

        return objectAreaAtPosition;
    }

    public BoundingArea getBoundingArea(){
        return boundingArea;
    }

    public Position getPosition(){
        return boundingArea.getPosition();
    }

    protected double getWidth(){
        return boundingArea.getWidth();
    }

    protected double getHeight(){
        return boundingArea.getHeight();
    }

    protected void setWidthAndHeight(double width, double height) {
        boundingArea.setWidthAndHeight(width, height);
    }

    @Override
    public String toString(){
        return "PhysicalObject number " + getId() + ": " + this.getClass() + " at Position: " + boundingArea.getPosition().toString();
    }

}
