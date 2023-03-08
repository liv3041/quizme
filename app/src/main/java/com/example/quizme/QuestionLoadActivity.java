package com.example.quizme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionLoadActivity extends AppCompatActivity {

    private Call<ArrayList<Questions>> call;
    ProgressDialog progress;
    int pos =0;
    TextView QuestionNo ,QuestionTv,optA,optB,optC,optD,optE,optF;
    LinearLayout linearLayout;
    ProgressBar progressBar;
    String cValue,userSelection,genre;
    ImageView back,exit;
    int score =0;
    int red = Color.parseColor("#FF0000");
    int teal = Color.parseColor("#F37878");
    int green = Color.parseColor("#59CE8F");
    int brown = Color.parseColor("#876445");
    int lbrown = Color.parseColor("#CA955C");
    int bteal = Color.parseColor("#FAD9A1");
    int lblug = Color.parseColor("#ADCF9F");
    ArrayList<Questions> questionsDetails;
    public static final String TAG ="QuestionLoadActivity";
    CardView optAq,optBq,optCq,optDq,optEq,optFq,cardQuestionQLA;
     Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_load);
        getSupportActionBar().hide();
        QuestionNo=findViewById(R.id.qnoQLA);
        QuestionTv = findViewById(R.id.ques_QQT);
        optA = findViewById(R.id.optA_QQT);
        optB = findViewById(R.id.optB_QQT);
        optC = findViewById(R.id.optC_QQT);
        optD = findViewById(R.id.optD_QQT);
        optE = findViewById(R.id.optE_QQT);
        optF = findViewById(R.id.optF_QQT);
        back = findViewById(R.id.backBtnQQ);
        exit = findViewById(R.id.exitBtnQQ);
        cardQuestionQLA = findViewById(R.id.ques_QQC);
        next = findViewById(R.id.nextQLA);
        progressBar = findViewById(R.id.progressBarQQ);
        linearLayout = findViewById(R.id.llCardViewQLA);
        optAq= findViewById(R.id.optA_QQC);
        optBq = findViewById(R.id.optB_QQC);
        optCq = findViewById(R.id.optC_QQC);
        optDq = findViewById(R.id.optD_QQC);
        optEq = findViewById(R.id.optE_QQC);
        optFq = findViewById(R.id.optF_QQC);
        Intent intent= getIntent();
        Bundle extras = getIntent().getExtras();
        genre = extras.getString("name").toLowerCase(Locale.ROOT).trim();
//        questionsDetails = (ArrayList<Questions>)intent.getSerializableExtra("questionDetails");
        Log.e(TAG,"Selected Genre is "+ genre);
        QuizMethods method = RetrofitQuiz.getRetrofitInstance().create(QuizMethods.class);
        progressBar.setVisibility(View.VISIBLE);
        String apiKey = "EJQUM5ob10VDJ7eg26HI7WB9HUDW4baybscNaek7";
        switch (genre){
            case"html": call = method.getQuestionsOf(apiKey,"","html", String.valueOf(11));
                 Log.e(TAG,"Html");
                 break;
            case"sql": call = method.getSqlQuestions();
                Log.e(TAG,"Sql");
                break;
            case"linux": call = method.getLinuxQuestions();
                Log.e(TAG,"Linux");
                break;
            case"javascript": call = method.getQuestionsOf(apiKey,"","javascript", String.valueOf(11));
                Log.e(TAG,"Javascript");
                break;
            case"code": call = method.getCodeQuestions();
                Log.e(TAG,"Code");
                break;
            case"docker": call = method.getDockerQuestions();
                Log.e(TAG,"Docker");
                break;
            default:call=method.getAllData();
                  Log.e(TAG,"allData");
                  break;
        }
        Log.e(TAG,"after get "+genre);
        if(pos>9){
            Log.e(TAG,"set view:pos is "+ pos);
            Intent GameIntent = new Intent(this,GameOverActivity.class).putExtra("Genre",genre).putExtra("score",score);
            startActivity(GameIntent);
        }
        if (pos==0){
            progressBar();
        }else{
            getData();
        }
        Log.e(TAG,"onCreate: "+score);
        optAq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSelection ="A";
                optAq.setCardBackgroundColor(teal);
                optA.setBackgroundColor(teal);
                revealAnswers();
                if(java.util.Objects.equals(userSelection, cValue)) score = score+1;
                getScore();
                disableCardOptions();
            }
        });
        optBq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSelection ="B";
                optAq.setCardBackgroundColor(teal);
                optA.setBackgroundColor(teal);
                revealAnswers();
                if(userSelection==cValue) score = score+1;
                getScore();
                disableCardOptions();
            }
        });
        optCq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSelection ="C";
                optAq.setCardBackgroundColor(teal);
                optA.setBackgroundColor(teal);
                revealAnswers();
                if(userSelection==cValue) score = score+1;
                getScore();
                disableCardOptions();
            }
        });
        optDq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSelection ="D";
                optAq.setCardBackgroundColor(teal);
                optA.setBackgroundColor(teal);
                revealAnswers();
                if(userSelection==cValue) score = score+1;
                getScore();
                disableCardOptions();
            }
        });

        optEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSelection ="E";
                optAq.setCardBackgroundColor(teal);
                optA.setBackgroundColor(teal);
                revealAnswers();
                if(userSelection==cValue) score = score+1;
                getScore();
                disableCardOptions();
            }
        });
        optFq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSelection ="F";
                optAq.setCardBackgroundColor(teal);
                optA.setBackgroundColor(teal);
                revealAnswers();
                if(userSelection==cValue) score = score+1;
                getScore();
                disableCardOptions();
            }
        });
       getScore();
       next.setOnClickListener(view -> {
           nextQuestion(questionsDetails,score);
       });
       back.setOnClickListener(view -> {
           startActivity(new Intent(this,QuizGenreActivity.class));

       });
       exit.setOnClickListener(view -> {
           startActivity(new Intent(this,GameOverActivity.class).putExtra("genre",genre).putExtra("score",score));

       });





    }
//    oncreate ends here
public void getData() {
    call.enqueue(new Callback<ArrayList<Questions>>() {
        @Override
        public void onResponse(@NonNull Call<ArrayList<Questions>> call, Response<ArrayList<Questions>> response) {
            Log.e(TAG,"OnResponse:code "+ response.code());
            ArrayList<Questions> questions = response.body();
            Log.i(TAG,"onResponse: "+questions.size());
            questionsDetails = response.body();
            progressBar.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            setView(questionsDetails,pos);
        }

        @Override
        public void onFailure(@NonNull Call<ArrayList<Questions>> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
            Log.e(TAG,"onFailure: "+ t.getMessage());
            finish();
        }
    });

}
    public void setView(ArrayList<Questions> questionsDetails, int pos) {
//        if(pos>9){
//            Log.e(TAG,"set view:pos is "+pos);
//            Intent Gintent = new Intent(this, GameOverActivity.class).putExtra("Genre", genre).putExtra("Score",score);
//            startActivity(Gintent);
//        }
        if(questionsDetails.get(pos).getqId()!=null){
            QuestionNo.setText(String.valueOf(pos+1));
            QuestionTv.setText(questionsDetails.get(pos).getQuestion());
            optA.setText(questionsDetails.get(pos).answers.getAnsA());
            optB.setText(questionsDetails.get(pos).answers.getAnsB());
            optC.setText(questionsDetails.get(pos).answers.getAnsC());
            optD.setText(questionsDetails.get(pos).answers.getAnsD());
            optE.setText(questionsDetails.get(pos).answers.getAnsE());
            optF.setText(questionsDetails.get(pos).answers.getAnsF());
            Toast.makeText(this,questionsDetails.get(pos).getCategory(),Toast.LENGTH_SHORT).show();
            Log.e(TAG,questionsDetails.get(pos).getCategory());
            progressBar.setProgress(pos);
            if(optC.getText().toString().isEmpty()){
                optCq.setVisibility(View.GONE);
            }
            if(optD.getText().toString().isEmpty()){
                optDq.setVisibility(View.GONE);
            }
            if(optE.getText().toString().isEmpty()){
                optEq.setVisibility(View.GONE);
            }
            if(optF.getText().toString().isEmpty()){
                optFq.setVisibility(View.GONE);
            }


        }
//        linearLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        cValue = correctAnswerOpt(questionsDetails,pos);
        Log.e(TAG,cValue);
    }
    public String correctAnswerOpt(ArrayList<Questions> questionsDetails, int pos) {
        if(questionsDetails.get(pos).correctAnswers.getAnsAC().equals("true")){
            return "A";
        }
        else  if(questionsDetails.get(pos).correctAnswers.getAnsBC().equals("true")){
            return "B";
        }
        else  if(questionsDetails.get(pos).correctAnswers.getAnsCC().equals("true")){
            return "C";
        }
        else  if(questionsDetails.get(pos).correctAnswers.getAnsDC().equals("true")){
            return "D";
        }
        else  if(questionsDetails.get(pos).correctAnswers.getAnsEC().equals("true")){
            return "E";
        }
        else  if(Objects.equal(questionsDetails.get(pos).correctAnswers.getAnsFC(),"true")){
            return "F";
        }
        return"";
    }
    public void revealAnswers() {
        final String ans = cValue;
        switch(ans){
            case"A": optAq.setCardBackgroundColor(green);
                optA.setBackgroundColor(green);
                optA.setTextColor(Color.WHITE);
                break;
            case"B": optBq.setCardBackgroundColor(green);
                optB.setBackgroundColor(green);
                optB.setTextColor(Color.WHITE);
                break;
            case"C": optCq.setCardBackgroundColor(green);
                optC.setBackgroundColor(green);
                optC.setTextColor(Color.WHITE);
                break;
            case"D": optDq.setCardBackgroundColor(green);
                optD.setBackgroundColor(green);
                optD.setTextColor(Color.WHITE);
                break;
            case"E": optEq.setCardBackgroundColor(green);
                optE.setBackgroundColor(green);
                optE.setTextColor(Color.WHITE);
                break;
            case"F": optFq.setCardBackgroundColor(green);
                optF.setBackgroundColor(green);
                optF.setTextColor(Color.WHITE);
                break;
        }
    }
    private void getScore() {
        Log.i(TAG,"getScore: "+score);
    }

    public void disableCardOptions() {
        optAq.setEnabled(false);
        optBq.setEnabled(false);
        optCq.setEnabled(false);
        optDq.setEnabled(false);
        optEq.setEnabled(false);
        optFq.setEnabled(false);

    }

    public void nextQuestion(ArrayList<Questions> questionsDetails, int score) {
        optAq.setEnabled(true);
        optBq.setEnabled(true);
        optCq.setEnabled(true);
        optDq.setEnabled(true);
        optEq.setEnabled(true);
        optFq.setEnabled(true);
        pos=pos+1;
        Log.i(TAG,"pos "+pos);
        optAq.setVisibility(View.VISIBLE);
        optBq.setVisibility(View.VISIBLE);
        optCq.setVisibility(View.VISIBLE);
        optDq.setVisibility(View.VISIBLE);
        optEq.setVisibility(View.VISIBLE);
        optFq.setVisibility(View.VISIBLE);

        optAq.setCardBackgroundColor(bteal);
        optA.setBackgroundColor(bteal);

        optBq.setCardBackgroundColor(bteal);
        optB.setBackgroundColor(bteal);

        optCq.setCardBackgroundColor(bteal);
        optC.setBackgroundColor(bteal);

        optDq.setCardBackgroundColor(bteal);
        optD.setBackgroundColor(bteal);

        optEq.setCardBackgroundColor(bteal);
        optE.setBackgroundColor(bteal);

        optFq.setCardBackgroundColor(bteal);
        optF.setBackgroundColor(bteal);
        cardQuestionQLA.setCardBackgroundColor(lblug);
        optA.setTextColor(Color.BLACK);
        optB.setTextColor(Color.BLACK);
        optC.setTextColor(Color.BLACK);
        optD.setTextColor(Color.BLACK);
        optE.setTextColor(Color.BLACK);
        optF.setTextColor(Color.BLACK);
        if(pos>9){
            Log.e(TAG,"nextques:pos is "+pos);
            linearLayout.setVisibility(View.GONE);
            Intent intent = new Intent(this,GameOverActivity.class).putExtra("genre",genre).putExtra("score",score);
            startActivity(intent);
        }
        else{
            setView(questionsDetails,pos);
        }
    }
    private void progressBar() {
        progress = new ProgressDialog(QuestionLoadActivity.this);
        progress.setTitle("Loading Questions..");
        progress.setMessage("Please Wait Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
        new Thread(){
            public void run(){
                getData();
                try{
                    Thread.sleep(4000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                progress.dismiss();
            }
        }.start();
    }


}