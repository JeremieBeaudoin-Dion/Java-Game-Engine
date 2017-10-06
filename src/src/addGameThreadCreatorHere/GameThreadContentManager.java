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
import core.MouseHandler;
import core.ObjectHandler;
import coreActions.Action;
import coreActions.ActionTimed;
import coreActions.GameEvent;
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

    private static final Position PLAYER_LEVEL1_POSITION = new Position(0, 0);

    private ImageLoader imageLoader;

    GameThreadContentManager(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    /**
     * Returns the thread for the start of the game
     */
    GameThread getStartGame(Game gameObject){
        return new GameThread(gameObject, new CameraStill(), getStartGameObjects(), getStartGameKeyMap());
    }

    private TreeSet<PhysicalObject> getStartGameObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new Example_Button());

        return objects;
    }

    private InputActionKeyMap getStartGameKeyMap(){
        HashMap<Integer, Action> actionMap = new HashMap<>();

        try {
            GameEvent<ObjectHandler> clickEvent = new GameEvent<>(ObjectHandler.class, ObjectHandler.class.getMethod("doClick", Position.class));
            ActionTimed leftClickAction = new ActionTimed(clickEvent, 200);

            actionMap.put(MouseHandler.LEFT_CLICK, leftClickAction);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(null, null, actionMap, null);
    }

    /**
     * Returns the thread for the FirstLevel of the game
     */
    GameThread getLevel1(Game gameReference){
        return new GameThread(gameReference, new CameraWithEdges(), getLevel1GameObjects(), getLevel1KeyMap());
    }

    private TreeSet<PhysicalObject> getLevel1GameObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new Example_Image(imageLoader.getImageExample()));

        objects.add(new Example_Circle());

        objects.add(getPlayerObject());

        return objects;
    }

    /**
     * Player object
     */
    private Player getPlayerObject(){
        return new Player(new Dimension(PLAYER_LEVEL1_POSITION.clone(),10,10), new VisionCircle(50),
                new VelocitySquare(2,2));
    }

    private InputActionKeyMap getLevel1KeyMap(){
        HashMap<Integer, Action> actionPushMap = new HashMap<>();
        HashMap<Integer, Action> actionReleaseMap = new HashMap<>();

        try {
            // When pushed, the actions are done
            Action playerGoingUp = new Action(new GameEvent<>(Player.class, Player.class.getMethod("addToMovingOrders", Velocity.Direction.class), Velocity.Direction.UP));
            actionPushMap.put(KeyEvent.VK_UP, playerGoingUp);

            Action playerGoingDown = new Action(new GameEvent<>(Player.class, Player.class.getMethod("addToMovingOrders", Velocity.Direction.class), Velocity.Direction.DOWN));
            actionPushMap.put(KeyEvent.VK_DOWN, playerGoingDown);

            Action playerGoingLeft = new Action(new GameEvent<>(Player.class, Player.class.getMethod("addToMovingOrders", Velocity.Direction.class), Velocity.Direction.LEFT));
            actionPushMap.put(KeyEvent.VK_LEFT, playerGoingLeft);

            Action playerGoingRight = new Action(new GameEvent<>(Player.class, Player.class.getMethod("addToMovingOrders", Velocity.Direction.class), Velocity.Direction.RIGHT));
            actionPushMap.put(KeyEvent.VK_RIGHT, playerGoingRight);

            // When released, the actions are removed.
            Action playerNotUp = new Action(new GameEvent<>(Player.class, Player.class.getMethod("removeToMovingOrders", Velocity.Direction.class), Velocity.Direction.UP));
            actionReleaseMap.put(KeyEvent.VK_UP, playerNotUp);

            Action playerNotDown = new Action(new GameEvent<>(Player.class, Player.class.getMethod("removeToMovingOrders", Velocity.Direction.class), Velocity.Direction.DOWN));
            actionReleaseMap.put(KeyEvent.VK_DOWN, playerNotDown);

            Action playerNotLeft = new Action(new GameEvent<>(Player.class, Player.class.getMethod("removeToMovingOrders", Velocity.Direction.class), Velocity.Direction.LEFT));
            actionReleaseMap.put(KeyEvent.VK_LEFT, playerNotLeft);

            Action playerNotRight = new Action(new GameEvent<>(Player.class, Player.class.getMethod("removeToMovingOrders", Velocity.Direction.class), Velocity.Direction.RIGHT));
            actionReleaseMap.put(KeyEvent.VK_RIGHT, playerNotRight);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(actionPushMap, actionReleaseMap, null, null);
    }

}
