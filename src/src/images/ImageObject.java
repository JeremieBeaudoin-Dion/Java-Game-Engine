package images;

/**
 * An ImageObject can be displayed on screen by ImageHandler
 * It has a position and a size, which are stored in an Dimension object
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageObject {

    private Dimension dimension;

    /**
     * Constructors
     */
    ImageObject(Dimension dimension) {
        this.dimension = dimension;
    }

    ImageObject(Position position, int width, int height) {
        dimension = new Dimension(position, width, height);
    }

    public ImageObject(int x, int y, int width, int height) {
        dimension = new Dimension(x, y, width, height);
    }

    /**
     * Getters
     */
    public Position getPosition() {
        return dimension.getPosition();
    }

    public int getWidth() {
        return dimension.getWidth();
    }

    public int getHeight() {
        return dimension.getHeight();
    }

}
