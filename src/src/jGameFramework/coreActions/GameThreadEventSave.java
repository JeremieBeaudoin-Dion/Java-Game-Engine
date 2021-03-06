package jGameFramework.coreActions;

import jGameFramework.core.threadObjects.GameThreadHandler;
import jGameFramework.exceptions.ActionInvocationException;

import java.lang.reflect.Method;

/**
 * A specific GameEvent that will save the current GameThread
 * with the desired String for the name of the File.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameThreadEventSave extends GameThreadEvent {

    public GameThreadEventSave(String saveFilePath) {
        super(getSaveGameStateMethod(), saveFilePath);
    }

    private static Method getSaveGameStateMethod(){
        try {
            return GameThreadHandler.class.getMethod("saveGame", String.class);
        } catch (NoSuchMethodException e) {
            throw new ActionInvocationException("saveGame", GameThreadHandler.class, e.getMessage());
        }
    }

}
