package images;

/**
 * A Position is an object which has two variables representing
 * the X axis and the Y axis as Integers.
 *
 *@author Jérémie Beaudoin-Dion
 */
public class Position {

    private int x;
    private int y;

    /**
     * The constructor
     *
     * @param x: the X axis integer
     * @param y: the Y axis integer
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}