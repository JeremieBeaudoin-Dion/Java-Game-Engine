package addGameObjectsHere;

import core.Game;
import images.*;
import images.Dimension;
import images.Rectangle;
import physicalObjects.ObjectProperties;
import physicalObjects.PhysicalObject;
import physicalObjects.Velocity;

import java.awt.*;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class Example_Button extends PhysicalObject {

    private static final Font FONT_BUTTON = new Font("Century Schoolbook", Font.PLAIN, 35);;

    /**
     * Constructor
     */
    public Example_Button() {
        super(new ObjectProperties(new Dimension(new Position(Game.WINDOW_HEIGHT/2, Game.WINDOW_WIDTH/2),
                300, 50), new Velocity(0, 0)));
    }

    @Override
    public TreeSet<ImageObject> getImageObjects(Position cameraPosition) {
        Position positionRelativeToScreen = getPositionAccordingToCamera(cameraPosition);

        TreeSet<ImageObject> imagesToShowOnScreen = new TreeSet<>();

        imagesToShowOnScreen.add(new Rectangle(new Dimension(positionRelativeToScreen,
                getProperties().getDimension().getWidth(), getProperties().getDimension().getHeight()),
                ImageObject.FOREGROUND, Color.darkGray, true));

        positionRelativeToScreen.add(new Position(0, getProperties().getDimension().getHeight()/2));

        imagesToShowOnScreen.add(new Text(positionRelativeToScreen, "PLAY!", FONT_BUTTON,
                Color.BLACK, ImageObject.FLOATING));

        return imagesToShowOnScreen;
    }
}
