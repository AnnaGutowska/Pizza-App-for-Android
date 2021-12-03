package com.example.pizzaapp.backend;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pizzaapp.R;
import com.example.pizzaapp.backend.StoreOrders;
import android.view.View;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;

public class StoreOrdersActivity extends AppCompatActivity {

    private StoreOrders storeOrders;
    Spinner spinnerPhoneNumber;
    Button buttonCancelOrder;
    TextView textViewSum;
    ListView listViewStoreOrders;
    private final double SALES_TAX_SUM = 1.06625;
    private final static int ZERO = 0;
    private ArrayList<String> orders = new ArrayList<>();
    private ArrayList<String> phoneNumbers = new ArrayList<>();
    private ArrayList<String> pizzasString = new ArrayList<>();
    CheckedTextView checkedTextView;
    int selectedOrderToDelete;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> arrayAdapter;
    int ind = -1;
    ArrayList<String> pizzas;


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

    private void spinnerMethod() {
        spinnerPhoneNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int cellnumber = parent.getSelectedItemPosition();
                ind = position;
                Order order = OrderProcessing.placedOrders.get(cellnumber);
                order.getPizzas().toString();
                pizzas = new ArrayList<>();

                for (Pizza pizza : order.getPizzas()){
                    pizzas.add(pizza.toString());
                }
                adapter = new ArrayAdapter<String>(StoreOrdersActivity.this,
                        android.R.layout.simple_list_item_1, pizzas);

                listViewStoreOrders.setAdapter(adapter);
                textViewSum.setText(String.format("%.2f", thisOrderTotal(order)));

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void onClickCancelButton(View view){
        try {
            if (phoneNumbers.size() == 1){
                OrderProcessing.placedOrders.remove(ind);
                listViewStoreOrders.setAdapter(null);
                phoneNumbers.remove(ind);
                arrayAdapter.notifyDataSetChanged();
                textViewSum.setText("");
                pizzas.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Order cancelled", Toast.LENGTH_SHORT).show();
            } else if (phoneNumbers.size() > 1) {
                OrderProcessing.placedOrders.remove(ind);
                phoneNumbers.remove(ind);
                arrayAdapter.notifyDataSetChanged();
                spinnerPhoneNumber.setSelection(0);
                Toast.makeText(this, "Order cancelled", Toast.LENGTH_SHORT).show();
            } else if (phoneNumbers.size() == 0 ){
                throw new Exception("empty");
            }
        }catch (Exception e){
            Toast.makeText(this,"No order to cancel",Toast.LENGTH_SHORT ).show();
        }
    }

    private void setPhoneNumberSpinner() {
        if (!OrderProcessing.placedOrders.isEmpty()) {
            int storeOrderCount = OrderProcessing.placedOrders.size();

            for (int i = 0; i < storeOrderCount; i++) {
                phoneNumbers.add(storeOrders.getOrder(i).getPhoneNumber());
            }

             arrayAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, phoneNumbers);
             spinnerPhoneNumber.setAdapter(arrayAdapter);
        }
    }

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
     * @param selectedOrder
     * @return the total for the provided order
     */
    private double thisOrderTotal(Order selectedOrder) {
        double total = selectedOrder.getTotal() * SALES_TAX_SUM;
        return total;
    }
}
