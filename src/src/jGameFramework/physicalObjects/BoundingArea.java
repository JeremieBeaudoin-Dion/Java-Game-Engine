package jGameFramework.physicalObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.List;

/**
 * This class was made to simplify the actual Area class from JavaSE7.
 *
 * Every physical object has a BoundingArea which will help determine
 * collision and position in the game.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class BoundingArea implements Cloneable, Serializable {

    private SerialArea area;
    private boolean highPrecisionCollision;

    /**
     * Creates a simple BoundingArea with a rectangular shape
     */
    public BoundingArea(int x, int y, int width, int height){
        this.area = new SerialArea(new Rectangle2D.Float(x, y, width, height));
        highPrecisionCollision = false;
    }

    /**
     * Constructor with any Shape
     */
    public BoundingArea(Shape shape){
        this.area = new SerialArea(shape);
        highPrecisionCollision = !isRectangular();
    }

    /**
     * Constructor with an Area. This is useful if
     * many shapes are involved.
     */
    public BoundingArea(SerialArea area){
        this.area = area;
        highPrecisionCollision = !isRectangular();
    }

    /**
     * Creates a BoundingArea from an image
     *
     * WARNING! This does NOT stores the image in the area.
     * @param image: The Image to be transfered to an Area
     */
    public BoundingArea(int x, int y, Image image) {
        this.area = new SerialArea(new Rectangle2D.Float(x, y,
                image.getWidth(null), image.getHeight(null)));
        highPrecisionCollision = false;
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
    private static SerialArea getAreaFromShapes(List<Shape> shapes){
        SerialArea desiredArea = new SerialArea();

        for (Shape shape: shapes){
            desiredArea.add(new SerialArea(shape));
        }

        return desiredArea;
    }

    /**
     * Creates a new RectangleBoundingArea according to a position which
     * represent the center of the Area and a width and height
     */
    public static BoundingArea getRectangleAreaFromCenterPosition(Position centerPosition, int width, int height){
        Position actualPosition = centerPosition.add(new Position(-width/2, -height/2));

        return new BoundingArea(new Rectangle2D.Float(actualPosition.getX(), actualPosition.getY(), width, height));
    }

    public static Position getCenterPositionFromRectangle(Position topLeft, int width, int height) {
        Rectangle2D rect = new Rectangle2D.Float(topLeft.getX(), topLeft.getY(), width, height);

        return getCenterPosition(rect);
    }

    public static Position getCenterPosition(Shape shape) {
        return new Position(shape.getBounds2D().getCenterX(), shape.getBounds2D().getCenterY());
    }

    // Collisions
    public boolean collidesWith(Position position){
        return area.contains(position.getX(), position.getY());
    }

    boolean collidesWith(BoundingArea other){
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

    /**
     * Returns true if this object fully encompasses the other
     */
    boolean isWithinBounds(BoundingArea other) {
        Rectangle2D otherBounds = other.area.getBounds2D();

        return this.area.contains(otherBounds);
    }

    // Setters
    /**
     * Moves the area without changing the shape
     */
    void setPosition(Position newPosition){
        area.transform(AffineTransform.getTranslateInstance(
                newPosition.getX() - getX(), newPosition.getY() - getY()));
    }

    /**
     * Sets the width and the height to the desired value
     *
     * These can't be zero. If any of those values are negative,
     * the ABS() value will become the new width or height
     */
    void setWidthAndHeight(double width, double height) {
        if (width != 0 && height != 0) {
            scaleAreaWithoutTranslatingIt(width / area.getBounds2D().getWidth(),
                    height / area.getBounds2D().getHeight());
        }
    }

    /**
     * It seems that the Java AffineTransform changes the position of the
     * area when scaling it (see AreaTransformTest)
     */
    private void scaleAreaWithoutTranslatingIt(double scaleX, double scaleY) {
        Position oldPosition = new Position(getX(), getY());

        area.transform(AffineTransform.getScaleInstance(scaleX, scaleY));

        setPosition(oldPosition);
    }

    // Getters
    /**
     * This was made to give access to all the methods of the Area from
     * Java. Other methods were added as well to simplify the code and
     * ensure (for jGameFramework.example) that the area would not changed undesirably.
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

    public double getWidth() {
        return getArea().getBounds2D().getWidth();
    }

    public double getHeight() {
        return getArea().getBounds2D().getHeight();
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

    public String toString() {
        return "BoundingArea at Position:" + getPosition() + " of width :" + getWidth() + " and height :" + getHeight();
    }

}
