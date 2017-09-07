package core;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** 
 * The current MouseLister, to handle mouse events. It stores 
 * the value of isMouseDown in a boolean. MouseHandler can
 * then be called and know if the mouse is pressed or not with 
 * isMouseDown.
 */ 
public class MouseHandler implements MouseListener {

	private boolean isMouseDown = false;

	/** 
     * Assigns the newly created MouseHandler to a Component 
     * @param c Component to get mouse input from 
     */ 
    public MouseHandler(Component c){ 
            c.addMouseListener(this);
    }
	
	/**
	 * Handles mouse events
	 */
	public void mousePressed(MouseEvent arg0) {
		isMouseDown = true;
	}

	public void mouseReleased(MouseEvent arg0) {
		isMouseDown = false;
	}
	
	/**
	 * 
	 * @return Whether the mouse is pressed or not
	 */
	public boolean isMouseDown(){
		return isMouseDown;
	}
	
	// Not used
	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}
}
