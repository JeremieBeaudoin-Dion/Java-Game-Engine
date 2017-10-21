package jGameFramework.coreActions;

import jGameFramework.core.threadObjects.GameThreadHandler;
import jGameFramework.exceptions.ActionInvocationException;

import java.lang.reflect.Method;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class GameThreadEventSave extends GameThreadEvent {

    public GameThreadEventSave(String saveFilePath) {
        super(GameThreadHandler.class, getSaveGameStateMethod(), saveFilePath);
    }

    private static Method getSaveGameStateMethod(){
        try {
            return GameThreadHandler.class.getMethod("saveGame", String.class);
        } catch (NoSuchMethodException e) {
            throw new ActionInvocationException("saveGame", GameThreadHandler.class, e.getMessage());
        }
    }

}
