package images;

import java.awt.*;

/**
 * A rectangle has the basic attributes of an image and a color
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Rectangle extends ImageObject{

    private Color color;

    public Rectangle(Color color, Position position, int width, int height) {
        super(position, width, height);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
