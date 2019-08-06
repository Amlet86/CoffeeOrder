package com.example.justjava;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int currentNumberOfCoffees = 1;
    int pricePerCup = 5;
    int priceWhippedCream = 1;
    int priceChocolate = 2;
    String[] mail = {"wecow@yandex.ru"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayPrice();
    }

    /**
     * This method return string from field Name.
     */
    private String getName() {
        EditText userNameField = (EditText) findViewById(R.id.plain_text_input);
        return String.valueOf(userNameField.getText());
    }

    /**
     * This method return string for display info of Whipped Cream topping depending on condition checkBox.
     */
    private boolean getWhippedCream() {
        CheckBox toppingCheckBox = (CheckBox) findViewById(R.id.checkbox_whippedcream);
        return toppingCheckBox.isChecked();
    }

    /**
     * This method return string for display info of Chocolate topping depending on condition checkBox.
     */
    private boolean getChocolate() {
        CheckBox toppingCheckBox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        return toppingCheckBox.isChecked();
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (currentNumberOfCoffees < 10)
            currentNumberOfCoffees++;
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "You can't order more 10 cups of coffee.", Toast.LENGTH_SHORT);
            toast.show();
        }
        display(currentNumberOfCoffees);
        displayPrice();
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (currentNumberOfCoffees > 1)
            currentNumberOfCoffees--;
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "You can't order less 1 cup of coffee.", Toast.LENGTH_SHORT);
            toast.show();
        }
        display(currentNumberOfCoffees);
        displayPrice();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        createOrderSummary(currentNumberOfCoffees);
    }

    /**
     * This method displays the given price value on the screen.
     */
    private void displayPrice() {
        int totalPrice = calculatePrice();
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(totalPrice));
    }

    /**
     * This method calculate and return total price.
     */
    private int calculatePrice() {
        int totalPrice = currentNumberOfCoffees * pricePerCup;
        if (getWhippedCream())
            totalPrice = totalPrice + currentNumberOfCoffees * priceWhippedCream;
        if (getChocolate())
            totalPrice = totalPrice + currentNumberOfCoffees * priceChocolate;
        return totalPrice;
    }

    /**
     * This method call intent for send the given text and price value on the post.
     */
    private void createOrderSummary(int number) {
        String toppingOne = getWhippedCream() ? getResources().getString(R.string.whippedCream) : "";
        String toppingTwo = getChocolate() ? getResources().getString(R.string.chocolate) : "";
        String message =
                getResources().getString(R.string.usersName) + ": " + getName() + "\n" +
                        getResources().getString(R.string.quantity) + ": " + number + " " + getResources().getString(R.string.coffee) + "\n" +
                        getResources().getString(R.string.titleOfToppings) + ": " + toppingOne + " " + toppingTwo + "\n" +
                        getResources().getString(R.string.totalPrice) + ": " + NumberFormat.getCurrencyInstance().format(calculatePrice()) + "\n" +
                        getResources().getString(R.string.thankYou);
        composeEmail(mail, message);
    }

    public void composeEmail(String[] addresses, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject));
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}