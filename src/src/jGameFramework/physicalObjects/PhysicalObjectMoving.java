package jGameFramework.physicalObjects;

import jGameFramework.display.DisplayableDepth;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * An updating PhysicalObject that has a velocity: it can move.
 *
 * It will follow orders that was sent to it. For it to actually
 * move, the updateAndMove() method should be called.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class PhysicalObjectMoving extends PhysicalObjectUpdating {

    private Velocity velocity;

    private Set<Velocity.Direction> movingOrders;

    /**
     * Constructor
     */
    public PhysicalObjectMoving(BoundingArea area, boolean isObstacle, DisplayableDepth baseDepth, Vision vision, Velocity velocity) {
        super(area, isObstacle, vision, baseDepth);

        this.velocity = velocity;

        movingOrders = new ConcurrentSkipListSet<>();
    }

    /**
     * Adds the desired direction to the set that handles movingOrders.
     *
     * A set is used, therefore any direction will only be added once.
     */
    public void addToMovingOrders(Velocity.Direction direction){
        movingOrders.add(direction);
    }

    /**
     * A MovingObject will follow any moving orders up until that order
     * was deleted.
     */
    public void removeToMovingOrders(Velocity.Direction direction) {
        if (movingOrders.contains(direction)){
            movingOrders.remove(direction);
        }
    }

    public void removeAllMovingOrders(){
        movingOrders.clear();
    }

    /**
     * This method checks the current orders that the MovingObject should
     * follow. These were added in addToMovingOrders
     */
    protected void updateAndMove(TreeSet<PhysicalObject> surroundings){
        for (Velocity.Direction direction : movingOrders){
            moveTo(surroundings, direction);
        }
    }

    private void moveTo(TreeSet<PhysicalObject> surroundings, Velocity.Direction direction){
        Position desiredPosition = getPosition().add(velocity.goTo(direction, deltaValue));

        if (isObstacle()) {
            if (canMoveThere(surroundings, getObjectAreaAtPosition(desiredPosition))){
                setPositionTo(desiredPosition);
            }
        } else {
            setPositionTo(desiredPosition);
        }

    }

    private boolean canMoveThere(TreeSet<PhysicalObject> surroundings, BoundingArea areaAtDesiredPosition){
        for (PhysicalObject otherObject: surroundings){
            if (otherObject.isObstacle() && otherObject != this && otherObject.isColliding(areaAtDesiredPosition)){
                return false;

            }
        }

        return true;
    }

    public boolean isMoving() {
        return !movingOrders.isEmpty();
    }

}
