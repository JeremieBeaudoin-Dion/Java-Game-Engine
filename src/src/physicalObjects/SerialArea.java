package physicalObjects;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.io.IOException;
import java.io.Serializable;

/**
 * A serializable type of Area
 *
 * @author Jérémie Beaudoin-Dion
 */
public class SerialArea extends Area implements Serializable {
    private static final long serialVersionUID = -3627137348463415558L;

    /**
     * New Area
     */
    public SerialArea() {}

    /**
     * New Area From Shape
     */
    public SerialArea(Shape s) {
        super(s);
    }

    /**
     * Writes object out to out.
     * @param out Output
     * @throws IOException if I/O errors occur while writing to the
     *  underlying OutputStream
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(AffineTransform.getTranslateInstance(0, 0).
                createTransformedShape(this));
    }
    /**
     * Reads object in from in.
     * @param in Input
     * @throws IOException if I/O errors occur while writing to the
     *  underlying OutputStream
     * @throws ClassNotFoundException if the class of a serialized object
     *  could not be found.
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        add(new Area((Shape) in.readObject()));
    }
}
