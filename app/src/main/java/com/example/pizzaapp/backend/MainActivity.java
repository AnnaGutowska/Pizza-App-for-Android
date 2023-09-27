package com.example.pizzaapp.backend;

import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pizzaapp.R;

/**
 * The MainActivity manages functionalities within the main window which include checking the validation
 *  of the phone number and opening various different windows to be able to customize pizzas and viewing
 *  orders
 */
public class MainActivity extends AppCompatActivity {

    private ImageButton buttonDeluxe;
    private ImageButton buttonHawaiian;
    private ImageButton buttonPepperoni;
    private ImageButton buttonCurrentOrder;
    private ImageButton buttonStoreOrders;
    private EditText textPhoneNumber;
    public String phoneNumber = "";
    private static final int ZERO = 0;
    private static final int TEN = 10;

    /**
     The onCreate method creates the view of the app detailing what the user can do. The method
     also checks thr validity of the user's phone number and whether they have concluded their order.
     * @param savedInstanceState - bundle which contains the most recent data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCurrentOrder = findViewById(R.id.imageButtonCurrentOrder);
        buttonStoreOrders = findViewById(R.id.imageButtonStoreOrders);
        buttonDeluxe = findViewById(R.id.imageButtonDeluxe);
        textPhoneNumber = findViewById(R.id.textPhoneNumber);

        if (!OrderProcessing.currentPhoneNumber.isEmpty()) {
            phoneNumber = OrderProcessing.currentPhoneNumber.get(ZERO);
            textPhoneNumber.setText(phoneNumber);
            textPhoneNumber.setFocusable(false);
        } else {
            textPhoneNumber.setFocusable(true);
        }

    }

    /**
     The onBackPressed method is used for setting the MainActivity fields when returning
     from a different activity. It detects whether the current order was placed and if a new one can
     be started
     */
    @Override
    public void onBackPressed(){

        super.onBackPressed();

        if (!OrderProcessing.individualOrder.isEmpty()){
            textPhoneNumber.setFocusable(false);
            textPhoneNumber.setEnabled(false);
            textPhoneNumber.setClickable(false);
            textPhoneNumber.setFocusableInTouchMode(false);
        } else {
            textPhoneNumber.setText("");
            textPhoneNumber.setFocusable(true);
            textPhoneNumber.setEnabled(true);
            textPhoneNumber.setClickable(true);
            textPhoneNumber.setFocusableInTouchMode(true);
        }

    }


    /**
     The onClickDeluxe method allows for the user to click on the Deluxe pizza to open the pizza
     customization only once they input their valid phone number.
     @param view - the Deluxe pizza button that was clicked
     */
    public void onClickDeluxe(View view) {

        if (phoneNumberHandler() == true) {
            if (!orderAlert()){
                return;
            }
            OrderProcessing.currentPhoneNumber.add(phoneNumber);
            textPhoneNumber.setFocusable(false);
            Intent intent = new Intent(this, PizzaCustomizationActivity.class);
            intent.putExtra("message", "Deluxe Pizza");
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Enter phone number and try again", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     The onClickHawaiian method allows for the user to click on the Hawaiian pizza to open the pizza
     customization only once they input their valid phone number.
     @param view - the Hawaiian pizza button that was clicked
     */
    public void onClickHawaiian(View view){

        if (phoneNumberHandler() == true) {
            if (!orderAlert()){
                return;
            }
            OrderProcessing.currentPhoneNumber.add(phoneNumber);
            textPhoneNumber.setFocusable(false);
            Intent intent = new Intent(this, PizzaCustomizationActivity.class);
            intent.putExtra("message", "Hawaiian Pizza");
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Enter phone number and try again", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     The onClickPepperoni method allows for the user to click on the Pepperoni pizza to open the pizza
     customization only once they input their valid phone number.
     @param view - the Pepperoni pizza button that was clicked
     */
    public void onClickPepperoni(View view){

        if (phoneNumberHandler() == true) {
            if (!orderAlert()){
                return;
            }
            OrderProcessing.currentPhoneNumber.add(phoneNumber);
            textPhoneNumber.setFocusable(false);
            Intent intent = new Intent(this, PizzaCustomizationActivity.class);
            intent.putExtra("message", "Pepperoni Pizza");
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Enter phone number and try again", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     The orderAlert method checks to see if the phone number entered has been entered before in the past.
     If so, it alerts the user that this user has already placed an order but if not, users are given
     an alert that they are starting a new order
     * @return boolean — true if it is a new order, false otherwise
     */
    public boolean orderAlert(){

        if (!phoneNumberHandler()){
            return false;
        }

        if (!OrderProcessing.phoneNumberList.contains(phoneNumber)) {
            Toast.makeText(MainActivity.this, "Starting new order", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(MainActivity.this, "Only one order per person allowed.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    /**
     The phoneNumberHandler checks if the phone number is a valid 10 digit number
     @return boolean — true if phone number is valid, false otherwise
     */
    private boolean phoneNumberHandler() {

        try {
            if (!textPhoneNumber.getText().equals("") & textPhoneNumber.getText().length() == TEN
                    & isNumber(textPhoneNumber.getText())) {
                 phoneNumber = String.valueOf(textPhoneNumber.getText());
                return true;
                }
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     The isNumber method is a way of checking whether the phone number entered is a number
     or if it is of invalid type
     @param phoneNumber - the number entered by the user
     @return boolean — true if phone number can be parsed as a long int, false otherwise
     */
    private boolean isNumber(Editable phoneNumber){

       try {
           Long.parseLong(String.valueOf(phoneNumber));
           return true;
       } catch (NumberFormatException e){
           return false;
       }

    }

    /**
     The onClickCurrentOrder method allows for the user to open the current order window to see
     their pizzas
     @param view - the Current Order button that was clicked
     */
    public void onClickCurrentOrder(View view){

        Intent redirect = new Intent(this,CurrentOrderActivity.class);
        startActivity(redirect);

    }

    /**
     The onClickStoreOrders method allows for the user to open the store order window to see
     the orders that have been placed
     @param view - the Store Orders button that was clicked
     */
    public void onClickStoreOrders(View view){

        Intent intent = new Intent( this, StoreOrdersActivity.class );
        startActivity(intent);

    }

}