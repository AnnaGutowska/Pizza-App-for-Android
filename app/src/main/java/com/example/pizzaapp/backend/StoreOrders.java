package com.example.pizzaapp.backend;

import java.util.ArrayList;

/**
 The storeOrders class provides functions for the StoreOrdersController class to use for getting orders, calculating
 their total, as well as exporting the order into a textfile
 * @author Abia Mallick, Anna Gutowska
 */
public class StoreOrders implements Customizable {

    private final ArrayList<Order> orders = new ArrayList<>();

    public StoreOrders(){}

    /**
     The add method exists to add orders to the arraylist defined above, orders
     * @param obj of type Object
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
     * @param obj of type Object
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
     * @param i of type Integer
     */
    public Order getOrder(int i){

        return orders.get(i);

    }


}
