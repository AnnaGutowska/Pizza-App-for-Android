package com.example.pizzaapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 The storeOrders class provides functions for the StoreOrdersController class to use for getting orders, calculating
 their total, as well as exporting the order into a textfile
 * @author Abia Mallick, Anna Gutowska
 */
public class StoreOrders implements Customizable{

    private final ArrayList<Order> orders = new ArrayList<>();
    private final double SALES_TAX_SUM = 1.06625;

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

    /**
     The calculateSingleOrderTotal calculates the total order for the selected order
     * @param selectedOrder of type Order
     * @return
     */
    private double calculateSingleOrderTotal(Order selectedOrder) {
        double total = selectedOrder.getTotal() * SALES_TAX_SUM;
        return total;
    }

    /**
     The getNumOrders method exists as a getter method to return how many orders there are in the orders arraylist
     * @return number of orders of type Integer
     */
    public int getNumOrders(){
        return orders.size();
    }


}
