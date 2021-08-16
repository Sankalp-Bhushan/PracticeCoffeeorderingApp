package com.example.myapplicationinputoutput;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public int  noOfCoffee = 0;

    public void submitOrder(View view) {
      StringBuilder price = new StringBuilder();

        CheckBox whippedCream = (CheckBox) findViewById(R.id.notify_me_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate.isChecked();
        Log.v("MainActivity", "has whipped cream" + hasWhippedCream);

      price.append("Pay $ ");
      if(hasWhippedCream){
          price.append(  noOfCoffee * 8 + "\nfor whipped cream and coffee");
      } else if(hasChocolate){
          price.append( noOfCoffee * 10 + "\nfor chocolate and coffee");
      } else {
          price.append(noOfCoffee * 5+"\nfor coffee");
      }
      price.append("\nThank You");

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "order it for me");
        intent.putExtra(Intent.EXTRA_TEXT, price.toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

      //displayMessage(price.toString());

    }

    public void incrementOrder(View view) {
        noOfCoffee++;
       display(noOfCoffee);
    }

    public void decrementOrder(View view) {
         noOfCoffee--;
        display(noOfCoffee);
    }

    private void display(int num) {
        TextView quantiyTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantiyTextView.setText("" + num);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}