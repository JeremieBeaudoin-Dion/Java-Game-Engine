package core;

import coreActions.Action;
import coreActions.GameEvent;
import physicalObjects.Camera;

import java.lang.reflect.InvocationTargetException;
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

    private Game gameObject;

    /**
     * Constructor
     */
    public ActionHandler(Game gameObject, ObjectHandler objectHandler) {
        this.objectHandler = objectHandler;
        this.gameObject = gameObject;
    }

    /**
     * Sends an action to do in the game
     */
    public void doAction(Action currentAction) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (currentAction.isDoable()) {
            handleActionEvent(currentAction.getAction());
        }
    }

    @SuppressWarnings("unchecked")
    private void handleActionEvent(GameEvent gameEvent) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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

    public void doAction(Action currentAction, Object parameterValue) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (currentAction.isDoable()) {
            handleActionEvent(currentAction.getAction(), parameterValue);
        }
    }

    @SuppressWarnings("unchecked")
    private void handleActionEvent(GameEvent gameEvent, Object parameterValue) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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
    public void update() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        handleActionsFromObjectHandler();
    }

    private void handleActionsFromObjectHandler() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<GameEvent> actionsToDo = objectHandler.getAllCurrentActions();

        for (GameEvent action : actionsToDo) {
            handleActionEvent(action);
        }
    }
}
