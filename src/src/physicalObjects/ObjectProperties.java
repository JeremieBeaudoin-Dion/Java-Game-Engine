package physicalObjects;

import images.Dimension;

/**
 * Every PhysicalObject has a Dimension,
 * Speed, etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ObjectProperties {

    private Dimension dimension;
    private Velocity velocity;

    public ObjectProperties(Dimension dimension, Velocity velocity) {
        this.dimension = dimension;
        this.velocity = velocity;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Velocity getVelocity() {
        return velocity;
    }

}
