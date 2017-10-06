package core;

import coreActions.Action;
import coreActions.ActionEvent;
import physicalObjects.Camera;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Receives Actions from the InputHandler and sends
 * an ActionEvent to the correct class.
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
    private void handleActionEvent(ActionEvent actionEvent) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (actionEvent.getClassValue() == ObjectHandler.class) {
            actionEvent.doAction(objectHandler);

        } else if (actionEvent.getClassValue() == Game.class) {
            actionEvent.doAction(gameObject);

        } else if (actionEvent.getClassValue() == Camera.class) {
            actionEvent.doAction(objectHandler.getCamera());

        } else {
            actionEvent.doAction(objectHandler.getObject(actionEvent.getClassValue()));
        }
    }

    public void doAction(Action currentAction, Object parameterValue) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (currentAction.isDoable()) {
            handleActionEvent(currentAction.getAction());
        }
    }

    @SuppressWarnings("unchecked")
    private void handleActionEvent(ActionEvent actionEvent, Object parameterValue) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (actionEvent.getClassValue() == ObjectHandler.class) {
            actionEvent.doAction(objectHandler, parameterValue);

        } else if (actionEvent.getClassValue() == Game.class) {
            actionEvent.doAction(gameObject, parameterValue);

        } else if (actionEvent.getClassValue() == Camera.class) {
            actionEvent.doAction(objectHandler.getCamera(), parameterValue);

        } else {
            actionEvent.doAction(objectHandler.getObject(actionEvent.getClassValue()), parameterValue);
        }
    }

    /**
     * Called every frame
     */
    public void update() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        handleActionsFromObjectHandler();
    }

    private void handleActionsFromObjectHandler() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<ActionEvent> actionsToDo = objectHandler.getAllCurrentActions();

        for (ActionEvent action : actionsToDo) {
            handleActionEvent(action);
        }
    }
}
