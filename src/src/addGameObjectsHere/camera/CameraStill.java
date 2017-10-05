package addGameObjectsHere.camera;

import coreActions.ActionEvent;
import images.Dimension;
import images.ImageObject;
import images.Position;
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

    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        // Do nothing
        return null;
    }

    @Override
    public TreeSet<ImageObject> getImageObjects(Position cameraPosition) {
        return null;
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
