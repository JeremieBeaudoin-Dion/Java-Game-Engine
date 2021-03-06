package jGameFramework.core.threadObjects;

import jGameFramework.collections.InputActionKeyMap;
import jGameFramework.core.MouseHandler;
import jGameFramework.core.MouseWheelHandler;
import jGameFramework.physicalObjects.PlaneDimension;
import jGameFramework.physicalObjects.Position;

import java.awt.event.KeyEvent;

/**
 * The InputHandler class handles input from the player and converts it to
 * actionOfGameHere that the actionHandler can handle.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class InputHandler {

	private ActionHandler actionHandler;

	// Stores what to do with KeyEvents and MouseEvents
	private InputActionKeyMap inputActionKeyMap;

	/**
	 * Constructor
	 *
	 * @param inputActionKeyMap: The binds between each KeyEvent and
	 */
	InputHandler(InputActionKeyMap inputActionKeyMap, ActionHandler actionHandler) {
		this.actionHandler = actionHandler;

		if (inputActionKeyMap == null){
			this.inputActionKeyMap = new InputActionKeyMap();
		} else {
			this.inputActionKeyMap = inputActionKeyMap;
		}

	}

	/**
	 * Called when a key is pressed by the KeyListener
	 * @param event KeyEvent sent by the component
	 */
	public void keyPressed(KeyEvent event) {
		actionHandler.doAction(inputActionKeyMap.getKeyDown(event.getKeyCode()));
	}

	/**
	 * Called when a key is released by the KeyListener
	 * @param event KeyEvent sent by the component
	 */
	public void keyReleased(KeyEvent event) {
		actionHandler.doAction(inputActionKeyMap.getKeyRelease(event.getKeyCode()));
	}

	/**
	 * Called when a mouse button is pressed
	 */
	public void mousePressed(MouseHandler.MouseClick mouseClick, Position mousePosition) {
		actionHandler.doAction(inputActionKeyMap.getMouseDown(mouseClick), mousePosition);
	}

	public void mouseReleased(MouseHandler.MouseClick mouseClick, Position mousePosition) {
		actionHandler.doAction(inputActionKeyMap.getMouseRelease(mouseClick), mousePosition);
	}

    /**
     * Called when the wheel is scrolled
     */
    public void mouseWheelMoved(MouseWheelHandler.MouseWheel wheelValue, PlaneDimension dimension) {
        actionHandler.doAction(inputActionKeyMap.getMouseWheel(wheelValue), dimension);
    }
}
