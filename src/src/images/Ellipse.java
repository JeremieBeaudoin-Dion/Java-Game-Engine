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
    private boolean filled;

    /**
     * Constructor
     *
     * @param dimension the dimension
     * @param depth if the ellipse is a BACKGROUND, FOREGROUND or FLOATING. See ImageObject for value
     * @param color the color
     * @param filled if the ellipse is filled or not. Passing false will only draw the outline
     */
    public Ellipse(Dimension dimension, int depth, Color color, boolean filled) {
        super(dimension, depth);
        this.color = color;
        this.filled = filled;
    }

    public Color getColor() {
        return color;
    }

    public boolean isFilled() {
        return filled;
    }
}
