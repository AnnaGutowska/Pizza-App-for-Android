package com.example.pizzaapp.backend;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pizzaapp.R;
import android.content.DialogInterface;


import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {

    TextView textCurrentPhone;
    private ArrayList<Pizza> pizzas =  new ArrayList<>();
    private ArrayList<String> pizzasString = new ArrayList<>();
    private ListView listViewCurrent;
    TextView textViewSubtotalCurrent;
    TextView textViewTax;
    TextView textViewOrderTotal;
    Button buttonRemovePizza;
    Order order;
    String phoneNum = "";
    CheckedTextView checkedTextView;
    private static int selectedPizzaToDelete = -1;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_order_layout);
        textCurrentPhone = findViewById(R.id.textCurrentPhone);
        listViewCurrent = findViewById(R.id.listViewCurrent);
        textViewSubtotalCurrent = findViewById(R.id.textViewSubtotalCurrent);
        textViewTax = findViewById(R.id.textViewTax);
        textViewOrderTotal = findViewById(R.id.textViewOrderTotal);
        buttonRemovePizza = findViewById(R.id.buttonRemovePizza);

        if (!OrderProcessing.currentPhoneNumber.isEmpty()) {
            phoneNum = OrderProcessing.currentPhoneNumber.get(0);
            textCurrentPhone.setText(phoneNum);
            order = new Order(phoneNum);
        }

        for (Pizza pizza : OrderProcessing.individualOrder) {
            order.add(pizza);
            pizzas.add(pizza);
        }

        for (int i = 0; i < pizzas.size(); i++) {
            pizzasString.add(OrderProcessing.individualOrder.get(i).toString());
        }

        listViewCurrentMethod();

        if (!OrderProcessing.currentPhoneNumber.isEmpty()){
            setSubtotal();
            setTax();
            setOrderTotal();
        }

    }

    private void listViewCurrentMethod() {
        adapter = new ArrayAdapter<String>(CurrentOrderActivity.this,
                android.R.layout.simple_list_item_multiple_choice, pizzasString);

        listViewCurrent.setAdapter(adapter);

        listViewCurrent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                checkedTextView = ((CheckedTextView) view);
                checkedTextView.setChecked(!checkedTextView.isChecked());
                selectedPizzaToDelete = position;
            }
        });
    }

    public void onClickButtonRemove(View view) {
        try {
            if (textViewSubtotalCurrent.getText().equals(0.00)) {
                Toast.makeText(CurrentOrderActivity.this, "No pizzas to delete.",
                        Toast.LENGTH_LONG).show();
                return;
            }

            Pizza toDelete = OrderProcessing.individualOrder.get(selectedPizzaToDelete);
            order.remove(toDelete);
            OrderProcessing.individualOrder.remove(toDelete);
            pizzasString.remove(toDelete.toString());
            Toast.makeText(CurrentOrderActivity.this, "Pizza removed from order.",
                            Toast.LENGTH_LONG).show();
            setSubtotal();
            setTax();
            setOrderTotal();
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(CurrentOrderActivity.this, "No pizza selected.",
                    Toast.LENGTH_LONG).show();
        }
    }


    private void setOrderTotal() {
        double orderTotal = 0;
        double zero = 0;

        if (order.getPizzas().size() == 0){
            textViewOrderTotal.setText(String.format("%.2f",zero));
        }
        for (Pizza pizza : order.getPizzas()){
            orderTotal += pizza.getTotalWithTax();
        }
        textViewOrderTotal.setText(String.format("%.2f",orderTotal));
    }

    private void setTax() {
        double tax = 0;

        for (Pizza pizza : order.getPizzas()){
            tax += pizza.getSalesTax();
        }
        textViewTax.setText(String.format("%.2f",tax));
    }

    private void setSubtotal() {
        double subtotal = 0;

        for (Pizza pizza : order.getPizzas()){
            subtotal += pizza.getTotal();
        }
        textViewSubtotalCurrent.setText(String.format("%.2f",subtotal));
    }

    public void onPlaceOrderClick(View view) {

        try {
            if (order.getPizzas().isEmpty() || textViewOrderTotal.getText().equals("")) {
                Toast.makeText(CurrentOrderActivity.this, "Cart is empty.",
                        Toast.LENGTH_LONG).show();
                return;
            }
            phoneNum = OrderProcessing.currentPhoneNumber.get(0);
            OrderProcessing.phoneNumberList.add(phoneNum);
            OrderProcessing.placedOrders.add(order);
            OrderProcessing.currentPhoneNumber.clear();
            OrderProcessing.individualOrder.clear();

            textCurrentPhone.setText("");
            listViewCurrent.setAdapter(null);


            Toast.makeText(CurrentOrderActivity.this, "Success. Order placed!",
                    Toast.LENGTH_LONG).show();
            textViewSubtotalCurrent.setText("");
            textViewTax.setText("");
            textViewOrderTotal.setText("");
        } catch (Exception e){
            Toast.makeText(CurrentOrderActivity.this, "Cart is empty.",
                    Toast.LENGTH_LONG).show();
        }
    }



}

