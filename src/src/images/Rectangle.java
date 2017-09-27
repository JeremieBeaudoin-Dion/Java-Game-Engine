package images;

import java.awt.*;

/**
 * A rectangle has the basic attributes of an image and a color
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Rectangle extends ImageObject{

    private Color color;
    private boolean filled;

    /**
     * Constructor
     *
     * @param dimension the dimension of the rectangle
     * @param depth if the rectangle is a BACKGROUND, FOREGROUND or FLOATING. See ImageObject for value
     * @param color the color
     * @param filled if the rectangle is filled or not. Passing false will only draw the outline of the rectangle
     */
    public Rectangle(Dimension dimension, int depth, Color color, boolean filled) {
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
