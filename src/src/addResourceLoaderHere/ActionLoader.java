package addResourceLoaderHere;

import addGameObjectsHere.Player;
import jGameFramework.collections.InputActionKeyMap;
import jGameFramework.core.Loader;
import jGameFramework.core.MouseHandler;
import jGameFramework.core.threadObjects.ObjectHandler;
import jGameFramework.coreActions.*;
import jGameFramework.physicalObjects.Position;
import jGameFramework.physicalObjects.Velocity;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * An instance of Loader that returns InputActionKeyMap,
 * a Map necessary to interpret input from the user.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ActionLoader implements Loader<InputActionKeyMap> {

    public InputActionKeyMap get(GameThreadID gameThreadID){

        switch (gameThreadID){
            case Menu:
                return getStartGameKeyMap();

            case Level1:
                return getLevel1KeyMap();
        }

        throw new IllegalArgumentException("The GameThreadID: " + gameThreadID + " is not implemented.");
    }

    private InputActionKeyMap getStartGameKeyMap(){
        HashMap<Integer, Action> actionReleaseMap = new HashMap<>();

        // when L is released, load the game
        actionReleaseMap.put(KeyEvent.VK_L, new Action(new GameThreadEventLoad("savedGame.s")));

        return new InputActionKeyMap(null, actionReleaseMap, null, ActionMapHelper.getAllMouseReleaseActions(), null);
    }

    private InputActionKeyMap getLevel1KeyMap(){
        HashMap<Integer, Action> actionReleaseMap = ActionMapHelper.getAllMoveReleaseActions(Player.class);

        // when P is released, save the game
        actionReleaseMap.put(KeyEvent.VK_P, new Action(new GameThreadEventSave("savedGame.s")));

        return new InputActionKeyMap(ActionMapHelper.getAllMovePushActions(Player.class), actionReleaseMap,
                ActionMapHelper.getAllMousePressedActions(), ActionMapHelper.getAllMouseReleaseActions(), null);
    }

}

