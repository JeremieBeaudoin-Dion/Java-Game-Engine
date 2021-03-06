package jGameFramework.coreActions;

import jGameFramework.core.threadObjects.GameThreadHandler;
import jGameFramework.exceptions.ActionInvocationException;
import jGameFramework.physicalObjects.Position;

import java.lang.reflect.Method;

/**
 * Will change the size of the screen on the next frame.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameThreadEventResize extends GameThreadEvent {

    public GameThreadEventResize(Position screenSize) {
        super(getResizeMethod(), screenSize);
    }

    private static Method getResizeMethod(){
        try {
            return GameThreadHandler.class.getMethod("setNextResize", Position.class);
        } catch (NoSuchMethodException e) {
            throw new ActionInvocationException("setNextResize", GameThreadHandler.class, e.getMessage());
        }
    }
}
