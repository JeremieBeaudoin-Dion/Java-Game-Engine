package images;

/**
 * An ImageObject can be displayed on screen by ImageHandler
 * It has a position and a size, which are stored in an Dimension object
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageObject implements Comparable<ImageObject> {

    public final static int BACKGROUND = 0;
    public final static int FOREGROUND = 1;
    public final static int FLOATING = 2;

    private Dimension dimension;

    private int depth;

    /**
     * Constructors
     */
    ImageObject(Dimension dimension, int depth) {
        this.dimension = dimension;

        if (depth > FLOATING || depth < BACKGROUND) {
            throw new IllegalArgumentException("The depth " + depth + " is not a valid value");
        }

        this.depth = depth;
    }

    /**
     * ImageObjects are foremost compared with their depth values.
     * A FLOATING image will be added to the frame after any FOREGROUND
     * images, for example.
     *
     * If the depth values are the same, the positions are compared
     */
    @Override
    public int compareTo(ImageObject imageObject) {
        if (depth == imageObject.depth) {
            return dimension.getPosition().compareTo(imageObject.getPosition());
        }

        return depth - imageObject.depth;
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
