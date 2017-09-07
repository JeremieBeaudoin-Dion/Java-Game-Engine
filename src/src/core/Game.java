package core;

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

    public final static String GAME_NAME = "Test";

    public final static int GAMESTATE_MAINMENU = 0;

    public final static int WINDOW_WIDTH = 900;
    public final static int WINDOW_HEIGHT = 600;

    // Variables for the game
    public static boolean isRunning = true;
    private static Timer timer;
    private static final long FPS = 60;

    private Stack<Integer> gameState;

    // Instances of the core elements of the game
    private ImageHandler imageHandler;
    private InputHandler inputHandler;

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
        }

        timer = new Timer();
        timer.schedule(game, 0, 1000 / Game.FPS);

    }

    /**
     * This method will set up everything needed for the game to run
     * @throws IOException if an image is missing from the directory
     */
    private Game() throws IOException, UnsupportedAudioFileException {
        gameState = new Stack<>();

        imageHandler = new ImageHandler();
        inputHandler = new InputHandler(imageHandler);

        // Current GameState
        gameState.push(GAMESTATE_MAINMENU);
    }

    /**
     * This method starts the game and runs it in a loop
     * It updates all CoreHandler objects
     */
    @Override
    public void run() {

        // Gets current time for fps
        long time = System.currentTimeMillis();

        update();

        // Handles player input
        handleInput(inputHandler.getInput());

        if (!isRunning) {
            timer.cancel();
            System.exit(0);
        }

        verifyFrameRate(System.currentTimeMillis() - time);
    }

    /**
     * Updates the game every frame
     */
    private void update() {

    }

    /**
     * Handles Actions and sends them to the correct game controller
     */
    private void handleInput(String action) {

    }

    private void quitCurrentState() {
        gameState.pop();

        if (gameState.isEmpty()) {
            gameState.push(GAMESTATE_MAINMENU);
        }
    }

    private void reStartGame() {
        gameState = new Stack<>();

        gameState.push(GAMESTATE_MAINMENU);
    }

    /**
     * Ensures that the fps is stable
     *
     * @param time in milliseconds
     */
    private void verifyFrameRate(long time){

        long currentFPS = 0;

        if (time != 0) {
            currentFPS = 1000 / (time);
        }

        // System.out.println("Current FPS: " + currentFPS);

        if (currentFPS < FPS) {
            System.out.println("GameError: Running under " + FPS + " fps");
        }
    }

}
