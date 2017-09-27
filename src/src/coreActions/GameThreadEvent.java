package coreActions;

import java.lang.reflect.Method;

/**
 * A specific event that will change the current game thread
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class GameThreadEvent extends ActionEvent {

    public GameThreadEvent(Class classValue, Method methodValue) {
        super(classValue, methodValue);
    }

    public GameThreadEvent(Class classValue, Method methodValue, Object parameterValue) {
        super(classValue, methodValue, parameterValue);
    }
}
