package actionOfGameHere.actions;

import core.ObjectHandler;
import coreActions.ActionEvent;
import images.Position;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class MouseLeftClickAction implements MouseClickAction {

    private static final long TIME_BETWEEN_EACH_EVENTS = 400;
    private static long TIME_LAST_CALLED = System.currentTimeMillis();

    private Position positionOnScreen;

    /**
     * Constructor
     */
    public MouseLeftClickAction(Position positionOnScreen) {
        this.positionOnScreen = positionOnScreen;
    }

    @Override
    public boolean isDoable() {
        return System.currentTimeMillis() - TIME_LAST_CALLED >= TIME_BETWEEN_EACH_EVENTS;
    }

    @Override
    public ActionEvent doAction() throws NoSuchMethodException {
        TIME_LAST_CALLED = System.currentTimeMillis();

        return new ActionEvent<ObjectHandler>(ObjectHandler.class,
                ObjectHandler.class.getMethod("doClick", Position.class), positionOnScreen);
    }
}
