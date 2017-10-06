package addGameObjectsHere;

import coreActions.GameEvent;
import images.Dimension;
import images.ImageObject;
import images.Position;
import images.Rectangle;
import images.Text;
import physicalObjects.*;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * An example of a simple Player object.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Player extends PhysicalObjectMoving {

    private static final Font FONT_PLAYER = new Font("Century Schoolbook", Font.PLAIN, 25);

    /**
     * Constructor
     */
    public Player(Dimension dimension, Vision vision, Velocity velocity) {
        super(dimension, true, vision, velocity);
    }


    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        updateAndMove(surroundings);

        return null;
    }

    @Override
    public TreeSet<ImageObject> getImageObjects(Position cameraPosition) {
        Position positionOnScreen = getPositionAccordingToCamera(cameraPosition);

        TreeSet<ImageObject> imagesToShowOnScreen = new TreeSet<>();

        imagesToShowOnScreen.add(new Rectangle(new Dimension(positionOnScreen, getDimension().getWidth(),
                getDimension().getHeight()),
                ImageObject.FOREGROUND, Color.darkGray, true));

        Position positionOfText = positionOnScreen.add(new Position(getDimension().getWidth()/3,
                        getDimension().getHeight()/2));

        imagesToShowOnScreen.add(new Text(positionOfText, "Player!", FONT_PLAYER,
                Color.GREEN, ImageObject.FLOATING));

        return imagesToShowOnScreen;
    }

    @Override
    public void doClick() throws NoSuchMethodException {

    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    /**
     * If the player would be disposed, the current
     * GameThread will be quit.
     */
    @Override
    public boolean dispose() {
        return false;
    }
}
