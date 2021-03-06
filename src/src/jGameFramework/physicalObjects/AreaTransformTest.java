package jGameFramework.physicalObjects;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 * I still don't know why, but my areas seem to
 * translate as well as scale when an AffineTransform
 * is made on them.
 *
 * I will find a more elegant solution to the problem,
 * but now this is my solution.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AreaTransformTest {

    public static void main(String[] args) {

        createBoundingAreaAndScaleIt();

    }

    private static void createBoundingAreaAndScaleIt() {
        BoundingArea boundingArea = new BoundingArea(new Rectangle2D.Float(10, 10, 80, 90));

        System.out.println(boundingArea);

        boundingArea.setWidthAndHeight(100, 70);

        System.out.println("Setting width and height to (100, 70)...");

        System.out.println(boundingArea);

        boundingArea.setWidthAndHeight(100, 70);

        System.out.println("Trying to set to negative value: (-10, 2)...");

        boundingArea.setWidthAndHeight(-10, 2);

        System.out.println(boundingArea);
    }

    private static void scaleAreaWithoutTranslating() {
        Area area = new Area(new Rectangle2D.Float(20, 20, 7, 8));

        printArea(area);

        AffineTransform scaleTransform = new AffineTransform();

        Position widthAndHeight = new Position(80, 70);
        Position oldPosition = new Position(area.getBounds().x, area.getBounds().y);

        scaleTransform.scale(widthAndHeight.getX() / area.getBounds2D().getWidth(), widthAndHeight.getY() / area.getBounds2D().getHeight());

        System.out.println("Scaling Area...");

        area.transform(scaleTransform);

        AffineTransform returnToPreviousPosition = new AffineTransform();

        returnToPreviousPosition.translate(oldPosition.getX() - area.getBounds2D().getX(),oldPosition.getY() - area.getBounds2D().getY());

        System.out.println("Translating Area...");

        area.transform(returnToPreviousPosition);

        printArea(area);
    }

    private static void areaWhenScaledIsAlsoTranslated() {
        Area area = new Area(new Rectangle2D.Float(20, 20, 7, 8));

        printArea(area);

        AffineTransform scaleTransform = new AffineTransform();

        Position widthAndHeight = new Position(80, 70);
        Position oldPosition = new Position(area.getBounds().x, area.getBounds().y);

        scaleTransform.scale(widthAndHeight.getX() / area.getBounds2D().getWidth(), widthAndHeight.getY() / area.getBounds2D().getHeight());

        System.out.println("Scaling Area...");

        area.transform(scaleTransform);

        printArea(area);
    }

    private static void printArea(Area area) {
        System.out.println("Area at position(" + area.getBounds2D().getX() + ", " + area.getBounds2D().getY() + ") of width :"
            + area.getBounds2D().getWidth() + " and height :" + area.getBounds2D().getHeight());
    }

    private static void printAreaBoundingBox(Area area) {
        System.out.println("Area at position(" + area.getBounds2D().getX() + ", " + area.getBounds2D().getY() + ") , ("
                + area.getBounds2D().getMaxX() + ", " + area.getBounds2D().getMaxY() + ")");
    }

}
