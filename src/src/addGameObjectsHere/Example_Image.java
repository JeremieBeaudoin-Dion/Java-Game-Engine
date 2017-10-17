package addGameObjectsHere;

import addImageLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;
import jGameFramework.physicalObjects.PhysicalObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.TreeSet;

/**
 * An jGameFramework.example of an image PhysicalObject
 *
 * The PhysicalObjects should not have any images themselves
 * for them to be serializable.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Example_Image extends PhysicalObject {

    /**
     * Constructor
     */
    public Example_Image(BufferedImage image) {
        super(new BoundingArea(0, 0, image.getWidth(), image.getHeight()), false);
    }

    /**
     * This jGameFramework.example returns a simple image that is INDEPENDENT from the
     * position of the CameraWithEdges
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> myImageRepresentation = new TreeSet<>();

        Position positionOnScreen = getPositionAccordingToCamera(cameraPosition);

        Image myImage = imageLoader.getImageExample();

        myImageRepresentation.add(new DisplayableImage(positionOnScreen, Displayable.Depth.BACKGROUND, myImage));

        return myImageRepresentation;
    }

    @Override
    public void doClick() {

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
