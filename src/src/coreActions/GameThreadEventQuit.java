package coreActions;

import core.Game;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class GameThreadEventQuit extends GameThreadEvent {

    public GameThreadEventQuit() throws NoSuchMethodException {
        super(Game.class, Game.class.getMethod("quitCurrentState"));
    }

}
