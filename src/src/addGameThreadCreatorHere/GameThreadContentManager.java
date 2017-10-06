package addGameThreadCreatorHere;

import addGameObjectsHere.Example_Button;
import addGameObjectsHere.Example_Circle;
import addGameObjectsHere.Example_Image;
import addGameObjectsHere.Player;
import addGameObjectsHere.camera.CameraStill;
import addGameObjectsHere.camera.CameraWithEdges;
import addImageLoaderHere.ImageLoader;
import core.Game;
import core.GameThread;
import coreActions.Action;
import coreActions.ActionEvent;
import coreActions.InputActionKeyMap;
import images.Dimension;
import images.Position;
import physicalObjects.*;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Returns the desired objects when creating a new Thread
 *
 * @author Jérémie Beaudoin-Dion
 */
class GameThreadContentManager {

    private static final Position PLAYER_START_POSITION = new Position(Game.WINDOW_WIDTH/2, Game.WINDOW_HEIGHT/2);
    private static final Position PLAYER_LEVEL1_POSITION = new Position(0, 0);

    private ImageLoader imageLoader;

    GameThreadContentManager(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    /**
     * Returns the thread for the start of the game
     */
    GameThread getStartGame(Game gameObject){
        return new GameThread(gameObject, new CameraStill(), getStartGameObjects(), null);
    }

    /**
     * Objects used for the StartGame() thread
     */
    private TreeSet<PhysicalObject> getStartGameObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new Example_Button());

        return objects;
    }

    GameThread getLevel1(Game gameObject){
        return new GameThread(gameObject, new CameraWithEdges(), getLevel1GameObjects(), getLevel1KeyMap());
    }

    /**
     * Objects used for the Level_1 thread
     */
    private TreeSet<PhysicalObject> getLevel1GameObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new Example_Image(imageLoader.getImageExample()));

        objects.add(new Example_Circle());

        objects.add(getPlayerObject());

        return objects;
    }

    /**
     * Basic player starts at position (0,0)
     */
    private Player getPlayerObject(){
        return new Player(new Dimension(Game.WINDOW_WIDTH/2, Game.WINDOW_HEIGHT/2,
                10,10), new VisionCircle(50),
                new VelocitySquare(2,2));
    }

    private InputActionKeyMap getLevel1KeyMap(){
        HashMap<Integer, Action> actionMap = new HashMap<>();

        try {
            Action playerGoingUp = new Action(new ActionEvent<>(Player.class, Player.class.getMethod("storeAction", Integer.class)));

            actionMap.put(KeyEvent.VK_UP, playerGoingUp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(actionMap, null, null, null);
    }

}
