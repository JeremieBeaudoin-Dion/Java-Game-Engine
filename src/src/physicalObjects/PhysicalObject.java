package physicalObjects;

import images.Dimension;
import images.ImageObject;
import images.Position;

import java.util.List;

/**
 * A physical object has an ImageObject representation of itself
 * that can be put on screen.
 *
 * Note: this class has a natural ordering that is inconsistent with equals.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class PhysicalObject implements Comparable<PhysicalObject> {

    protected ObjectProperties properties;

    /**
     * Constructor
     */
    public PhysicalObject(ObjectProperties properties) {
        this.properties = properties;
    }

    /**
     * Returns the image representation of this physical object
     */
    public abstract List<ImageObject> getImageObjects(Position cameraPosition);

    @Override
    public int compareTo(PhysicalObject other) {
        if (properties.getDimension().getPosition().getY() != other.getProperties().getDimension().getPosition().getY()) {
            return other.getProperties().getDimension().getPosition().getY() -
                    properties.getDimension().getPosition().getY();
        } else {
            return other.getProperties().getDimension().getPosition().getX() -
                    properties.getDimension().getPosition().getX();
        }
    }

    /**
     * Returns true if this physical object collides with a Position
     */
    public boolean isColliding(Position position) {
        Dimension dimensionOfPoint = new Dimension(position, 1, 1);

        return getEastCollision(properties.getDimension(), dimensionOfPoint) &&
                getWestCollision(properties.getDimension(), dimensionOfPoint) &&
                getSouthCollision(properties.getDimension(), dimensionOfPoint) &&
                getNorthCollision(properties.getDimension(), dimensionOfPoint);
    }

    /**
     * Returns true if this physical object is colliding with another
     */
    public boolean isColliding(PhysicalObject other) {
        return getEastCollision(properties.getDimension(), other.getProperties().getDimension()) &&
                getWestCollision(properties.getDimension(), other.getProperties().getDimension()) &&
                getSouthCollision(properties.getDimension(), other.getProperties().getDimension()) &&
                getNorthCollision(properties.getDimension(), other.getProperties().getDimension());
    }

    private boolean getEastCollision(Dimension dimensionOfFirst, Dimension dimensionOfSecond) {
        return dimensionOfFirst.getPosition().getX() + dimensionOfFirst.getWidth()
                >= dimensionOfSecond.getPosition().getX();
    }

    private boolean getWestCollision(Dimension dimensionOfFirst, Dimension dimensionOfSecond) {
        return dimensionOfFirst.getPosition().getX()
                <= dimensionOfSecond.getPosition().getX() + dimensionOfSecond.getWidth();
    }

    private boolean getSouthCollision(Dimension dimensionOfFirst, Dimension dimensionOfSecond) {
        return dimensionOfFirst.getPosition().getY() + dimensionOfFirst.getHeight()
                >= dimensionOfSecond.getPosition().getY();
    }

    private boolean getNorthCollision(Dimension dimensionOfFirst, Dimension dimensionOfSecond) {
        return dimensionOfFirst.getPosition().getY()
                <= dimensionOfSecond.getPosition().getY() + dimensionOfSecond.getHeight();
    }

    /**
     * Example of a generic method doClick()
     * Defining what happens when a physicalObject gets "clicked" by the mouse.
     */
    public void doClick() {

    }

    /**
     * Getter
     */
    public ObjectProperties getProperties() {
        return properties;
    }

}
