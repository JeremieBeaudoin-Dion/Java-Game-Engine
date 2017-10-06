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

    private Action codeIsInMap(Integer code, HashMap<Integer, Action> map){
        if (map.containsKey(code)){
            return map.get(code);
        }

        throw new UnsupportedOperationException("The key : " + KeyEvent.getKeyText(code) + " is not implemented.");
    }

    public Action getKeyDown(Integer keyCode){
        return codeIsInMap(keyCode, keyDownActionMap);
    }

    public Action getKeyRelease(Integer keyCode){
        return codeIsInMap(keyCode, keyReleaseActionMap);
    }

    public Action getMouseDown(Integer mouseButton){
        return codeIsInMap(mouseButton, mouseDownActionMap);
    }

    public Action getMouseRelease(Integer mouseButton){
        return codeIsInMap(mouseButton, mouseReleaseActionMap);
    }
}
