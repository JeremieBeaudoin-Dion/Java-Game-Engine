package coreActions;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * An InputActionKeyMap stores keys binded with actions
 * of the game. This is used by the InputHandler in order
 * to send the right action to the action handler when
 * a key is pressed
 *
 * @author Jérémie Beaudoin-Dion
 */
public class InputActionKeyMap {


    public static final int MOUSE_BUTTON_RIGHT = MouseEvent.BUTTON3;
    public static final int MOUSE_BUTTON_LEFT = MouseEvent.BUTTON1;

    private HashMap<Integer, Action> keyDownActionMap;
    private HashMap<Integer, Action> keyReleaseActionMap;
    private HashMap<Integer, Action> mouseDownActionMap;
    private HashMap<Integer, Action> mouseReleaseActionMap;

    public InputActionKeyMap(HashMap<Integer, Action> keyDownActionMap, HashMap<Integer, Action> keyReleaseActionMap,
                             HashMap<Integer, Action> mouseDownActionMap, HashMap<Integer, Action> mouseReleaseActionMap) {

        if (keyDownActionMap != null){
            this.keyDownActionMap = keyDownActionMap;
        } else {
            this.keyDownActionMap = new HashMap<>();
        }
        if (keyReleaseActionMap != null){
            this.keyReleaseActionMap = keyReleaseActionMap;
        } else {
            this.keyReleaseActionMap = new HashMap<>();
        }
        if (mouseDownActionMap != null){
            this.mouseDownActionMap = mouseDownActionMap;
        } else {
            this.mouseDownActionMap = new HashMap<>();
        }
        if (mouseReleaseActionMap != null){
            this.mouseReleaseActionMap = mouseReleaseActionMap;
        } else {
            this.mouseReleaseActionMap = new HashMap<>();
        }
    }

    public Action getKeyDown(Integer keyCode){
        if (keyDownActionMap.containsKey(keyCode)){
            return keyDownActionMap.get(keyCode);
        }

        throw new UnsupportedOperationException("The key : " + KeyEvent.getKeyText(keyCode) + " is not implemented as getKeyDown().");
    }

    public Action getKeyRelease(Integer keyCode){
        if (keyReleaseActionMap.containsKey(keyCode)){
            return keyReleaseActionMap.get(keyCode);
        }

        throw new UnsupportedOperationException("The key : " + KeyEvent.getKeyText(keyCode) + " is not implemented as getKeyRelease().");
    }

    public Action getMouseDown(Integer mouseButton){
        if (mouseDownActionMap.containsKey(mouseButton)){
            return mouseDownActionMap.get(mouseButton);
        }

        throw new UnsupportedOperationException("The key : " + KeyEvent.getKeyText(mouseButton) + " is not implemented as getMouseDown().");
    }

    public Action getMouseRelease(Integer mouseButton){
        if (mouseReleaseActionMap.containsKey(mouseButton)){
            return mouseReleaseActionMap.get(mouseButton);
        }

        throw new UnsupportedOperationException("The key : " + KeyEvent.getKeyText(mouseButton) + " is not implemented as getMouseRelease().");
    }
}
