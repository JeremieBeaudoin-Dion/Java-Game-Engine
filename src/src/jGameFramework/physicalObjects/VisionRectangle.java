package jGameFramework.physicalObjects;

import java.io.Serializable;

/**
 * A type of vision which works as a rectangle
 *
 * The Position send to the constructor should represent
 * the rectangle that surrounds the object.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class VisionRectangle implements Vision, Serializable {

    private Position widthAndHeight;

    /**
     * Constructor with the full width and height of the rectangle
     * that represents the vision
     */
    public VisionRectangle(int width, int height) {
        widthAndHeight = new Position(width, height);
    }

    /**
     * Returns true if the baseObject can see the other according
     * to this vision's dimensions
     */
    public boolean isInSight(Position centerOfObject, PhysicalObject other) {
        BoundingArea area = BoundingArea.getRectangleAreaFromCenterPosition(centerOfObject, widthAndHeight.getX(),
                widthAndHeight.getY());

        return area.collidesWith(other.getBoundingArea());
    }

}
