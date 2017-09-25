package physicalObjects;

import core.Game;
import images.Dimension;
import images.ImageObject;
import images.Position;

import java.util.List;

/**
 * The Camera has a position, and the Width and Height of the Game
 *
 * It defines what will be shown in ImageHandler
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Camera extends PhysicalObject {

    /**
     * Basic constructor
     */
    public Camera() {
        super(new ObjectProperties(new Dimension(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT),
                new Velocity(5, 5)));
    }

    /**
     * Returns true if the PhysicalObject is within the
     * Camera's vision.
     *
     * If a zoom is implemented, a new method will be needed
     */
    public boolean isWithinBounds(PhysicalObject physicalObject) {
        return isColliding(physicalObject);
    }

    // Invoked methods
    public void moveRight() {
        properties.getDimension().setPosition(
                properties.getDimension().getPosition().add(properties.getVelocity().goRight()));
    }

    public void moveLeft() {
        properties.getDimension().setPosition(
                properties.getDimension().getPosition().add(properties.getVelocity().goLeft()));
    }

    public void moveDown() {
        properties.getDimension().setPosition(
                properties.getDimension().getPosition().add(properties.getVelocity().goDown()));
    }

    public void moveUp() {
        properties.getDimension().setPosition(
                properties.getDimension().getPosition().add(properties.getVelocity().goUp()));
    }

    /**
     * The camera doesn't have an Image representation
     */
    @Override
    public List<ImageObject> getImageObjects(Position cameraPosition) {
        return null;
    }

}
