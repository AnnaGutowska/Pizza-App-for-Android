package com.example.pizzaapp.backend;

/**
 The Deluxe class is a child class of the parent, Pizza class. This class contains the functionalities for a deluxe
 pizza which include setting the sub-total, tax amount, and final total
 */
public class Deluxe extends Pizza  {

    private static final double BASE_PRICE = 12.99;
    private static final int SMALL = 0;
    private static final int MEDIUM = 1;
    private static final int LARGE = 2;
    private static final double SIZE_INCREASE = 2.00;
    private static final double EXTRA_TOPPINGS_PRICE = 1.49;
    private static final int NONE = 0;
    private static final int DEFAULT_NUM_OF_TOPPINGS = 5;
    private static final double SALES_TAX = 0.06625;
    private static final double SALES_TAX_OF_TOTAL = 1.06625;
    private static final double invalidToppingTotal = -1.00;
    private static final double extraToppingNumber = 2;
    private static final double ZERO = 0.00;
    public double total = 0;
    public double subtotal = 0;
    public double salesTax = 0;


    /**
     * This is a default constructor that we call in the PizzaMaker class to
     * create a new instance of a Deluxe pizza.
     */
    public Deluxe() {}

    /**
     The setTotal method sets the sub-total of the deluxe pizzas
     * @param total of type double
     */
    public void setTotal(double total) {

        this.total = total;

    }

    /**
     The getTotal method gets the sub-total of the deluxe pizzas
     * @return total amount of type double
     */
    public double getTotal() {

        return this.total;

    }

    /**
     The setSalesTax method sets the sales tax which is defined in the beginning of the class
     (6.625%) of the deluxe pizzas
     * @param total of type double
     */
    public void setSalesTax(double total) {

        this.salesTax = SALES_TAX * total;

    }

    /**
     The getSalesTax method gets the sales tax which is defined in the beginning of the class
     (6.625%) of the deluxe pizzas
     * @return sales tax of type double
     */
    public double getSalesTax() {

        return this.salesTax;

    }

    /**
     The setTotalWithTax method sets the full total of the deluxe pizzas
     * @param subtotal of type double which is the the full total (subtotal + tax)
     */
    public void setTotalWithTax(double subtotal){

        this.subtotal = (SALES_TAX_OF_TOTAL * subtotal);

    }

    /**
     The getTotalWithTax method gets the full total of the deluxe pizzas
     * @return the full total (subtotal + tax) of type double
     */
    public double getTotalWithTax(){

        return this.subtotal;

    }

    /**
     The price method overrides the method in the Pizza class and calculates the price of deluxe pizzas using the
     size and amount of toppings. Deluxe pizza includes 5 toppings.
     * @return price of type double
     */
    @Override
    public double price() {

        int numberOfExcessToppings = NONE;

        if (toppings.size() > DEFAULT_NUM_OF_TOPPINGS) {
            numberOfExcessToppings = toppings.size() - DEFAULT_NUM_OF_TOPPINGS;
            if (numberOfExcessToppings > extraToppingNumber){
                return invalidToppingTotal;
            }
        }

        double total = ZERO;

        if (this.getSize() == Size.small) {
            total = SMALL * SIZE_INCREASE;
        } else if (this.getSize() == Size.medium) {
            total = MEDIUM * SIZE_INCREASE;
        } else if (this.getSize() == Size.large) {
            total = LARGE * SIZE_INCREASE;
        }

        total += (BASE_PRICE + (EXTRA_TOPPINGS_PRICE * numberOfExcessToppings));
        setSalesTax(total);
        setTotal(total);
        setTotalWithTax(total);
        return total;

    }


    /**
     The toString method returns a String representation of the deluxe pizza with its price, size and toppings
     @return a string comprising of all the information about a deluxe pizza. The string is in the form of
     Pizza type,ingredients,size,$price
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Deluxe pizza,");
        String price = String.format("%,.2f",(getTotal()));

        for (int i = 0; i < toppings.size() ; i++){
            string.append(toppings.get(i).toString());
            string.append(",");
        }

        string.append(getSize().toString() + ",$");
        string.append(price);
        return string.toString();
    }

}
