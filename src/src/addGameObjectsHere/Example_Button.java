package addGameObjectsHere;

import addGameThreadCreatorHere.GameThreadCreator;
import addImageLoaderHere.ImageLoader;
import core.Game;
import coreActions.GameEvent;
import coreActions.GameThreadEventNew;
import display.Displayable;
import display.DisplayableShapeFilled;
import display.DisplayableText;
import physicalObjects.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * An example of a button which, on click, will return an action
 * that will set the game to a new thread (GAME_LEVEL_1 in this case).
 *
 * This example is pretty simple and could have been set as a child
 * of a parent Button, but it should be a good example of the use
 * of the Framework.
 *
 * The action is set to null every frame, to ensure that it is
 * done only once.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Example_Button extends PhysicalObject {

    private static final Font FONT_BUTTON = new Font("Century Schoolbook", Font.PLAIN, 25);

    private static final int width = 300;
    private static final int height = 50;

    private GameEvent actionToDo;

    /**
     * Constructor
     *
     * In this example, no argument is passed as Position, which is simpler
     * but less viable. A position could be passed to make the Button more
     * generic.
     */
    public Example_Button() {
        super(BoundingArea.getRectangleAreaFromCenterPosition(new Position(Game.WINDOW_HEIGHT/2, Game.WINDOW_WIDTH/2),
                width, height), false);

        actionToDo = null;
    }

    /**
     * Returns a square with the text "PLAY!" printed on it.
     *
     * In this example, the display of this button is INDEPENDENT
     * of the position of the camera.
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> imagesToShowOnScreen = new TreeSet<>();

        RoundRectangle2D rect = new RoundRectangle2D.Double(getBoundingArea().getX(), getBoundingArea().getY(), width, height, 5, 5);

        GradientPaint paint = new GradientPaint(getBoundingArea().getX(), getBoundingArea().getY(), Color.DARK_GRAY,
                getBoundingArea().getCenterPosition().getX(), getBoundingArea().getCenterPosition().getY(), Color.BLACK);

        imagesToShowOnScreen.add(new DisplayableShapeFilled(Displayable.Depth.FOREGROUND, rect, paint));

        Position positionOfText = getPosition().add(new Position(width/3,height/2));

        imagesToShowOnScreen.add(new DisplayableText(positionOfText, Displayable.Depth.FLOATING, "PLAY!",
                FONT_BUTTON, Color.GREEN));

        return imagesToShowOnScreen;
    }

    @Override
    public void doClick() {
        actionToDo = new GameThreadEventNew(GameThreadCreator.GAME_LEVEL_1);
    }

    /**
     * Returns a list of actions to do. In this case, it will only
     * have one element if any.
     *
     * Ensures that the action is set back to null after it is called
     */
    @Override
    public List<GameEvent> getAction() {
        List<GameEvent> actionsToDo = new LinkedList<>();

        if (actionToDo != null) {
            actionsToDo.add(actionToDo);
            actionToDo = null;
        }

        return actionsToDo;
    }

    @Override
    public boolean dispose() {
        return false;
    }

}
