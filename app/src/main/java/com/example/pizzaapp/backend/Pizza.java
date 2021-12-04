package com.example.pizzaapp.backend;

import java.util.ArrayList;

/**
 The Pizza class is the super class for Deluxe, Hawaiian. and Pepperoni. It contains contains the  It is able
 the arraylist of toppings that can be placed on a pizza as well as setters and getters for the size, monetary amounts,
 as well as adding/removing toppings
 * @author Abia Mallick, Anna Gutowska
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;
    public abstract double price();

    /**
     Parameterized Constructor — The Pizza method creates a Pizza object with the
     given toppings and size of the pizza.
     * @param toppings of type ArrayList<Topping>
     * @param size of type Size
     */
    public Pizza(ArrayList<Topping> toppings, Size size) {

        this.toppings = toppings;
        this.size = size;

    }

    /**
     * This is a default constructor that we call to create a new instance of a pizza.
     */
    public Pizza() {}

    /**
     This abstract getTotal method is used to get the sub-total of all three kinda of pizza types
     * @return total amount of type abstract double
     */
    public abstract double getTotal();

    /**
     The abstract  getSalesTax method gets the sales tax of all three kinda of pizza types
     * @return sales tax of type abstract double
     */
    public abstract double getSalesTax();

    /**
     The abstract getTotalWithTax method gets the full total of all three kinda of pizza types
     * @return the full total (subtotal + tax) of type abstract double
     */
    public abstract double getTotalWithTax();

    /**
     The getSize method gets the size of the pizza
     * @return size of the pizza of type Size
     */
    public Size getSize() {

        return this.size;

    }

    /**
     The setSize method sets the size of the pizza
     * @param size of type Size
     */
    public void setSize(Size size) {

        this.size = size;

    }

    /**
     The addToppings method adds toppings to the arraylist, toppings, defined in the beginning of the class
     * @param topping of type Topping
     */
    public void addToppings(Topping topping) {

        toppings.add(topping);

    }

    /**
     The removeToppings method removes toppings from the arraylist, toppings, defined in the beginning of the class
     * @param topping of type Topping
     */
    public void removeToppings(Topping topping) {

        toppings.remove(topping);

    }

}

