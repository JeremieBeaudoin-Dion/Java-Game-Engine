package core;

import addGameThreadCreatorHere.GameThreadCreator;
import addImageLoaderHere.ImageLoader;
import exceptions.ActionInvocationException;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Stack;
import java.util.Timer;

/**
 * The Main class for the game
 * This class handles game flow
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Game extends java.util.TimerTask {

    final static String GAME_NAME = GameThreadCreator.GAME_NAME;

    public final static int WINDOW_WIDTH = GameThreadCreator.WINDOW_WIDTH;
    public final static int WINDOW_HEIGHT = GameThreadCreator.WINDOW_HEIGHT;

    // Variables for the game
    private static boolean isRunning = true;
    private static Timer timer;
    private static final long FPS = GameThreadCreator.FRAMES_PER_SECOND;

    // All game threads
    private Stack<GameThread> threads;
    private GameThreadCreator gameThreadCreator;

    // Instances of the core elements of the game
    private ImageHandler imageHandler;
    private ImageLoader imageLoader;

    /**
     * Main method to run the game
     */
    public static void main(String[] args){

        Game game = null;
        try {
            game = new Game();

        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
            System.exit(0);

        } catch (ActionInvocationException e) {
            System.exit(0);
        }

        timer = new Timer();
        timer.schedule(game, 0, 1000 / Game.FPS);

    }

    /**
     * This method will set up everything needed for the game to run
     *
     * @throws IOException if an image is missing from the directory
     */
    private Game() throws IOException, UnsupportedAudioFileException {
        threads = new Stack<>();

        imageHandler = new ImageHandler();

        imageHandler.addKeyListener(new KeyHandler(this));
        imageHandler.addMouseListener(new MouseHandler(this));

        imageLoader = new ImageLoader();

        gameThreadCreator = new GameThreadCreator(this, imageLoader);

        threads.push(gameThreadCreator.startGame());
    }

    /**
     * This method starts the game and runs it in a loop
     * It updates all CoreHandler objects
     */
    @Override
    public void run() {

        // Gets current time for fps
        long time = System.nanoTime();

        try {

            update();

        } catch (ActionInvocationException e) {
            /*
            * Something went wrong in the code, it is due to
            * bad implementation of the Actions. A change to
            * the code is required.
             */
            e.printStackTrace();
            isRunning = false;
        }

        if (!isRunning) {
            timer.cancel();
            System.exit(0);
        }

        verifyFrameRate(System.nanoTime() - time); // For testing purposes
    }

    /**
     * Updates the game every frame
     */
    private void update() {
        threads.peek().update();

        imageHandler.update(threads.peek().getImages(imageLoader));
    }

    /**
     * Pops the current thread. If the stack of threads is
     * empty, quits the game.
     */
    public void quitCurrentState() {
        if (!threads.isEmpty()) {
            threads.pop();
        }

        if (threads.isEmpty()) {
            isRunning = false;
        }
    }

    /**
     * Adds a new objectHandler on top of current stack
     */
    public void newGameState(int newState) {
        threads.push(gameThreadCreator.get(newState));
    }

    /**
     * Ensures that the fps is stable
     *
     * @param time in nanoseconds
     */
    private void verifyFrameRate(long time){
        double currentFps = 1000.0;

        if (time != 0) {
            currentFps = (int) (1000000000.0 / time);
        }

        if (currentFps < FPS) {
            System.out.println("GameError: Currently running at " + currentFps + " fps");
        }
    }

    GameThread getCurrentThread() {
        return threads.peek();
    }

}
