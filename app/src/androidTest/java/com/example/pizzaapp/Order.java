package com.example.pizzaapp;

import java.util.ArrayList;

/**
 The Order class contains an array list of Pizzas. Each order has a unique user phone number
 and each order can be added to a Store Order. This class is able to compute the total of each
 order as well
 * @author Abia Mallick, Anna Gutowska
 */
public class Order implements Customizable {
    private String phoneNumber;
    private final ArrayList<Pizza> pizzas = new ArrayList<>();
    private double total;

    /**
     Parameterized Constructor — creates an Order object with the given phone number
     * @param phoneNumber of type String
     */
    public Order(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     The setTotal method sets the phone number of a customer
     * @param phoneNum of type String
     */
    public void setPhoneNumber(String phoneNum) {
        this.phoneNumber = phoneNum;
    }

    /**
     The getTotal method gets the phone number of a customer
     * @return phone number of type String
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     The getTotal method gets the total cost of an order
     * @return total of type Double
     */
    public double getTotal() {
        return this.total;
    }

    /**
     The getPizzas method gets the arraylist of pizzas
     * @return pizzas of type ArrayList<Pizza>
     */
    public ArrayList<Pizza> getPizzas() {
        return this.pizzas;
    }


    /**
     The add method exists to add a pizza to the pizza arraylist per order
     * @param obj of type Object
     * @return type boolean — true if successfully added, false otherwise
     */
    public boolean add(Object obj) {
        if ( obj instanceof Pizza ){
            Pizza newPizza = (Pizza) obj;
            pizzas.add(newPizza);
            totalUpdate();
            return true;
        } else {
            return false;
        }
    }

    /**
     The remove method exists to remove a pizza from the pizza arraylist per order
     * @param obj of type Object
     * @return type boolean — true if successfully removed, false otherwise
     */
    public boolean remove(Object obj) {
        if ( obj instanceof Pizza ) {
            Pizza removePizza = (Pizza) obj;

            if ( pizzas.contains(removePizza) ) {
                pizzas.remove(removePizza);
                totalUpdate();
                return true;
            }
        }
        return false;
    }

    /**
     The totalUpdate method exists to sum up the total of the pizzas in the pizzas arraylist
     */
    public void totalUpdate() {
        total = 0;
        if ( pizzas.size() == 0 ) {
            return;
        }
        for ( int i = 0; i < pizzas.size(); i++ ){
            total += pizzas.get(i).getTotal();
        }
    }


}
