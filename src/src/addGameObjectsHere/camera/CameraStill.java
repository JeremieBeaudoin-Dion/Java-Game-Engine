package addGameObjectsHere.camera;

import coreActions.GameEvent;
import display.Displayable;
import physicalObjects.Position;
import physicalObjects.Camera;
import physicalObjects.PhysicalObject;

import java.util.List;
import java.util.TreeSet;

/**
 * A camera that does not move.
 *
 * Useful for the menus
 *
 * @author Jérémie Beaudoin-Dion
 */
public class CameraStill extends Camera{

    public CameraStill(){
        super();
    }

    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        // Do nothing
        return null;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition) {
        return null;
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
