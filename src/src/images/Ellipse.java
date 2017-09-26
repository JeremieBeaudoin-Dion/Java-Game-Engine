package images;

import java.awt.*;

/**
 * A image object that can be drawn in the shape of an ellipse with:
 *
 * // bbg.setColor()
 * // bbg.fillRoundRect();
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Ellipse extends ImageObject {

    private Color color;

    public Ellipse(Dimension dimension, int depth, Color color) {
        super(dimension, depth);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
