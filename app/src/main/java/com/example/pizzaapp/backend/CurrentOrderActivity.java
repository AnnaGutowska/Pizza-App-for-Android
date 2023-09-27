package com.example.pizzaapp.backend;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import com.example.pizzaapp.R;
import java.util.ArrayList;

/**
 The CurrentOrderActivity manages functionalities within the current order window. This includes
 viewing the pizzas within the order as well as the total. The user is allowed to remove pizzas
 and finally place their order
 */
public class CurrentOrderActivity extends AppCompatActivity {

    private TextView textCurrentPhone;
    private ListView listViewCurrent;
    private TextView textViewSubtotalCurrent;
    private TextView textViewTax;
    private TextView textViewOrderTotal;
    private Button buttonRemovePizza;
    private CheckedTextView checkedTextView;
    private ArrayAdapter<String> adapter;
    private static int selectedPizzaToDelete = -1;
    private static final double ZERO = 0.00;
    private static final int ZERO_INT = 0;
    private ArrayList<Pizza> pizzas =  new ArrayList<>();
    private ArrayList<String> pizzasString = new ArrayList<>();
    protected Order order;
    private String phoneNum = "";

    /**
     The onCreate method creates the initial view of the app detailing the order information
     * @param savedInstanceState - bundle which contains the most recent data
     */
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
            phoneNum = OrderProcessing.currentPhoneNumber.get(ZERO_INT);
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

    /**
     The onBackPressed method is used for setting the MainActivity fields when returning
     from this current order activity; detects whether the order was placed and if a new one can
     be started
     */
    @Override
    public void onBackPressed() {

        NavUtils.navigateUpFromSameTask(this);
        super.onBackPressed();

    }

    /**
     The listViewCurrentMethod method is used to list the pizza description in String form
     of each pizza in the current order in the list view
     */
    private void listViewCurrentMethod() {

        adapter = new ArrayAdapter<>(CurrentOrderActivity.this,
                android.R.layout.simple_list_item_multiple_choice, pizzasString);

        listViewCurrent.setAdapter(adapter);

        listViewCurrent.setOnItemClickListener((parent, view, position, id) -> {
            checkedTextView = ((CheckedTextView) view);
            checkedTextView.setChecked(!checkedTextView.isChecked());
            selectedPizzaToDelete = position;
        });

    }

    /**
     * The onClickButtonRemove method allows for the user to remove a pizza from their order
     * @param view - the Remove Pizza button that was clicked
     */
    public void onClickButtonRemove(View view) {

        try {
            if (textViewSubtotalCurrent.getText().equals(ZERO)) {
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

    /**
     The setOrderTotal method retrieves the order total before tax and sets the
     correct text view to display the amount
     */
    private void setOrderTotal() {

        double orderTotal = ZERO;

        if (order.getPizzas().size() == ZERO_INT){
            textViewOrderTotal.setText(String.format("%.2f",ZERO));
        }

        for (Pizza pizza : order.getPizzas()){
            orderTotal += pizza.getTotalWithTax();
        }

        textViewOrderTotal.setText(String.format("%.2f",orderTotal));

    }

    /**
     The setTax method retrieves the tax of the order and sets the
     correct text view to display the amount
     */
    private void setTax() {
        double tax = ZERO;

        for (Pizza pizza : order.getPizzas()){
            tax += pizza.getSalesTax();
        }
        textViewTax.setText(String.format("%.2f",tax));
    }

    /**
     The setSubTotal method retrieves the full total of the order including tax and sets the
     correct text view to display the amount
     */
    private void setSubtotal() {

        double subtotal = ZERO_INT;

        for (Pizza pizza : order.getPizzas()){
            subtotal += pizza.getTotal();
        }
        textViewSubtotalCurrent.setText(String.format("%.2f",subtotal));
    }


    /**
     *  The onPlaceOrderClick method allows for the user to place their order. It takes into account
     *  errors such as an empty cart
     * @param view - the Place Order button that was clicked
     */
    public void onPlaceOrderClick(View view) {

        try {
            if (order.getPizzas().isEmpty() || textViewOrderTotal.getText().equals("")) {
                Toast.makeText(CurrentOrderActivity.this, "Cart is empty.",
                        Toast.LENGTH_LONG).show();
                return;
            }
            phoneNum = OrderProcessing.currentPhoneNumber.get(ZERO_INT);
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

