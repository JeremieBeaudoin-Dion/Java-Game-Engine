package physicalObjects;

import java.io.Serializable;

/**
 * A velocity determines how much a PhysicalObject
 * should move in a certain direction according to
 * its speed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class Velocity implements Serializable {

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    Position goTo(Direction direction){
        switch (direction){
            case UP:
                return goUp();

            case DOWN:
                return goDown();

            case LEFT:
                return goLeft();

            case RIGHT:
                return goRight();
        }

        throw new IllegalArgumentException("The direction: " + direction + " does not exists.");
    }

    abstract Position goUp();

    abstract Position goDown();

    abstract Position goLeft();

    abstract Position goRight();

}
