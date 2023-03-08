package com.example.quizme;

import java.util.Date;

public class QuizInfo {
    String genre;
    String date;
    String time;
    String score;
    Date currentTime;
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getScore(){
        return score;
    }
    public void setScore(String score){
        this.score = score;
    }
    public Date getCurrentTime() {
        return currentTime;
    }
    public void setCurrentTime(Date currentTime){
        this.currentTime = currentTime;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
}
