package com.example.quizme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    Button  btn_signup;
    EditText user_name, pass_word,cnf,age,nameI;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        user_name = findViewById(R.id.userNameRA);
        pass_word = findViewById(R.id.passwordRA);
        nameI = findViewById(R.id.nameRA);
        age = findViewById(R.id.ageRA);
        cnf = findViewById(R.id.cnfPasswordRA);
         btn_signup = findViewById(R.id.btn_signupRA);
        mAuth = FirebaseAuth.getInstance();
        btn_signup.setOnClickListener(view ->  {
            String email = user_name.getText().toString().trim();
            String password = pass_word.getText().toString().trim();
            String userAge = age.getText().toString().trim();
            String name_user = nameI.getText().toString().trim();
            String cnfrm = cnf.getText().toString().trim();
            if (email.isEmpty()) {
                user_name.setError("Email is Empty.");
                user_name.requestFocus();
                return;
            }
            if (name_user.isEmpty()) {
                nameI.setError("Name of the User is Empty.");
                nameI.requestFocus();
                return;
            }
            if (userAge.isEmpty()) {
                age.setError("Age of the user is Empty.");
                age.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                user_name.setError("Enter the valid email address");
                user_name.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                pass_word.setError("Password is Empty");
                pass_word.requestFocus();
                return;
            }
            if (password.length() < 6) {
                pass_word.setError("Password length should be more than 6.");
                pass_word.requestFocus();
                return;
            }
            String str = age.getText().toString();
            if (Integer.parseInt(str) > 100 && Integer.parseInt(str) > 0) {
                age.setError("Age must be between 0 to 100.");
                age.requestFocus();
                return;
            }
            if (!cnfrm.equals(password)) {
                Toast.makeText(RegisterActivity.this, "The password and confirm password does not matches", Toast.LENGTH_SHORT).show();
                pass_word.setError("Please Enter Again");
                cnf.setError("The password and confirm password does not matches.");
                pass_word.requestFocus();
                return;
            }
            HashMap<String, Object> userMap;
            userMap = new HashMap<String, Object>();
            userMap.put("name", name_user);
            userMap.put("email", email);
            userMap.put("age", userAge);
            userMap.put("password", password);
            userMap.put("score", 0);

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.i(TAG, String.valueOf(userMap.size()));
                        Toast.makeText(RegisterActivity.this, "You are registered successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Not allowed. Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}