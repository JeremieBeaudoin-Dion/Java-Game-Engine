package actionOfGameHere.actions;

import addGameObjectsHere.Player;
import coreActions.ActionEvent;

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
        return new ActionEvent<Player>(Player.class, Player.class.getMethod("storeAction", Integer.class), Player.MOVE_RIGHT);
    }

}
