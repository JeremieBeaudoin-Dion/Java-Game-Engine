package physicalObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * This class was made to simplify the actual Area class from JavaSE7.
 *
 * Every physical object has a BoundingArea which will help determine
 * collision and position in the game.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class BoundingArea implements Cloneable {

    private Area area;
    private boolean highPrecisionCollision;

    /**
     * Constructor with any Shape
     */
    public BoundingArea(Shape shape){
        this.area = new Area(shape);
        highPrecisionCollision = !isRectangular();
    }

    /**
     * Constructor with an Area. This is useful if
     * many shapes are involved.
     */
    public BoundingArea(Area area){
        this.area = area;
        highPrecisionCollision = !isRectangular();
    }

    /**
     * Constructor with a list of shapes. Useful when the width, height
     * and position of the BoundingArea are not known.
     *
     * Takes a list of shapes as argument and creates the area from those
     * shapes.
     */
    public BoundingArea(List<Shape> shapes){
        this.area = getAreaFromShapes(shapes);
        highPrecisionCollision = !isRectangular();
    }

    /**
     * Useful to get the area of many shapes
     */
    public static Area getAreaFromShapes(List<Shape> shapes){
        Area desiredArea = new Area();

        for (Shape shape: shapes){
            desiredArea.add(new Area(shape));
        }

        return desiredArea;
    }

    /**
     * Creates a new RectangleBoundingArea according to a position which
     * represent the center of the Area and a width and height
     */
    public static BoundingArea getRectangleAreaFromCenterPosition(Position centerPosition, int width, int height){
        Position actualPosition = centerPosition.add(new Position(-width/2, -height/2));

        return new BoundingArea(new Rectangle2D.Double(centerPosition.getX(), centerPosition.getY(), width, height));
    }

    /**
     * Moves the area without changing the shape
     */
    public void moveTo(Position newPosition){
        area.transform(AffineTransform.getTranslateInstance(newPosition.getX(), newPosition.getY()));
    }

    // Collisions
    public boolean collidesWith(Position position){
        return area.contains(position.getX(), position.getY());
    }

    public boolean collidesWith(BoundingArea other){
        if (highPrecisionCollision){
            return collidesHighPrecision(other.area);
        } else {
            return collidesLowPrecision(other.area);
        }
    }

    /**
     * Mostly useful for rectangles colliding
     */
    private boolean collidesLowPrecision(Area other){
        return area.intersects(other.getBounds2D());
    }

    /**
     * Helps detect collisions with ellipses and irregular shapes with high
     * precision.
     */
    private boolean collidesHighPrecision(Area other){
        Area areaA = new Area(area);
        areaA.intersect(new Area(other));
        return !areaA.isEmpty();
    }

    // Getters
    /**
     * This was made to give access to all the methods of the Area from
     * Java. Other methods were added as well to simplify the code and
     * ensure (for example) that the area would not changed undesirably.
     */
    public Area getArea(){
        return area;
    }

    public int getX(){
        return area.getBounds().x;
    }

    public int getY(){
        return area.getBounds().y;
    }

    public Position getCenterPosition(){
        return new Position((int) area.getBounds().getCenterX(), (int) area.getBounds().getCenterY());
    }

    public Position getPosition(){
        return new Position(getX(), getY());
    }

    public boolean isRectangular(){
        return area.isRectangular();
    }

    public BoundingArea clone(){
        return new BoundingArea((Area) area.clone());
    }

}
