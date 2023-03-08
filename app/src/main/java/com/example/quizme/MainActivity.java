package com.example.quizme;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public Button btn_takeQuiz,btn_history;
    public TextView totalScore, userName;
    NavigationView nav;
    int vscore =0;
    ImageView ivLevel;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,userRef,nameRef,scoreRef;
    NavigationView navView;
    public static final String TAG ="MainActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayMA);
        btn_takeQuiz = findViewById(R.id.btn_takeQuizMA);
        btn_history = findViewById(R.id.btn_history);
        totalScore = findViewById(R.id.TotalScoreMA);
        userName = findViewById(R.id.userNameMA);
        ivLevel = findViewById(R.id.ivMainActivity);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        userRef = databaseReference.child("users").child(Objects.requireNonNull(mAuth.getUid()).toString());
        nameRef = userRef.child("name");
        scoreRef = userRef.child("score");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        btn_takeQuiz.setOnClickListener(view -> {
            startActivity(new Intent(this,QuizGenreActivity.class));
        });
        btn_history.setOnClickListener(view -> {
            startActivity(new Intent(this,HistoryItemActivity.class));

        });

        scoreRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e(TAG,"onDataChange: "+snapshot.toString());
                final String score = Objects.requireNonNull(snapshot.getValue().toString());
                Log.e(TAG,"onDataChange: "+ score);
                vscore = parseInt(score);
                totalScore.setText(String.valueOf(vscore));
                setImageView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
               Log.e(TAG,"onDataChange: cancelled");
            }
        });
        nameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e(TAG,"onDataChange: "+snapshot.toString());
                final String name = Objects.requireNonNull((snapshot.getValue().toString().trim().toUpperCase(Locale.ROOT)));
                Log.e(TAG,"onDataChange: "+name);
                String S = "Lets Play! "+name;
                userName.setText(S);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
             Log.e(TAG,"onDataChange: cancelled nameRef");
            }
        });
//        nav = (NavigationView)findViewById(R.id.navMenuMA);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.nav_open,R.string.nav_close);
//         drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        navView.setNavigationItemSelectedListener(item -> {
//            Log.i(TAG, "onNavigationItemSelected: " + item.toString());
//            if (item.getItemId() == R.id.nav_logout) {
//                mAuth.signOut();
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                Log.e(TAG, "onNavigationItemSelected: " + item.getItemId());
//            }
//            return true;
//        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        Log.e(TAG,"NavMenu"+item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setImageView() {

        if(vscore<=10){
            ivLevel.setImageResource(R.drawable.duck);
        }
        else if(vscore<=25){
            ivLevel.setImageResource(R.drawable.bronze_medal);
        }
        else if(vscore<=50){
            ivLevel.setImageResource(R.drawable.silver_medal);
        }
        else if(vscore<=100){
            ivLevel.setImageResource(R.drawable.gold_medal);
        }
        else{
            ivLevel.setImageResource(R.drawable.award);
        }
    }
}
