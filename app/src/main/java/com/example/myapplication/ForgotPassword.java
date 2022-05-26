package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    public Button button;
    private EditText emailEditText;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        button = (Button) findViewById(R.id.verifybtn);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.cancel_button);
        button.setOnClickListener(this);
        emailEditText = (EditText) findViewById(R.id.emailid);

        mAuth = FirebaseAuth.getInstance();


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                startActivity(new Intent(ForgotPassword.this,MainActivity.class));
                break;
            case R.id.verifybtn:
                resetPassword();
                break;
    }

}

    private void resetPassword() {

        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()){
            emailEditText.setError("Email Is required!");
            emailEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please Enter Valid Email!");
            emailEditText.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"Reset Password Emil sent Successfully!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ForgotPassword.this,"Something Went Wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    }