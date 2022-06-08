package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
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


    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

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

        gso = new  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginbtn:
                userLogin();
                break;
            case R.id.signupbtnmain:
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                break;
            case R.id.forgotpass:
                startActivity(new Intent(MainActivity.this, ForgotPassword.class));
                break;
            case R.id.signInWithGoogle:
               googleSignIn();
                break;



        }

    }

    private void googleSignIn() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                startActivity(new Intent(getApplicationContext(), AdminMovieActivity.class));
            } catch (ApiException e) {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email Is required!");
            editTextEmail.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Enter Valid Email!");
            editTextEmail.requestFocus();
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password Is required!");
            editTextPassword.requestFocus();
        }
        if(password.length()<6) {
            editTextPassword.setError("Password Is less than 6 char");
            editTextPassword.requestFocus();
        }

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user.getEmail().contains("admin")){
                            startActivity(new Intent(MainActivity.this, AdminActivity.class));
                        }else {
                            startActivity(new Intent(MainActivity.this, UserMovieActivity.class));
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }



    }

