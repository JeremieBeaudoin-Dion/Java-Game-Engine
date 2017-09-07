package images;

/**
 * An ImageObject can be displayed on screen by ImageHandler
 * It has a position and a size, which are stored in an Attributes object
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageObject {

    private Attributes attributes;

    /**
     * Constructors
     */
    ImageObject(Attributes attributes) {
        this.attributes = attributes;
    }

    ImageObject(Position position, int width, int height) {
        attributes = new Attributes(position, width, height);
    }

    public ImageObject(int x, int y, int width, int height) {
        attributes = new Attributes(x, y, width, height);
    }

    /**
     * Getters
     */
    public Position getPosition() {
        return attributes.getPosition();
    }

    public int getWidth() {
        return attributes.getWidth();
    }

    public int getHeight() {
        return attributes.getHeight();
    }

}
