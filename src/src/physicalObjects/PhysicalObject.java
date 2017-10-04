package physicalObjects;

import coreActions.ActionEvent;
import images.Dimension;
import images.ImageObject;
import images.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * A physical object has an ImageObject representation of itself
 * that can be put on screen.
 *
 * Note: this class has a natural ordering that is inconsistent with equals.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class PhysicalObject implements Comparable<PhysicalObject> {

    private ObjectProperties properties;

    /**
     * Constructor
     */
    public PhysicalObject(ObjectProperties properties) {
        this.properties = properties;
    }

    /**
     * Returns the image representation of this physical object
     *
     * The ImageObject will have a relative position depending
     * on the position of the Camera.
     */
    public abstract TreeSet<ImageObject> getImageObjects(Position cameraPosition);

    public abstract void doClick() throws NoSuchMethodException;

    public abstract List<ActionEvent> getAction();

    public abstract boolean dispose();

    /**
     * PhysicalObjects are compared according to
     * their position on the screen
     */
    @Override
    public int compareTo(PhysicalObject other) {
        return properties.getDimension().getPosition().compareTo(other.getProperties().getDimension().getPosition());
    }

    /**
     * Places the PhysicalObject at the desiredPosition
     */
    public void moveTo(Position newPosition) {
        this.properties.getDimension().setPosition(newPosition);
    }

    public boolean isColliding(Position position) {
        Dimension dimensionOfPoint = new Dimension(position, 1, 1);

        return properties.getDimension().collidesWith(dimensionOfPoint);
    }

    public boolean isColliding(PhysicalObject other) {
        return properties.getDimension().collidesWith(other.getProperties().getDimension());
    }

    public boolean isColliding(Dimension dimension){
        return properties.getDimension().collidesWith(dimension);
    }

    /**
     * Getter
     */
    public ObjectProperties getProperties() {
        return properties;
    }

    /**
     * Useful method to get the value of the current position according
     * to the camera's. It is mostly useful when creating the TreeSet of
     * the ImageObjects of this object.
     */
    protected Position getPositionAccordingToCamera(Position cameraPosition) {

        return getProperties().getDimension().getPosition().add(
                new Position(-cameraPosition.getX(), -cameraPosition.getY()));
    }

    protected Dimension getObjectDimensionAtPosition(Position position){
        return new Dimension(position.clone(),
                getProperties().getDimension().getWidth(), getProperties().getDimension().getHeight());
    }

}
