package com.example.quizme;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizMethods {
    String url1 ="https://quizapi.io/api/v1/questions?apiKey=EJQUM5ob10VDJ7eg26HI7WB9HUDW4baybscNaek7";
    String url = "questions";
    @GET(url)
    Call <ArrayList<Questions>> getAllData();
    String linux = url1+"&category=linux&limit=11&tags=Linux";
    @GET(linux)
    Call<ArrayList<Questions>> getLinuxQuestions();

    String html = url1+"&category=uncategorized&limit=11&tags=HTML";
    @GET(html)
    Call<ArrayList<Questions>> getHtmlQuestions();

    String sql = url1+"&category=sql&limit=11&tags=MySQL";
    @GET(sql)
    Call<ArrayList<Questions>> getSqlQuestions();

    String docker = url1+"&category=docker&limit=11&tags=Docker";
    @GET(docker)
    Call<ArrayList<Questions>> getDockerQuestions();

    String code = url1+"&category=code&difficulty=Easy&limit=11&tags=HTML,JavaScript,WordPress,MySQL";
    @GET(code)
    Call<ArrayList<Questions>> getCodeQuestions();

    String javascript = url1+"&category=uncategorized&limit=11&tags=JavaScript";
    @GET(javascript)
    Call<ArrayList<Questions>> getJavascriptQuestions();

    String random = url1+"&&category=code&limit=11&tags=Docker,HTML,JavaScript,Linux,MySQL,BASH";
    @GET(random)
    Call<ArrayList<Questions>> getRandomQuestions();

    @GET(url)
    Call<ArrayList<Questions>>getQuestionsOf(@Query("apiKey") String apiKey,@Query("category") String category,@Query("tags")String tags,@Query("limit") String limit);
}
