package jGameFramework.coreActions;

import jGameFramework.core.threadObjects.GameThreadHandler;
import jGameFramework.exceptions.ActionInvocationException;

import java.lang.reflect.Method;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class GameThreadEventLoad extends GameThreadEvent {

    public GameThreadEventLoad(String saveFilePath) {
        super(GameThreadHandler.class, getSaveGameStateMethod(), saveFilePath);
    }

    private static Method getSaveGameStateMethod(){
        try {
            return GameThreadHandler.class.getMethod("loadGame", String.class);
        } catch (NoSuchMethodException e) {
            throw new ActionInvocationException("loadGame", GameThreadHandler.class, e.getMessage());
        }
    }

}
