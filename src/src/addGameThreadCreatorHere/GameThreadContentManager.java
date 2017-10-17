package addGameThreadCreatorHere;

import addGameObjectsHere.Example_Button;
import addGameObjectsHere.Example_Wall;
import addGameObjectsHere.Example_Image;
import addGameObjectsHere.Player;
import addGameObjectsHere.camera.CameraFollowingPlayer;
import addGameObjectsHere.camera.CameraStill;
import addImageLoaderHere.ImageLoader;
import jGameFramework.core.GameThread;
import jGameFramework.core.MouseHandler;
import jGameFramework.core.ObjectHandler;
import jGameFramework.coreActions.*;
import jGameFramework.physicalObjects.Position;
import jGameFramework.physicalObjects.*;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Returns the desired objects when creating a new Thread
 *
 * @author Jérémie Beaudoin-Dion
 */
class GameThreadContentManager {

    private ImageLoader imageLoader;

    private Player playerLevel1;

    GameThreadContentManager(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        playerLevel1 = new Player();
    }

    /**
     * Returns the thread for the start of the game
     */
    GameThread getStartGame(){
        return new GameThread(GameThreadCreator.GAME_MENU, new CameraStill(), getStartGameObjects(), getStartGameKeyMap());
    }

    private TreeSet<PhysicalObject> getStartGameObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new Example_Button());

        return objects;
    }

    InputActionKeyMap getStartGameKeyMap(){
        HashMap<Integer, Action> mouseActionMap = new HashMap<>();
        HashMap<Integer, Action> actionReleaseMap = new HashMap<>();

        try {
            GameEvent<ObjectHandler> clickEvent = new GameEvent<>(ObjectHandler.class, ObjectHandler.class.getMethod("doClick", Position.class));
            ActionTimed leftClickAction = new ActionTimed(clickEvent, 200);

            mouseActionMap.put(MouseHandler.LEFT_CLICK, leftClickAction);

            // when L is released, load the game
            actionReleaseMap.put(KeyEvent.VK_L, new Action(new GameThreadEventLoad("savedGame.s")));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(null, actionReleaseMap, mouseActionMap, null);
    }

    /**
     * Returns the thread for the FirstLevel of the game
     */
    GameThread getLevel1(){
        return new GameThread(GameThreadCreator.GAME_LEVEL_1, new CameraFollowingPlayer(playerLevel1),
                getLevel1GameObjects(), getLevel1KeyMap());
    }

    private TreeSet<PhysicalObject> getLevel1GameObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new Example_Image(imageLoader.getImageExample()));

        objects.add(new Example_Wall());

        objects.add(getPlayerObject());

        return objects;
    }

    /**
     * Player object
     */
    private Player getPlayerObject(){
        return playerLevel1;
    }

    InputActionKeyMap getLevel1KeyMap(){
        HashMap<Integer, Action> actionPushMap = new HashMap<>();
        HashMap<Integer, Action> actionReleaseMap = new HashMap<>();
        HashMap<Integer, Action> mouseClickMap = new HashMap<>();

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

            // when P is released, save the game
            actionReleaseMap.put(KeyEvent.VK_P, new Action(new GameThreadEventSave("savedGame.s")));

            // Mouse actions
            GameEvent<ObjectHandler> clickEvent = new GameEvent<>(ObjectHandler.class, ObjectHandler.class.getMethod("doClick", Position.class));
            ActionTimed leftClickAction = new ActionTimed(clickEvent, 200);

            mouseClickMap.put(MouseHandler.LEFT_CLICK, leftClickAction);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(actionPushMap, actionReleaseMap, mouseClickMap, null);
    }

}
