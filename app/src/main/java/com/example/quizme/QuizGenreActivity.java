package com.example.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Locale;

public class QuizGenreActivity extends AppCompatActivity {
    GridView genreGv;
    ProgressDialog progress;
    public static final String TAG = "QuizGenreActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_genre);
        getSupportActionBar().hide();
        genreGv  = findViewById(R.id.quizGenreGridV);
        ArrayList<GenreModel> genreModelArrayList = new ArrayList<GenreModel>();
        genreModelArrayList.add(new GenreModel("LINUX",R.drawable.linux));
        genreModelArrayList.add(new GenreModel("SQL",R.drawable.sql));
        genreModelArrayList.add(new GenreModel("DOCKER",R.drawable.docker));
        genreModelArrayList.add(new GenreModel("HTML",R.drawable.html));
        genreModelArrayList.add(new GenreModel("JAVASCRIPT",R.drawable.javascript));
        genreModelArrayList.add(new GenreModel("CODE",R.drawable.coding));
        genreModelArrayList.add(new GenreModel("RANDOM",R.drawable.random));

        GenreGVAdapter adapter = new GenreGVAdapter(this,genreModelArrayList);
        genreGv.setAdapter(adapter);
        genreGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedName = genreModelArrayList.get(i).getCourse_name().toLowerCase();
                Log.e(TAG,"selected Name is "+selectedName);
                Intent intent = new Intent(QuizGenreActivity.this,QuestionLoadActivity.class).putExtra("name",selectedName);
                startActivity(intent);

            }
        });
    }
}