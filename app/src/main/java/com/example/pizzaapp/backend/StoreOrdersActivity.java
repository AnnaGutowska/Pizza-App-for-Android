package com.example.pizzaapp.backend;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pizzaapp.R;
import android.view.View;
import java.util.ArrayList;

/**
 * The StoreOrdersActivity manages functionalities within the store orders window. This includes
 * cancelling orders as well as viewing each individual order and its total
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private StoreOrders storeOrders;
    private Spinner spinnerPhoneNumber;
    private Button buttonCancelOrder;
    private TextView textViewSum;
    private ListView listViewStoreOrders;
    private ArrayList<String> orders = new ArrayList<>();
    private ArrayList<String> phoneNumbers = new ArrayList<>();
    private ArrayList<String> pizzasString = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> pizzas;
    private final double SALES_TAX_SUM = 1.06625;
    private final static int ZERO = 0;
    private final static int ONE = 1;
    private int TEMP = -1;

    /**
     * The onCreate method sets the values of the fields displayed on the screen. This includes
     * the phone numbers of each order anf the pizzas in each order
     * @param savedInstanceState - bundle which contains the most recent data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders_layout);
        listViewStoreOrders =  findViewById(R.id.listViewStoreOrders);
        buttonCancelOrder = findViewById(R.id.buttonCancelOrder);
        textViewSum = findViewById(R.id.textViewSum);
        spinnerPhoneNumber = findViewById(R.id.spinnerPhoneNumber);
        spinnerMethod();
        storeOrders = new StoreOrders();

        if (!OrderProcessing.placedOrders.isEmpty()) {
            for (Order order : OrderProcessing.placedOrders) {
                storeOrders.add(order);
            }
        }

        setPhoneNumberSpinner();
        setListViewOrders();

    }

    /**
     * The spinnerMethod is used for setting an onItemSelectedListener for the spinner which contains
     * the phone numbers of the orders that have been placed. The method also refreshes the list view
     * to display the pizzas within the current order chosen from the spinner.
     */
    private void spinnerMethod() {

        spinnerPhoneNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int cellnumber = parent.getSelectedItemPosition();
                TEMP = position;
                Order order = OrderProcessing.placedOrders.get(cellnumber);
                order.getPizzas().toString();
                pizzas = new ArrayList<>();

                for (Pizza pizza : order.getPizzas()){
                    pizzas.add(pizza.toString());
                }

                adapter = new ArrayAdapter<>(StoreOrdersActivity.this,
                        android.R.layout.simple_list_item_1, pizzas);

                listViewStoreOrders.setAdapter(adapter);
                textViewSum.setText(String.format("%.2f", thisOrderTotal(order)));

            }

            /**
             * The onNothingSelected is called when no selection is made for the phone number
             * @param parent - The AdapterView where the lack of selection happened
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    /**
     * The onClickCancel method allows for the user to remove a store order and deletes all the
     * information about the order in the database
     * @param view - The cancel button that was clicked
     */
    public void onClickCancelButton(View view){

        try {
            if (phoneNumbers.size() == ONE){
                OrderProcessing.placedOrders.remove(TEMP);
                listViewStoreOrders.setAdapter(null);
                phoneNumbers.remove(TEMP);
                arrayAdapter.notifyDataSetChanged();
                textViewSum.setText("");
                pizzas.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Order cancelled", Toast.LENGTH_SHORT).show();
            } else if (phoneNumbers.size() > ONE) {
                OrderProcessing.placedOrders.remove(TEMP);
                phoneNumbers.remove(TEMP);
                arrayAdapter.notifyDataSetChanged();
                spinnerPhoneNumber.setSelection(ZERO);
                Toast.makeText(this, "Order cancelled", Toast.LENGTH_SHORT).show();
            } else if (phoneNumbers.size() == ZERO ){
                throw new Exception("empty");
            }
        }catch (Exception e){
            Toast.makeText(this,"No order to cancel",Toast.LENGTH_SHORT ).show();
        }

    }

    /**
     * The setPhoneNumberSpinner method adds phone numbers to the phone numbers arraylist. The user
     * can then use the spinner to choose a phone number to view the order
     */
    private void setPhoneNumberSpinner() {

        if (!OrderProcessing.placedOrders.isEmpty()) {
            int storeOrderCount = OrderProcessing.placedOrders.size();

            for (int i = 0; i < storeOrderCount; i++) {
                phoneNumbers.add(storeOrders.getOrder(i).getPhoneNumber());
            }

             arrayAdapter = new ArrayAdapter<>(this,
                     android.R.layout.simple_spinner_item, phoneNumbers);
             spinnerPhoneNumber.setAdapter(arrayAdapter);
        }

    }

    /**
     The setListViewOrders method displays the pizzas within the selected order
     and the corresponding subtotal
     */
    private void setListViewOrders() {

        listViewStoreOrders.setAdapter(null);

        if (!OrderProcessing.placedOrders.isEmpty()) {
            int orderPosition = spinnerPhoneNumber.getSelectedItemPosition();
            Order order = OrderProcessing.placedOrders.get(orderPosition);
            ArrayList<Pizza> pizzaArrayList = order.getPizzas();

            for (int i = 0; i < pizzaArrayList.size(); i++) {
                pizzasString.add(pizzaArrayList.get(i).toString());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(StoreOrdersActivity.this,
                    android.R.layout.simple_list_item_multiple_choice, pizzasString);

            listViewStoreOrders.setAdapter(adapter);
            Order selectedOrder = storeOrders.getOrder(orderPosition);
            textViewSum.setText(String.format("%.2f", thisOrderTotal(selectedOrder)));
        }

    }



    /**
     * The thisOrderTotal method retrieves the total amount of the order to be able to display in the window
     * @param selectedOrder - the order being selected
     * @return the total for the provided order
     */
    private double thisOrderTotal(Order selectedOrder) {

        double total = selectedOrder.getTotal() * SALES_TAX_SUM;
        return total;

    }

}
