package actionOfGameHere;

import actionOfGameHere.actions.*;
import addGameObjectsHere.Camera;
import core.Game;
import core.HandlerOfActions;
import core.ObjectHandler;
import coreActions.ActionEvent;

import java.lang.reflect.InvocationTargetException;

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
public class ActionHandler extends HandlerOfActions {

    @SuppressWarnings("unchecked")
    protected void handleActionEvent(ActionEvent actionEvent) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (actionEvent.getClassValue() == ObjectHandler.class) {
            actionEvent.doAction(objectHandler);

        } else if (actionEvent.getClassValue() == Game.class) {
            actionEvent.doAction(gameObject);

        } else if (actionEvent.getClassValue() == Camera.class) {
            actionEvent.doAction(objectHandler.getCamera());
        }
    }

    /**
     * Constructor
     *
     * Not much to change here, this is already called in the
     * Game class.
     */
    public ActionHandler(Game gameObject, ObjectHandler objectHandler) {
        super(gameObject, objectHandler);
    }

}
