package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    public Button button;
    public TextView textView;
    private  EditText editfullnametext,editemailtext,editmobilenotext,editpasswordtext;



    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        button = (Button) findViewById(R.id.signupbtn);
        button.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.backtosignin);
        textView.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.signuptext);
        textView.setOnClickListener(this);

        editfullnametext = (EditText) findViewById(R.id.fullname);
        editemailtext = (EditText) findViewById(R.id.emailid);
        editmobilenotext = (EditText) findViewById(R.id.mobilenumber);
        editpasswordtext = (EditText) findViewById(R.id.password);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupbtn:
                registerUser();
                break;
            case R.id.backtosignin:
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                break;
            case R.id.signuptext:
                startActivity(new Intent(SignUpActivity.this, SignUpActivity.class));

        }
    }

    private void registerUser() {
        String fullname = editfullnametext.getText().toString();
        String email = editemailtext.getText().toString().trim();
        String mobileno = editmobilenotext.getText().toString().trim();
        String password = editpasswordtext.getText().toString().trim();

        if (fullname.isEmpty()){
            editfullnametext.setError("FullName Is required!");
            editfullnametext.requestFocus();
            return;
        }


        if (email.isEmpty()){
            editemailtext.setError("Email Is required!");
            editemailtext.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editemailtext.setError("Please Enter Valid Email!");
            editemailtext.requestFocus();
            return;
        }


        if (mobileno.isEmpty()){
            editmobilenotext.setError("Mobile Number Is required!");
            editmobilenotext.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editpasswordtext.setError("Password Is required!");
            editpasswordtext.requestFocus();
            return;
        }
        if(password.length()<6){
            editpasswordtext.setError("Password Is less than 6 char");
            editpasswordtext.requestFocus();
            return;

        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(fullname,email,mobileno);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this,"User has been registered Successfully!", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(SignUpActivity.this,"User registration Failed! Try again!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(SignUpActivity.this,"User registration Failed! Try again!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}