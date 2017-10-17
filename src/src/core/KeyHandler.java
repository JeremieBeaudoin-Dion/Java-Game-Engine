package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Jérémie Beaudoin-Dion
 */
class KeyHandler implements KeyListener {

    private Game gameReference;

    KeyHandler(Game gameReference){
        this.gameReference = gameReference;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /*
         * TODO: Implement basic KeyType to send as string
         */
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameReference.getCurrentThread().getInputHandler().keyPressed(e, gameReference);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameReference.getCurrentThread().getInputHandler().keyReleased(e, gameReference);
    }
}
