package coreActions;

/**
 * An Action is an instance send by the InputHandler to the ActionHandler. It represents an input
 * from the user and will return an ActionEvent that will help the ActionHandler to easily
 * influence the game with that action.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Action {

    private ActionEvent actionEvent;

    public Action(ActionEvent actionEvent){
        this.actionEvent = actionEvent;
    }

    public boolean isDoable(){
        return true;
    }

    public ActionEvent getAction(){
        return actionEvent;
    }

}
