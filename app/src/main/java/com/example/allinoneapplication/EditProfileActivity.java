package com.example.allinoneapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText editName, editEmail, editUsername, editPassword;
    Button saveButton,Home;
    String nameUser, emailUser, usernameUser, passwordUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        saveButton = findViewById(R.id.saveButton);

        Home = findViewById(R.id.editButton1);
        showData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                boolean chack = validateinfo(name,email,username,password);
                if (chack == true) {
                if (isNameChanged() || isEmailChanged() || isPasswordChanged()) {

                    Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(EditProfileActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
                }
                else{
                    Toast.makeText(getApplicationContext(), "sorry chack information again",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isNameChanged(){
        if (!nameUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("name").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public boolean isEmailChanged(){
        if (!emailUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public boolean isPasswordChanged(){
        if (!passwordUser.equals(editPassword.getText().toString())){
            reference.child(usernameUser).child("password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public void showData(){
        Intent intent = getIntent();

        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        usernameUser = intent.getStringExtra("username");
        passwordUser = intent.getStringExtra("password");

        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editUsername.setText(usernameUser);
        editPassword.setText(passwordUser);
    }
    private boolean validateinfo(String name, String email, String username, String password) {
        if(name.length()<=2){
            editName.requestFocus();
            editName.setError("Enter at least 3 Alphabate Character");
            return false;
        }
        else if(!name.matches("[a-zA-Z]+")){
            editName.requestFocus();
            editName.setError("Enter only Alpabetcal Character");
            return false;
        }
        else if (email.length()==0){
            editEmail.requestFocus();
            editEmail.setError("Field cannot be Empty");
            return false;
        }
        else if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            editEmail.requestFocus();
            editEmail.setError("Enter valid Email");
            return false;
        }else  if(username.length()<=5){
            editUsername.requestFocus();
            editUsername.setError("Enter Minimum 6 Character ");
            return false;
        }
        else if(!username.matches("[a-zA-Z]+")){
            editUsername.requestFocus();
            editUsername.setError("Enter only Alpabetcal");
            return false;
        }
        else if (password.length()<=7){
            editPassword.requestFocus();
            editPassword.setError("Minimum 8 Character Required");
            return false;
        }
        else
        {
            return true;
        }
    }
}