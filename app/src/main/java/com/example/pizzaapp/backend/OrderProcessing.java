package com.example.pizzaapp.backend;

import java.util.ArrayList;

/**
 The OrderProcessing class creates new arraylists for the user's current phone number, already existent user's phone
 numbers, the pizzas in the current order, and the placed orders.
 */
public class OrderProcessing {

    protected static ArrayList<String> currentPhoneNumber = new ArrayList<>();
    protected static ArrayList<String> phoneNumberList = new ArrayList<>();
    protected static ArrayList<Pizza> individualOrder = new ArrayList<>();
    protected static ArrayList<Order> placedOrders = new ArrayList<>();

}
