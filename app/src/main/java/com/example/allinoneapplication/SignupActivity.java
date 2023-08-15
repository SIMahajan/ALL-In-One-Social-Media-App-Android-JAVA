package com.example.allinoneapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupUsername, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
                boolean chack = validateinfo(name,email,username,password);
                if (chack == true) {
                    HelperClass helperClass = new HelperClass(name, email, username, password);
                    reference.child(username).setValue(helperClass);

                    Toast.makeText(SignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "sorry chack information again",Toast.LENGTH_SHORT).show();
                }
            }
        });




        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateinfo(String name, String email, String username, String password) {
        if(name.length()<=2){
            signupName.requestFocus();
            signupName.setError("Enter at least 3 Alphabate Character");
            return false;
        }
        else if(!name.matches("[a-zA-Z]+")){
            signupName.requestFocus();
            signupName.setError("Enter only Alpabetcal Character");
            return false;
        }
        else if (email.length()==0){
            signupEmail.requestFocus();
            signupEmail.setError("Field cannot be Empty");
            return false;
        }
        else if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            signupEmail.requestFocus();
            signupEmail.setError("Enter valid Email");
            return false;
        }else  if(username.length()<=5){
            signupUsername.requestFocus();
            signupUsername.setError("Enter Minimum 6 Character ");
            return false;
        }
        else if(!username.matches("[a-zA-Z]+")){
            signupUsername.requestFocus();
            signupUsername.setError("Enter only Alpabetcal");
            return false;
        }
        else if (password.length()<=7){
            signupPassword.requestFocus();
            signupPassword.setError("Minimum 8 Character Required");
            return false;
        }
        else
        {
            return true;
        }
    }
}