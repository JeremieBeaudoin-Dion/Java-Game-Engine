package coreActions;

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
public class ActionEvent<E> {

    private final Class<E> classValue;
    private Method methodValue;
    private Object setParameter;

    /**
     * Constructor for an action that doesn't need a parameter, or that
     * the parameter is sent when the action is called
     */
    public ActionEvent(Class<E> classValue, Method methodValue) {
        this.classValue = classValue;
        this.methodValue = methodValue;
    }

    /**
     * Constructor for an action that doesn't need a parameter
     */
    public ActionEvent(Class<E> classValue, Method methodValue, Object parameter) {
        this.classValue = classValue;
        this.methodValue = methodValue;
        this.setParameter = parameter;
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
    public void doAction(E classMatchingValue, Object parameterValue) throws InvocationTargetException, IllegalAccessException {
        if (parameterValue == null) {
            doAction(classMatchingValue);
        } else {
            methodValue.invoke(classMatchingValue, parameterValue);
        }
    }

    /**
     * Invokes the method of the desired class
     *
     * @param classMatchingValue: the class that should be affected
     */
    public void doAction(E classMatchingValue) throws InvocationTargetException, IllegalAccessException {
        if (setParameter == null){
            methodValue.invoke(classMatchingValue);
        } else {
            methodValue.invoke(classMatchingValue, setParameter);
        }
    }
}
