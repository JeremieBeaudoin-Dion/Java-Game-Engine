package core;

import coreActions.Action;
import coreActions.GameEvent;
import physicalObjects.Camera;

import java.util.List;

/**
 * Receives Actions from the InputHandler and sends
 * an GameEvent to the correct class.
 *
 * The only unimplemented method is updateAllActions() which
 * should call the method update() to all game dependent
 * actions.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ActionHandler {

    private ObjectHandler objectHandler;

    /**
     * Constructor
     */
    public ActionHandler(ObjectHandler objectHandler) {
        this.objectHandler = objectHandler;
    }

    /**
     * Sends an action to do in the game
     */
    public void doAction(Game gameObject, Action currentAction) {
        if (currentAction.isDoable()) {
            handleActionEvent(gameObject, currentAction.getAction());
        }
    }

    @SuppressWarnings("unchecked")
    private void handleActionEvent(Game gameObject, GameEvent gameEvent) {
        if (gameEvent.getClassValue() == ObjectHandler.class) {
            gameEvent.doAction(objectHandler);

        } else if (gameEvent.getClassValue() == Game.class) {
            gameEvent.doAction(gameObject);

        } else if (gameEvent.getClassValue() == Camera.class) {
            gameEvent.doAction(objectHandler.getCamera());

        } else {
            gameEvent.doAction(objectHandler.getObject(gameEvent.getClassValue()));
        }
    }

    public void doAction(Game gameObject, Action currentAction, Object parameterValue) {
        if (currentAction.isDoable()) {
            handleActionEvent(gameObject, currentAction.getAction(), parameterValue);
        }
    }

    @SuppressWarnings("unchecked")
    private void handleActionEvent(Game gameObject, GameEvent gameEvent, Object parameterValue) {
        if (gameEvent.getClassValue() == ObjectHandler.class) {
            gameEvent.doAction(objectHandler, parameterValue);

        } else if (gameEvent.getClassValue() == Game.class) {
            gameEvent.doAction(gameObject, parameterValue);

        } else if (gameEvent.getClassValue() == Camera.class) {
            gameEvent.doAction(objectHandler.getCamera(), parameterValue);

        } else {
            gameEvent.doAction(objectHandler.getObject(gameEvent.getClassValue()), parameterValue);
        }
    }

    /**
     * Called every frame
     */
    public void update(Game gameObject) {
        handleActionsFromObjectHandler(gameObject);
    }

    private void handleActionsFromObjectHandler(Game gameObject) {
        List<GameEvent> actionsToDo = objectHandler.getAllCurrentActions();

        for (GameEvent action : actionsToDo) {
            handleActionEvent(gameObject, action);
        }
    }
}
