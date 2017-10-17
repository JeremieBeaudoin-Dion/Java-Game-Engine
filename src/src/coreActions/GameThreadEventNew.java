package coreActions;

import core.Game;
import exceptions.ActionInvocationException;

import java.lang.reflect.Method;

/**
 * A specific GameEvent that will create a new Thread to the game
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameThreadEventNew extends GameThreadEvent {

    public GameThreadEventNew(String newThreadValue) {
        super(Game.class, getNewGameStateMethod(), newThreadValue);
    }

    private static Method getNewGameStateMethod(){
        try {
            return Game.class.getMethod("newGameState", String.class);
        } catch (NoSuchMethodException e) {
            throw new ActionInvocationException("newGameState", Game.class, e.getMessage());
        }
    }


}
