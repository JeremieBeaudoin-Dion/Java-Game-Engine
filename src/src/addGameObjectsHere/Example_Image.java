package addGameObjectsHere;

import coreActions.ActionEvent;
import images.Dimension;
import images.Image;
import images.ImageObject;
import images.Position;
import physicalObjects.PhysicalObject;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.TreeSet;

/**
 * An example of an image PhysicalObject
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Example_Image extends PhysicalObject {

    private BufferedImage myImage;

    /**
     * Constructor
     */
    public Example_Image(BufferedImage myImage) {
        super(new Dimension(0, 0, myImage.getWidth(), myImage.getHeight()));

        this.myImage = myImage;
    }

    /**
     * This example returns a simple image that is INDEPENDENT from the
     * position of the CameraWithEdges
     */
    @Override
    public TreeSet<ImageObject> getImageObjects(Position cameraPosition) {
        TreeSet<ImageObject> myImageRepresentation = new TreeSet<>();

        Position positionOnScreen = getPositionAccordingToCamera(cameraPosition);

        myImageRepresentation.add(new Image(new Dimension(positionOnScreen, getDimension().getWidth(),
                getDimension().getHeight()), ImageObject.BACKGROUND, myImage));

        return myImageRepresentation;
    }

    @Override
    public void doClick() throws NoSuchMethodException {

    }

    @Override
    public List<ActionEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }
}
