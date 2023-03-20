package com.example.quizappoblig1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

import android.os.AsyncTask;
import android.util.Log;

import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.os.CountDownTimer;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizappoblig1.Database.Animal;
import com.example.quizappoblig1.Database.AnimalDatabase;
import com.example.quizappoblig1.R;
import com.example.quizappoblig1.ViewModels.AnimalAsyncTask;


public class QuizActivity extends AppCompatActivity {

    private ImageView image;
    private Random rnd = new Random();
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Integer score = 0;
    private Integer attempts = 0;
    private int correctInt;
    private TextView scoreText;
    private AnimalDatabase db;
    private boolean difficulty;
    private AnimalAsyncTask repository;
    private TextView timer;
    public CountDownTimer timerObject = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish()   {
                attempts++;
                showAnswers();
                updateScore();

                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(true);
                timer.setText("Time is up!");
            }

        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        db = AnimalDatabase.getInstance(this);
        difficulty = getIntent().getBooleanExtra("switch", true);
        AnimalAsyncTask repository = new AnimalAsyncTask(db.animalDao(), new AnimalAsyncTask.AsyncResponse() {
            @Override
            public void processFinish(List<Animal> output) {
                newQuestion();
            }
        });

        image = findViewById(R.id.image);
        btn1 = findViewById(R.id.alt0);

        btn2 = findViewById(R.id.alt1);
        btn3 = findViewById(R.id.alt2);

        btn4 = findViewById(R.id.next);
        TextView scoreText = findViewById(R.id.textScore);

        btn1.setOnClickListener(((v) -> {
            guess(0);
        }));
        btn2.setOnClickListener((v) -> {
            guess(1);
        });
        btn3.setOnClickListener((v) -> {
            guess(2);
        });
        btn4.setOnClickListener((v) -> {
            newQuestion();
        });
    }

    public void newQuestion() {
        db.animalDao().getThree(new AnimalAsyncTask.AsyncResponse() {
            @Override
            public void processFinish(List<Animal> liste) {
                correctInt = rnd.nextInt(liste.size());

                byte[] picture = liste.get(correctInt).getImage();
                image.setImageBitmap(BitmapFactory.decodeByteArray(picture, 0, picture.length));
                btn1.setText(liste.get(0).getName());
                btn2.setText(liste.get(1).getName());
                btn3.setText(liste.get(2).getName());

                btn1.setBackgroundColor(getResources().getColor(R.color.blue));
                btn2.setBackgroundColor(getResources().getColor(R.color.blue));
                btn3.setBackgroundColor(getResources().getColor(R.color.blue));
                btn2.setEnabled(true);
                btn1.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(false);
                if(difficulty) { timerObject.start(); }
            }
        });
    }



    public void guess(int index) {

        if (isCorrect(index)) {
            score++;
        }

        attempts++;
        showAnswers();
        updateScore();
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(true);
        if(difficulty) { timerObject.cancel(); }

        timer = findViewById(R.id.timer);

    }



    private void updateScore() {
        scoreText = findViewById(R.id.textScore);
        scoreText.setText("Score: "+ score + "/" + attempts);
    }

    private void showAnswers() {

        if(correctInt == 0) {
            btn1.setBackgroundColor(getResources().getColor(R.color.green));
            btn2.setBackgroundColor(getResources().getColor(R.color.red));
            btn3.setBackgroundColor(getResources().getColor(R.color.red));
        } else if(correctInt == 1) {
            btn1.setBackgroundColor(getResources().getColor(R.color.red));
            btn2.setBackgroundColor(getResources().getColor(R.color.green));
            btn3.setBackgroundColor(getResources().getColor(R.color.red));
        } else if(correctInt == 2) {
            btn1.setBackgroundColor(getResources().getColor(R.color.red));
            btn2.setBackgroundColor(getResources().getColor(R.color.red));
            btn3.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }


    public boolean isCorrect (int position) {
        return position == correctInt;
    }
}
