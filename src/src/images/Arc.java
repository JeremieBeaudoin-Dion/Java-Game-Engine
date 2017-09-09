package images;

import java.awt.*;

/**
 * An arc is a line which has a startAngle and an arcAngle
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Arc extends ImageObject {

    private Color color;
    private int startAngle;
    private int arcAngle;

    public Arc(Color color, Position position, int width, int height, int startAngle, int arcAngle) {
        super(position, width, height);
        this.color = color;
        this.arcAngle = arcAngle;
        this.startAngle = startAngle;
    }

    public Arc(Color color, Dimension dimension, int startAngle, int arcAngle) {
        super(dimension);
        this.color = color;
        this.arcAngle = arcAngle;
        this.startAngle = startAngle;
    }

    public Color getColor() {
        return color;
    }

    public int getStartAngle() {
        return startAngle;
    }

    public int getArcAngle() {
        return arcAngle;
    }
}
