package com.example.pizzaapp.backend;

/**
 The Customizable class exists to contains the necessary "add" and "remove" for classes that extend this Customizable class
 * @author Abia Mallick, Anna Gutowska
 */
public interface Customizable {

    /**
     The add method for the Customizable interface for other classes to override
     * @param obj the object to add
     * @return true if successful, false otherwise
     */
    boolean add(Object obj);

    /**
     The remove method for the Customizable interface for other classes to override
     * @param obj the object to remove
     * @return true if successful, false otherwise
     */
    boolean remove(Object obj);

}
