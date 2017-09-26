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

    public Arc(Dimension dimension, int depth, Color color, int startAngle, int arcAngle) {
        super(dimension, depth);
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
