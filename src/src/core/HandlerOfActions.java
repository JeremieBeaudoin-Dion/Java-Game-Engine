package core;

import addGameObjectsHere.Camera;
import coreActions.Action;
import coreActions.ActionEvent;

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
public abstract class HandlerOfActions {

    protected ObjectHandler objectHandler;

    protected Game gameObject;

    /**
     * Constructor
     */
    public HandlerOfActions(Game gameObject, ObjectHandler objectHandler) {
        this.objectHandler = objectHandler;
        this.gameObject = gameObject;
    }

    /**
     * Sends an action to do in the game
     */
    public void doAction(Action currentAction) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (currentAction.isDoable()) {
            handleActionEvent(currentAction.doAction());
        }
    }

    @SuppressWarnings("unchecked")
    protected void handleActionEvent(ActionEvent actionEvent) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (actionEvent.getClassValue() == ObjectHandler.class) {
            actionEvent.doAction(objectHandler);

        } else if (actionEvent.getClassValue() == Game.class) {
            actionEvent.doAction(gameObject);
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
