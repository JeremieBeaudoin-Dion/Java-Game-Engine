package core;

import coreActions.InputActionKeyMap;
import physicalObjects.Position;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The InputHandler class handles input from the player and converts it to
 * actionOfGameHere that the actionHandler can handle.
 * 
 * @author Jérémie Beaudoin-Dion
 */
class InputHandler {

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
			this.inputActionKeyMap = new InputActionKeyMap(null, null,null,null);
		} else {
			this.inputActionKeyMap = inputActionKeyMap;
		}

	}

	public InputActionKeyMap getInputActionKeyMap() {
		return inputActionKeyMap;
	}

	/**
	 * Called when a key is pressed by the KeyListener
	 * @param event KeyEvent sent by the component
	 */
	void keyPressed(KeyEvent event, Game gameReference) {
		actionHandler.doAction(gameReference, inputActionKeyMap.getKeyDown(event.getKeyCode()));
	}

	/**
	 * Called when a key is released by the KeyListener
	 * @param event KeyEvent sent by the component
	 */
	void keyReleased(KeyEvent event, Game gameReference) {
		actionHandler.doAction(gameReference, inputActionKeyMap.getKeyRelease(event.getKeyCode()));
	}

	void mousePressed(MouseEvent mouseEvent, Game gameReference) {
		actionHandler.doAction(gameReference, inputActionKeyMap.getMouseDown(mouseEvent.getButton()),
                    getTrueMousePosition(mouseEvent));
	}

	void mouseReleased(MouseEvent mouseEvent, Game gameReference) {
		actionHandler.doAction(gameReference, inputActionKeyMap.getMouseDown(mouseEvent.getButton()),
                    getTrueMousePosition(mouseEvent));
	}

	private Position getTrueMousePosition(MouseEvent mouseEvent){
	    Position positionAccordingToScreen = new Position(mouseEvent.getX(), mouseEvent.getY());

	    return positionAccordingToScreen.add(new Position(-ImageHandler.INSETS.left, -ImageHandler.INSETS.top));
    }
}
