package com.example.quizappoblig1;

import androidx.lifecycle.ViewModel;

import android.os.Bundle;

public class QuizState extends ViewModel {

    private Database db;
    protected int guesses;
    protected int points;

//   public void guess() {
//        if( /* correct TODO */ ) { points++; }
//            guesses++;
//    }

    public void nextQuestion() {
        db.getNewQuestion();
    }

}