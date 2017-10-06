package coreActions;

/**
 * An action which takes account of the time
 * between each of the calls.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class TimedAction extends Action {

    private long timeOfLastCall;
    private long timeBetweenEachEvents;

    /**
     * Constructor
     *
     * @param actionEvent: the action that will be returned if isDoable()
     * @param timeBetweenEachEvents: in milliseconds
     */
    public TimedAction(ActionEvent actionEvent, long timeBetweenEachEvents){
        super(actionEvent);

        timeOfLastCall = System.currentTimeMillis();
        this.timeBetweenEachEvents = timeBetweenEachEvents;
    }

    @Override
    public boolean isDoable() {
        return System.currentTimeMillis() - timeOfLastCall >= timeBetweenEachEvents;
    }

}
