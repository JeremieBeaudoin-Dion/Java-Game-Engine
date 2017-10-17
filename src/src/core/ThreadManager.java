package core;

/**
 * A thread manager will create GameThreads
 * for the game to handle
 *
 * @author Jérémie Beaudoin-Dion
 */
public interface ThreadManager {

    GameThread getThread(String id);

    GameThread startGame();

}
