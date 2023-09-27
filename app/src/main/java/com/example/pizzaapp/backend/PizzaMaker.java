package com.example.pizzaapp.backend;


/**
 The PizzaMaker class exists to create an instance of subclasses based on the chosen type of pizza
 */
public class PizzaMaker {

    /**
     The createPizza method exists to create a new instance of either a deluxe/pepperoni/hawaiian pizza based on which
     pizza the user chooses.
     @param flavor - the pizza type chosen by the user
     @return Pizza of the provided flavor
     */
    public static Pizza createPizza(String flavor) {

        if ( flavor.equals("deluxe") ) {
            return new Deluxe();
        } else if ( flavor.equals("pepperoni") ){
            return new Pepperoni();
        } else if ( flavor.equals("hawaiian") ){
            return new Hawaiian();
        } else {
            return null;
        }

    }

}