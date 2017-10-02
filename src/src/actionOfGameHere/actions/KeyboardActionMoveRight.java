package actionOfGameHere.actions;

import coreActions.ActionEvent;
import addGameObjectsHere.Camera;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class KeyboardActionMoveRight implements KeyboardActionMove {

    /**
     * Can always be called
     */
    @Override
    public boolean isDoable() {
        return true;
    }

    @Override
    public ActionEvent doAction() throws NoSuchMethodException {
        return new ActionEvent<Camera>(Camera.class, Camera.class.getMethod("moveRight"));
    }

}
