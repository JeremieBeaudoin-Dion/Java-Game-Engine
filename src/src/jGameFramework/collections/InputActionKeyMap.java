package jGameFramework.collections;

import jGameFramework.core.MouseHandler;
import jGameFramework.core.MouseWheelHandler;
import jGameFramework.coreActions.Action;
import jGameFramework.exceptions.UnimplementedKeyException;

import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * An InputActionKeyMap contains 4 different ResourceKeyMap. Each map
 * representing an input from the user: keyDown, keyRelease, mouseDown
 * and mouseRelease.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class InputActionKeyMap {

    private ResourceKeyMap<Integer, Action> keyDownActionMap;
    private ResourceKeyMap<Integer, Action> keyReleaseActionMap;
    private ResourceKeyMap<MouseHandler.MouseClick, Action> mouseDownActionMap;
    private ResourceKeyMap<MouseHandler.MouseClick, Action> mouseReleaseActionMap;
    private ResourceKeyMap<MouseWheelHandler.MouseWheel, Action> mouseWheelActionMap;

    /**
     * Constructor for empty InputActionKeyMap
     */
    public InputActionKeyMap(){
        this.keyDownActionMap = new ResourceKeyMap<>(null);
        this.keyReleaseActionMap = new ResourceKeyMap<>(null);
        this.mouseDownActionMap = new ResourceKeyMap<>(null);
        this.mouseReleaseActionMap = new ResourceKeyMap<>(null);
        this.mouseWheelActionMap = new ResourceKeyMap<>(null);
    }

    public InputActionKeyMap(Map<Integer, Action> keyDownActionMap,
                             Map<Integer, Action> keyReleaseActionMap,
                             Map<MouseHandler.MouseClick, Action> mouseDownActionMap,
                             Map<MouseHandler.MouseClick, Action> mouseReleaseActionMap,
                             Map<MouseWheelHandler.MouseWheel, Action> mouseWheelActionMap) {

        this.keyDownActionMap = new ResourceKeyMap<>(keyDownActionMap);
        this.keyReleaseActionMap = new ResourceKeyMap<>(keyReleaseActionMap);
        this.mouseDownActionMap = new ResourceKeyMap<>(mouseDownActionMap);
        this.mouseReleaseActionMap = new ResourceKeyMap<>(mouseReleaseActionMap);
        this.mouseWheelActionMap = new ResourceKeyMap<>(mouseWheelActionMap);
    }

    public Action getKeyDown(Integer keyCode){
        try {
            return keyDownActionMap.get(keyCode);

        } catch (UnimplementedKeyException e){
            // Redefines the message for it to be more descriptive
            throw new UnimplementedKeyException("The key : " + KeyEvent.getKeyText(keyCode) +
                    " is not implemented as getKeyDown().");
        }
    }

    public Action getKeyRelease(Integer keyCode){
        try {
            return keyReleaseActionMap.get(keyCode);
        } catch (UnimplementedKeyException e){
            throw new UnsupportedOperationException("The key : " + KeyEvent.getKeyText(keyCode) +
                    " is not implemented as getKeyRelease().");
        }

    }

    public Action getMouseDown(MouseHandler.MouseClick mouseButton){
        try {
            return mouseDownActionMap.get(mouseButton);

        } catch (UnimplementedKeyException e){
            throw new UnimplementedKeyException("The click: " + mouseButton +
                    " is not implemented as getMouseDown()");
        }
    }

    public Action getMouseRelease(MouseHandler.MouseClick mouseButton){
        try {
            return mouseReleaseActionMap.get(mouseButton);

        } catch (UnimplementedKeyException e){
            throw new UnimplementedKeyException("The click: " + mouseButton +
                    " is not implemented as getMouseReleased()");
        }
    }

    public Action getMouseWheel(MouseWheelHandler.MouseWheel mouseWheel){
        try {
            return mouseWheelActionMap.get(mouseWheel);

        } catch (UnimplementedKeyException e){
            throw new UnimplementedKeyException("The mouse wheel: " + mouseWheel +
                    " is not implemented as getMouseReleased()");
        }
    }
}
