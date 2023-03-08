package com.example.quizme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText user_name,pass_word;
    FirebaseAuth mAuth , auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        user_name = findViewById(R.id.et_userNameLA);
        pass_word = findViewById(R.id.et_passwordLA);

        Button btn_login = findViewById(R.id.btn_loginLA);
        Button btn_forgot = findViewById(R.id.btn_forgotPasswordLA);
        Button btn_register = findViewById(R.id.btn_registerLA);

        mAuth = FirebaseAuth.getInstance();
        btn_login.setOnClickListener(v->{
            String email = user_name.getText().toString().trim();
            String password = pass_word.getText().toString().trim();
            if(email.isEmpty()){
                user_name.setError("Email is Empty.");
                user_name.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                user_name.setError("Enter valid Email Address.");
                user_name.requestFocus();
                return;
            }
            if(password.isEmpty()){
                pass_word.setError("Password is Empty.");
                pass_word.requestFocus();
                return;
            }
            if(pass_word.length()<6){
                pass_word.setError("Length of Password be more than 6.");
                pass_word.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
               if(task.isSuccessful()){
                   startActivity(new Intent(this, MainActivity.class));
               }
               else{
                   Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT).show();
               }
            });
        });
        btn_register.setOnClickListener(v->{
            startActivity(new Intent(this,RegisterActivity.class));
        });
        btn_forgot.setOnClickListener(v->{
            if(user_name.length()==0){
                Toast.makeText(this,"Enter Email ID ",Toast.LENGTH_SHORT).show();
            }
            else{
                FirebaseAuth.getInstance().setLanguageCode("en");
                FirebaseAuth.getInstance().sendPasswordResetEmail(user_name.getText().toString());
                Toast.makeText(this,"Reset request sent to email",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1 = sh.getString("user_name","");
        String s2 = sh.getString("pass_word","");
        user_name.setText(s1);
        pass_word.setText(s2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1 = sh.getString("user_name","");
        String s2 = sh.getString("pass_word","");
        user_name.setText(s1);
        pass_word.setText(s2);
    }
}