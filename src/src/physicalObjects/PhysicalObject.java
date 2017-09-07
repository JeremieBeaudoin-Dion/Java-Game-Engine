package physicalObjects;

import images.ImageObject;

import java.util.List;

/**
 * A physical object has an ImageObject representation of itself
 * that can be put on screen.
 *
 * @author Jérémie Beaudoin-Dion
 */
public interface PhysicalObject {

    List<ImageObject> getImageObjects();

}
