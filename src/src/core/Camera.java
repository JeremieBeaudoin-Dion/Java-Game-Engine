package core;

import images.Position;

/**
 * The Camera has a position, and the Width and Height of the Game
 *
 * It defines what will be shown in ImageHandler
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Camera {

    private Position position;

    private final static int X_VELOCITY = 5;
    private final static int Y_VELOCITY = 5;

    public Camera() {
        position = new Position(0, 0);
    }

    public void moveRight() {
    }

    public Position getPosition() {
        return position;
    }

}
