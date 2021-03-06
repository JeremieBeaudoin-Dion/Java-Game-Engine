package jGameFramework.exceptions;

/**
 * A type of jGameFramework.exceptions which occurs when a GameActionEvent tries
 * to invoke a method which does not exists or that it doesn't have
 * access to it.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ActionInvocationException extends RuntimeException {

    public ActionInvocationException(String methodName, Class classValue, String message){
        System.out.println("ActionInvocationException: the method " + methodName + " in " + classValue + " cannot " +
                "be invoked. \n Log: " + message);
    }

}
