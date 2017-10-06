package coreActions;

import java.lang.reflect.Method;

/**
 * A specific event that will change the current game thread (the current state
 * of the game object).
 *
 * @author Jérémie Beaudoin-Dion
 */
abstract class GameThreadEvent extends GameEvent {

    @SuppressWarnings("unchecked")
    GameThreadEvent(Class classValue, Method methodValue) {
        super(classValue, methodValue);
    }

    @SuppressWarnings("unchecked")
    GameThreadEvent(Class classValue, Method methodValue, Object parameterValue) {
        super(classValue, methodValue, parameterValue);
    }
}
