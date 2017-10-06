package physicalObjects;

import images.Position;

/**
 * The x and y speed of any object
 *
 * When calling the goRight(), goLeft(), goUp() or goDown()
 * the VelocitySquare returns a position that can be added to any
 * position in order to move to the desired direction.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class VelocitySquare extends Velocity {

    private int x;
    private int y;

    /**
     * Constructor
     */
    public VelocitySquare(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Position goRight() {
        return new Position(x, 0);
    }

    Position goLeft() {
        return new Position(-x, 0);
    }

    Position goUp() {
        return new Position(0, -y);
    }

    Position goDown() {
        return new Position(0, y);
    }
}
