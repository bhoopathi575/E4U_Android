package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button button;
    public TextView textView;
    private EditText editTextEmail,editTextPassword;
    public ImageView imageView;

    private FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.loginbtn);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.signupbtnmain);
        button.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.forgotpass);
        textView.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        imageView = (ImageView) findViewById(R.id.signInWithGoogle);
        imageView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginbtn:
                userLogin();
                break;
            case R.id.signupbtnmain:
                startActivity(new Intent(MainActivity.this, signupScreen.class));
                break;
            case R.id.forgotpass:
                startActivity(new Intent(MainActivity.this, ForgotPassword.class));
                break;


        }

    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email Is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Enter Valid Email!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password Is required!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6) {
            editTextPassword.setError("Password Is less than 6 char");
            editTextPassword.requestFocus();
            return;
        }

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user.getEmail().contains("admin")){
                            startActivity(new Intent(MainActivity.this, AdminActivity.class));
                        }else {
                            startActivity(new Intent(MainActivity.this, SelectionPage.class));
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }
