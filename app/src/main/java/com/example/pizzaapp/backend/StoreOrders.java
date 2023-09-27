package com.example.pizzaapp.backend;

import java.util.ArrayList;

/**
 The storeOrders class provides functions for the StoreOrdersController class to use for getting orders, calculating
 their total, as well as exporting the order into a textfile
 */
public class StoreOrders implements Customizable {

    private final ArrayList<Order> orders = new ArrayList<>();

    /**
     * This is a default constructor that we call to create a new instance of the store orders.
     */
    public StoreOrders(){}

    /**
     The add method exists to add orders to the arraylist defined above, orders
     * @param obj - the order to be added
     * @return true if the order can be added, false if not
     */
    @Override
    public boolean add(Object obj){

        if ( obj instanceof Order ) {
            Order order = (Order) obj;
            orders.add(order);
            return true;
        }
        return false;

    }

    /**
     The remove method exists to remove orders to the arraylist defined above, orders
     * @param obj - the order to be added
     * @return true if the order can be removed, false if not
     */
    @Override
    public boolean remove(Object obj){

        if ( obj instanceof Order ) {
            Order order = (Order) obj;
            if( orders.contains(order) ) {
                orders.remove(order);
                return true;
            }
        }
        return false;

    }

    /**
     The getOrder method returns the specified order in the arraylist defined above, orders
     * @param i - the position of the order in the orders array
     * @return the order at the given index i
     */
    public Order getOrder(int i){

        return orders.get(i);

    }


}
