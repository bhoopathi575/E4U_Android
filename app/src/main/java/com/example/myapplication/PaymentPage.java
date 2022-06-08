package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentPage extends AppCompatActivity implements PaymentResultListener {
public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments_page);
        button = findViewById(R.id.paybtn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String sAmount = "25";

        int amount = Math.round(Float.parseFloat(sAmount)*100);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout checkout = new Checkout();

                checkout.setKeyID("rzp_test_HCVYCp9beI7gNu");

                checkout.setImage(R.drawable.rzpay);

                JSONObject object = new JSONObject();
                try {
                    object.put("name","Entertainment4U");
                    object.put("description","Payment");
                    object.put("theme.color","#0093DD");
                    object.put("currency","CAD");
                    object.put("amount",amount);
                    object.put("prefill.contact","5146326464");
                    object.put("prefill.email","bhoopathi575@gmail.com");


                    checkout.open(PaymentPage.this,object);


                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment");
        builder.setMessage(s);
        builder.show();

        startActivity(new Intent(PaymentPage.this,PaymentComplete.class));
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

    }
}