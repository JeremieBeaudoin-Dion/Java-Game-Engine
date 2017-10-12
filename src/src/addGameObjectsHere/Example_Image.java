package addGameObjectsHere;

import coreActions.GameEvent;
import display.Displayable;
import display.DisplayableImage;
import physicalObjects.BoundingArea;
import physicalObjects.Position;
import physicalObjects.PhysicalObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.TreeSet;

/**
 * An example of an image PhysicalObject
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Example_Image extends PhysicalObject {

    private Image myImage;

    /**
     * Constructor
     */
    public Example_Image(BufferedImage myImage) {
        super(new BoundingArea(0, 0, myImage.getWidth(), myImage.getHeight()), false);

        this.myImage = myImage;
    }

    /**
     * This example returns a simple image that is INDEPENDENT from the
     * position of the CameraWithEdges
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition) {
        TreeSet<Displayable> myImageRepresentation = new TreeSet<>();

        Position positionOnScreen = getPositionAccordingToCamera(cameraPosition);

        myImageRepresentation.add(new DisplayableImage(positionOnScreen, Displayable.Depth.BACKGROUND, myImage));

        return myImageRepresentation;
    }

    @Override
    public void doClick() throws NoSuchMethodException {

    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }
}
