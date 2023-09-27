package com.example.pizzaapp.backend;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pizzaapp.R;
import java.util.ArrayList;

/**
 The PizzaCustomizationActivity class allows the user to customize the selected pizza they chose.
 They are able to remove toppings and add up to 7. The user is given alerts if they are adding too
 many toppings. The user is able to view the price increase with each size and topping
 increase/decrease
 */
public class PizzaCustomizationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener{

    private TextView temporary;
    private ImageView pizzaPhoto;
    private Spinner spinnerSize;
    private CheckBox CheckBoxBlackOlives;
    private CheckBox CheckBoxHam;
    private CheckBox CheckBoxCheese;
    private CheckBox CheckBoxSausage;
    private CheckBox CheckBoxPineapple;
    private CheckBox CheckBoxGreenPepper;
    private CheckBox CheckBoxOnion;
    private CheckBox CheckBoxChicken;
    private CheckBox CheckBoxBeef;
    private CheckBox CheckBoxMushroom;
    private CheckBox CheckBoxPepperoni;
    private TextView textViewTotal;
    private Button addToOrder;
    private ArrayList<Topping> toppings = new ArrayList<>();
    private String phoneNumber = "";
    protected Pizza pizza;
    private static final int MAX_TOPPINGS = 7;
    private static final int ONE = 1;


    /**
     The onCreate method sets the fields displayed on the window upon first opening it. It
     assigns the pizza photo, has the running total, allows users to add/remove toppings and placing
     the order, all dependent on the type of pizza they chose.
     @param savedInstanceState - bundle which contains the most recent data
     */
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

    /**
     The setPizzaChoice method displays the correct image of the pizza selected. It also
     initializes the default list of toppings per pizza and initializes the pizza to size small
     */
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

    /**
     The findToppings method allows for the user to add/remove toppings by assigning onClickListeners
      to all the toppings on screen
     */
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

    /**
     The setToppingsForPepperoni sets the correct default topping for the Pepperoni pizza, pepperoni.
     */
    private void setToppingsForPepperoni() {

        CheckBoxPepperoni.setChecked(true);
        pizza.addToppings(Topping.Pepperoni);
        toppings.add(Topping.Pepperoni);
        setTextBoxPrice();

    }

    /**
     The setToppingsForHawaiian sets the correct toppings for the Hawaiian pizza. This includes
     Pineapple and Cheese.
     */
    private void setToppingsForHawaiian() {

        CheckBoxPineapple.setChecked(true);
        CheckBoxCheese.setChecked(true);
        pizza.addToppings(Topping.Pineapple);
        pizza.addToppings(Topping.Cheese);
        toppings.add(Topping.Pineapple);
        toppings.add(Topping.Cheese);
        setTextBoxPrice();

    }

    /**
     The setToppingsForDeluxe sets the correct toppings for the Deluxe pizza. This includes
     Sausage, Onion, Green Pepper, Pepperoni, Mushroom.
     */
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

    /**
     * This onItemSelected method updates the pizza's size and price according to the size
     * selected by the user
     * @param parent - The AdapterView where the selection happened
     * @param view - The size choice within the AdapterView that was clicked
     * @param position - The position of the view in the adapter
     * @param id - The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String size = parent.getSelectedItem().toString();
        pizza.setSize(Size.valueOf(size));
        setTextBoxPrice();

    }

    /**
     * The onNothingSelected is called when no selection is made for the size
     * @param parent - The AdapterView where the lack of selection happened
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    /**
     The setTextBoxPrice method allows for the running total to be displayed as the user is
     customizing their order
     */
    private void setTextBoxPrice() {

        textViewTotal.setText(String.format("%.2f", pizza.price()));

    }

    /**
     The onClickAddtoOrder method alerts the user that their pizza has been added to the order
     after they click the add to order button.
     */
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

    /**
     The onClick method allows allows for the user to customize their pizza using the checkboxes.
     It also ensures there is a minimum of one topping and a maximum of seven toppings. Also allows the
     user to add the pizza to their order upon clicking the button.
     @param view - the button that was clicked
     */
    @Override
    public void onClick(View view) {

        if (view == addToOrder) {
            onClickAddToOrder();
        } else {
            CheckBox cb = (CheckBox) view;
            String text = cb.getText().toString();
            String string = text.replace(" ", "").trim();
            Topping topping = Topping.valueOf(string);
            if (toppings.size() < MAX_TOPPINGS) {
                if (cb.isChecked()) {
                    pizza.addToppings(topping);
                    toppings.add(topping);
                    setTextBoxPrice();
                } else if (!cb.isChecked()) {
                    if (toppings.size() == ONE){
                        Toast.makeText(getApplicationContext(), "Minimum 1 topping!", Toast.LENGTH_LONG).show();
                        cb.setChecked(true);
                        pizza.addToppings(topping);
                        toppings.add(topping);
                    }
                    pizza.removeToppings(topping);
                    toppings.remove(topping);
                    setTextBoxPrice();
                }
            } else if (toppings.size() >= MAX_TOPPINGS) {
                if (!cb.isChecked()) {
                    pizza.removeToppings(topping);
                    toppings.remove(topping);
                    setTextBoxPrice();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Maximum of 7 toppings are allowed!",
                        Toast.LENGTH_LONG).show();
                cb.setChecked(false);
                return;
            }
        }
    }


}
