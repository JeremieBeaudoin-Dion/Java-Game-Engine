package core;

import java.awt.event.MouseEvent;

/**
 * The Listener of the Mouse for the Game.
 *
 * Sends the actions to the InputHandler.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class MouseHandler implements java.awt.event.MouseListener {

    public final static int LEFT_CLICK = MouseEvent.BUTTON1;
    public final static int RIGHT_CLICK = MouseEvent.BUTTON3;

    private Game gameReference;

    MouseHandler(Game gameReference){
        this.gameReference = gameReference;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gameReference.getCurrentThread().getInputHandler().mousePressed(e, gameReference);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gameReference.getCurrentThread().getInputHandler().mouseReleased(e, gameReference);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
