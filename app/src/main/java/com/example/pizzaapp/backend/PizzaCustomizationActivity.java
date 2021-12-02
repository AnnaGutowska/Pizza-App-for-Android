package com.example.pizzaapp.backend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.pizzaapp.R;


import java.util.ArrayList;

public class PizzaCustomizationActivity extends Activity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener{

    TextView temporary;
    ImageView pizzaPhoto;
    Spinner spinnerSize;

    CheckBox CheckBoxBlackOlives;
    CheckBox CheckBoxHam;
    CheckBox CheckBoxCheese;
    CheckBox CheckBoxSausage;
    CheckBox CheckBoxPineapple;
    CheckBox CheckBoxGreenPepper;
    CheckBox CheckBoxOnion;
    CheckBox CheckBoxChicken;
    CheckBox CheckBoxBeef;
    CheckBox CheckBoxMushroom;
    CheckBox CheckBoxPepperoni;
    TextView textViewTotal;
    private static final int MAX_TOPPINGS = 7;
    ArrayList<Topping> toppings = new ArrayList<>();
    Button addToOrder;
    private String phoneNumber = "";
    Pizza pizza;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cutomize_layout);

        temporary = findViewById(R.id.temporary);
        temporary.setText(getIntent().getStringExtra("message"));
        pizzaPhoto = findViewById(R.id.pizzaPhoto);
        textViewTotal = findViewById(R.id.textViewTotal);
        addToOrder = findViewById(R.id.buttonAddToOrder);
        addToOrder.setOnClickListener(this);
        findToppings();
        setPizzaChoice();

        spinnerSize = findViewById(R.id.spinnerSize);
        spinnerSize.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.size_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter);

        setTextBoxPrice();
    }

    protected void setPizzaChoice() {
        if (temporary.getText().equals("Deluxe Pizza")){
            pizza = PizzaMaker.createPizza("deluxe");
            pizzaPhoto.setImageResource(R.drawable.deluxepic);
            setToppingsForDeluxe();
            pizza.setSize(Size.small);
        } else if (temporary.getText().equals("Hawaiian Pizza")) {
            pizza = PizzaMaker.createPizza("hawaiian");
            pizzaPhoto.setImageResource(R.drawable.hawaiianpic);
            setToppingsForHawaiian();
            pizza.setSize(Size.small);
        } else {
            pizza = PizzaMaker.createPizza("pepperoni");
            pizzaPhoto.setImageResource(R.drawable.pepperonipic);
            setToppingsForPepperoni();
            pizza.setSize(Size.small);
        }
    }

    private void findToppings() {
        CheckBoxBlackOlives = findViewById(R.id.checkBoxBlackOlives);
        CheckBoxBlackOlives.setOnClickListener(this);
        CheckBoxHam = findViewById(R.id.checkBoxHam);
        CheckBoxHam.setOnClickListener(this);
        CheckBoxCheese = findViewById(R.id.checkBoxCheese);
        CheckBoxCheese.setOnClickListener(this);
        CheckBoxSausage = findViewById(R.id.checkBoxSausage);
        CheckBoxSausage.setOnClickListener(this);
        CheckBoxPineapple = findViewById(R.id.checkBoxPineapple);
        CheckBoxPineapple.setOnClickListener(this);
        CheckBoxGreenPepper = findViewById(R.id.checkBoxGreenPepper);
        CheckBoxGreenPepper.setOnClickListener(this);
        CheckBoxOnion = findViewById(R.id.checkBoxOnion);
        CheckBoxOnion.setOnClickListener(this);
        CheckBoxChicken = findViewById(R.id.checkBoxChicken);
        CheckBoxChicken.setOnClickListener(this);
        CheckBoxBeef = findViewById(R.id.checkBoxBeef);
        CheckBoxBeef.setOnClickListener(this);
        CheckBoxMushroom = findViewById(R.id.mushroom);
        CheckBoxMushroom.setOnClickListener(this);
        CheckBoxPepperoni = findViewById(R.id.checkBoxPepperoni);
        CheckBoxPepperoni.setOnClickListener(this);

    }

    private void setToppingsForPepperoni() {
        CheckBoxPepperoni.setChecked(true);

        pizza.addToppings(Topping.Pepperoni);
        toppings.add(Topping.Pepperoni);

        setTextBoxPrice();
    }

    private void setToppingsForHawaiian() {
        CheckBoxPineapple.setChecked(true);
        CheckBoxCheese.setChecked(true);

        pizza.addToppings(Topping.Pineapple);
        pizza.addToppings(Topping.Cheese);

        toppings.add(Topping.Pineapple);
        toppings.add(Topping.Cheese);

        setTextBoxPrice();

    }

    private void setToppingsForDeluxe() {
        CheckBoxGreenPepper.setChecked(true);
        CheckBoxSausage.setChecked(true);
        CheckBoxOnion.setChecked(true);
        CheckBoxPepperoni.setChecked(true);
        CheckBoxMushroom.setChecked(true);

        pizza.addToppings(Topping.Sausage);
        pizza.addToppings(Topping.GreenPepper);
        pizza.addToppings(Topping.Onion);
        pizza.addToppings(Topping.Pepperoni);
        pizza.addToppings(Topping.Mushroom);

        toppings.add(Topping.Sausage);
        toppings.add(Topping.GreenPepper);
        toppings.add(Topping.Onion);
        toppings.add(Topping.Pepperoni);
        toppings.add(Topping.Mushroom);

        setTextBoxPrice();

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String size = parent.getSelectedItem().toString();
        pizza.setSize(Size.valueOf(size));
        setTextBoxPrice();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setTextBoxPrice() {
        textViewTotal.setText(String.format("%.2f", pizza.price()));
    }

    public void onClickAddToOrder(){
        try {
            OrderProcessing.individualOrder.add(pizza);
            Toast.makeText(PizzaCustomizationActivity.this, "Success. Added to order.",
                    Toast.LENGTH_LONG).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(PizzaCustomizationActivity.this, "Failed to add to order.",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View v) {
        if (v == addToOrder) {
            onClickAddToOrder();
        } else {
            CheckBox cb = (CheckBox) v;
            String text = cb.getText().toString();
            String string = text.replace(" ", "").trim();

            Topping topping = Topping.valueOf(string);
            if (toppings.size() < MAX_TOPPINGS) {
                if (cb.isChecked()) {
                    pizza.addToppings(topping);
                    toppings.add(topping);
                    setTextBoxPrice();
                }
            } else if (toppings.size() >= MAX_TOPPINGS) {
                if (!cb.isChecked()) {
                    pizza.removeToppings(topping);
                    toppings.remove(topping);
                    setTextBoxPrice();
                    return;
                }
                Toast.makeText(PizzaCustomizationActivity.this, "Maximum of 7 toppings are allowed!",
                        Toast.LENGTH_LONG).show();
                cb.setChecked(false);
                return;
            }

        }
    }
}
