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
            Toast.makeText(MainActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
        }

    }

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
            Toast.makeText(MainActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
        }

    }

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
            Toast.makeText(MainActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
        }

    }

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

    private boolean isNumber(Editable phoneNumber){

       try {
           Long.parseLong(String.valueOf(phoneNumber));
           return true;
       } catch (NumberFormatException e){
           return false;
       }

    }

    public void onClickCurrentOrder(View view){

        Intent redirect = new Intent(this,CurrentOrderActivity.class);
        startActivity(redirect);

    }

    public void onClickStoreOrders(View v){

        Intent intent = new Intent( this, StoreOrdersActivity.class );
        startActivity(intent);

    }

}