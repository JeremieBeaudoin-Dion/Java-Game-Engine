package jGameFramework.core;

import jGameFramework.core.threadObjects.GameThreadHandler;

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

    private GameThreadHandler gameThreadHandler;

    MouseHandler(GameThreadHandler gameThreadHandler){
        this.gameThreadHandler = gameThreadHandler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gameThreadHandler.getCurrentInputHandler().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gameThreadHandler.getCurrentInputHandler().mouseReleased(e);
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
