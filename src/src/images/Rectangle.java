package images;

import java.awt.*;

/**
 * A rectangle has the basic attributes of an image and a color
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Rectangle extends ImageObject{

    private Color color;

    public Rectangle(Dimension dimension, int depth, Color color) {
        super(dimension, depth);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
