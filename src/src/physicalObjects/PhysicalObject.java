package physicalObjects;

import coreActions.GameEvent;
import display.Displayable;

import java.util.List;
import java.util.TreeSet;

/**
 * A physical object has a Displayable representation of itself
 * that can be put on screen.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class PhysicalObject implements Comparable<PhysicalObject> {

    private static int ORDER_OF_APPEARANCE = 0;

    private BoundingArea boundingArea;

    private boolean obstacle;

    private int appearanceNumber;

    /**
     * Constructor
     */
    public PhysicalObject(BoundingArea boundingArea, boolean isObstacle) {
        this.boundingArea = boundingArea;
        this.obstacle = isObstacle;

        appearanceNumber = ORDER_OF_APPEARANCE++;
    }

    /**
     * Returns the image representation of this physical object
     *
     * The ImageObject will have a relative position depending
     * on the position of the CameraWithEdges.
     */
    public abstract TreeSet<Displayable> getImageObjects(Position cameraPosition);

    public abstract void doClick() throws NoSuchMethodException;

    public abstract List<GameEvent> getAction();

    public abstract boolean dispose();

    /**
     * PhysicalObjects are compared according to their position on the screen.
     *
     * If those are equals, they are compared in order of appearance in the game.
     */
    @Override
    public int compareTo(PhysicalObject other) {
        if (boundingArea.getPosition().compareTo(other.getPosition()) == 0){
            return appearanceNumber - other.appearanceNumber;
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
        boundingArea.moveTo(newPosition);
    }

    public boolean isColliding(Position position) {
        return  boundingArea.collidesWith(position);
    }

    public boolean isColliding(PhysicalObject other) {
        return boundingArea.collidesWith(other.getBoundingArea());
    }

    public boolean isColliding(BoundingArea areaOther){
        return boundingArea.collidesWith(areaOther);
    }

    /**
     * If an other object can pass through this one
     */
    public boolean isObstacle(){
        return obstacle;
    }

    /**
     * Useful method to get the value of the current position according
     * to the camera's. It is mostly useful when creating the TreeSet of
     * the ImageObjects of this object.
     */
    protected Position getPositionAccordingToCamera(Position cameraPosition) {
        return boundingArea.getPosition().add(new Position(-cameraPosition.getX(), -cameraPosition.getY()));
    }

    protected BoundingArea getObjectAreaAtPosition(Position position){
        BoundingArea objectAreaAtPosition = boundingArea.clone();
        objectAreaAtPosition.moveTo(position);

        return objectAreaAtPosition;
    }

    public BoundingArea getBoundingArea(){
        return boundingArea;
    }

    public Position getPosition(){
        return boundingArea.getPosition();
    }

    @Override
    public String toString(){
        return "PhysicalObject: " + this.getClass() + " at Position: " + boundingArea.getPosition().toString();
    }

}
