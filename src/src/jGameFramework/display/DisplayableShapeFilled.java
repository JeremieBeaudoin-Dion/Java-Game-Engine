package jGameFramework.display;

import jGameFramework.physicalObjects.BoundingArea;

import java.awt.*;

/**
 * A displayable shape which will be filled with a desired color.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class DisplayableShapeFilled extends DisplayableShape {

    /**
     * Constructors
     */
    public DisplayableShapeFilled(Depth depth, Shape shape, Paint paint) {
        super(depth, shape, paint);
    }

    public DisplayableShapeFilled(Depth depth, BoundingArea area, Paint paint) {
        super(depth, area.getArea(), paint);
    }

}
