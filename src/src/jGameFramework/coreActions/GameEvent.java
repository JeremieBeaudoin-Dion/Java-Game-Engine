package jGameFramework.coreActions;

import jGameFramework.exceptions.ActionInvocationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * An action event uses Java.Reflexion to call the right method from
 * the right class using getAction().
 *
 * It stores the class value and method value and can have an object
 * sent as parameter or not.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameEvent<E> {

    private final Class<E> classValue;
    private Class parameterClass;
    private Method methodValue;
    private Object setParameter;

    /**
     * Constructor for an action that doesn't need a parameter, or that
     * the parameter is sent when the action is called
     */
    public GameEvent(Class<E> classValue, Method methodValue) {
        this.classValue = classValue;
        this.methodValue = methodValue;
    }

    /**
     * Constructor for an action that needs a parameter
     */
    public GameEvent(Class<E> classValue, Method methodValue, Class parameterClass, Object parameter) {
        this.classValue = classValue;
        this.methodValue = methodValue;
        this.setParameter = parameter;
        this.parameterClass = parameterClass;
    }

    public Class<E> getClassValue() {
        return classValue;
    }

    /**
     * Invokes the method of the desired class
     *
     * @param classMatchingValue: the class that should be affected
     * @param parameterValue: the value of the parameter of the method of the class
     */
    public void doAction(E classMatchingValue, Object parameterValue) {
        if (parameterValue == null) {
            doAction(classMatchingValue);
        } else {
            try {
                methodValue.invoke(classMatchingValue, parameterValue);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionInvocationException(methodValue.toString(), classValue, e.getMessage());
            }
        }
    }

    /**
     * Invokes the method of the desired class
     *
     * @param classMatchingValue: the class that should be affected
     */
    public void doAction(E classMatchingValue) {

        try {
            if (setParameter == null){
                methodValue.invoke(classMatchingValue);
            } else {
                methodValue.invoke(classMatchingValue, parameterClass.cast(setParameter));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ActionInvocationException(methodValue.toString(), classValue, e.getMessage());
        }

    }
}
