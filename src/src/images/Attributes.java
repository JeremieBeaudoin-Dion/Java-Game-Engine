package images;

/**
 * The Attributes of an image is its position, width and height
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Attributes {

    private Position position;
    private int width;
    private int height;

    public Attributes(Position position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Attributes(int x, int y, int width, int height) {
        this.position = new Position(x, y);
        this.width = width;
        this.height = height;
    }

    public Position getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
