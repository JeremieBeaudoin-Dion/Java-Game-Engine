package addGameObjectsHere;

import core.Game;
import coreActions.GameEvent;
import display.Displayable;
import display.DisplayableShapeOutline;
import physicalObjects.BoundingArea;
import physicalObjects.Position;
import physicalObjects.PhysicalObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * An example of a simple PhysicalObject
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Example_Wall extends PhysicalObject {

    private Color color = Color.WHITE;

    /**
     * Constructor
     */
    public Example_Wall() {
        super(new BoundingArea(getShapes()), true);
    }

    private static List<Shape> getShapes(){
        List<Shape> allShapes = new LinkedList<>();

        int baseX = Game.WINDOW_WIDTH/2;
        int baseY = Game.WINDOW_HEIGHT/4;

        allShapes.add(new Ellipse2D.Float(baseX + 10, baseY + 10, 100, 100));
        allShapes.add(new Line2D.Float(baseX + 60, baseY + 110, baseX + 60, baseY + 310));
        allShapes.add(new Line2D.Float(baseX + 60, baseY + 130, baseX + 120,baseY + 200));
        allShapes.add(new Line2D.Float(baseX + 60, baseY + 130, baseX + 0,baseY + 200));
        allShapes.add(new Line2D.Float(baseX + 60, baseY + 310, baseX + 120,baseY + 400));
        allShapes.add(new Line2D.Float(baseX + 60, baseY + 310, baseX + 0,baseY + 400));

        return allShapes;
    }

    /**
     * This PhysicalObject is DEPENDENT from the position of the Camera.
     * It will move according to it.
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition) {
        TreeSet<Displayable> screenRepresentation = new TreeSet<>();

        for (Shape shape : getShapes()){
            screenRepresentation.add(new DisplayableShapeOutline(Displayable.Depth.FOREGROUND, shape, color));
        }

        return screenRepresentation;
    }

    /**
     * Changes the color of the button randomly
     */
    @Override
    public void doClick() {
    }

    @Override
    public List<GameEvent> getAction() {
        return null;  // Never returns actions
    }

    @Override
    public boolean dispose() {
        return false;
    }

}
