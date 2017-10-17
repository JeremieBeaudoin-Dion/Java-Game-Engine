package addGameThreadCreatorHere;

import addImageLoaderHere.ImageLoader;
import core.GameThread;
import core.ThreadManager;

/**
 * Do not delete. This class is necessary for the
 * game to run.
 *
 * This will instantiate GameThreads according to the
 * desired thread's value.
 *
 * In this case, a GameThreadContentManager was created to
 * simplify the code.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameThreadCreator implements ThreadManager{

    public final static String GAME_NAME = "Test";

    public final static int WINDOW_WIDTH = 900;
    public final static int WINDOW_HEIGHT = 600;

    public static final long FRAMES_PER_SECOND = 60;

    // Threads
    public final static String GAME_MENU = "Menu";
    public final static String GAME_LEVEL_1 = "Level1";

    private GameThreadContentManager gameThreadContentManager;

    /**
     * Constructor
     *
     * @param imageLoader the object containing all game display
     */
    public GameThreadCreator(ImageLoader imageLoader) {
        gameThreadContentManager = new GameThreadContentManager(imageLoader);
    }

    @Override
    public GameThread getThread(String id) {
        switch (id) {

            case GAME_MENU:
                return gameThreadContentManager.getStartGame();

            case GAME_LEVEL_1:
                return gameThreadContentManager.getLevel1();

        }

        throw new IllegalArgumentException("The thread ID " + id + " is not valid");
    }

    @Override
    public GameThread startGame() {
        return gameThreadContentManager.getStartGame();
    }

}
