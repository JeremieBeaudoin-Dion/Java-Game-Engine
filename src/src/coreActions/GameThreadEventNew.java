package coreActions;

import core.Game;

/**
 * A specific GameEvent that will create a new Thread to the game
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameThreadEventNew extends GameThreadEvent {

    public GameThreadEventNew(Integer newThreadValue) throws NoSuchMethodException {
        super(Game.class, Game.class.getMethod("newGameState", int.class), newThreadValue);
    }


}
