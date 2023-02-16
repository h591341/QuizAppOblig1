package com.example.quizappoblig1;

import static java.util.Collections.shuffle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import java.util.Random;

import android.annotation.SuppressLint;

import android.os.Bundle;


import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


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
    private Database db;
    private boolean difficulty;



    private ProgressBar progressBar;
    private int counter;

    private final int DELAY = 3000;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        /**
        Bundle bundle = getIntent().getBundleExtra("dbase");
        Log.d("bundleString", bundle.getParcelable("db", Database.class).getList().get(0).toString());
        db = bundle.getParcelable("db", Database.class);
        Log.d("db0Name", db.getList().get(0).toString());
        */
        db = new Database();

        // Not implementet yet
        //If hard mode is activated
        //if (difficulty.equals("hard")) {
        //    startInactivityTimer();
        //}

        //må opprette progressbar i XML med id
        progressBar = findViewById(R.id.progressBar);

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
        newQuestion();

    }


    public void newQuestion() {
        ArrayList<Animal> liste = db.getNewQuestion();
        correctInt = rnd.nextInt(db.list.size()-1);
        for(Animal a : liste) { Log.d("AnimalToString", a.toString()); }
        Log.d("CorrectInt", ""+correctInt);
        if(correctInt == 0) {
            image.setImageResource(liste.get(0).getImage());
            btn1.setText(liste.get(0).getName());
            btn2.setText(liste.get(1).getName());
            btn3.setText(liste.get(2).getName());
        } else if(correctInt == 1 ) {
            image.setImageResource(liste.get(1).getImage());
            btn1.setText(liste.get(0).getName());
            btn2.setText(liste.get(1).getName());
            btn3.setText(liste.get(2).getName());
        } else {
            image.setImageResource(liste.get(2).getImage());
            btn1.setText(liste.get(0).getName());
            btn2.setText(liste.get(1).getName());
            btn3.setText(liste.get(2).getName());
        }
        btn1.setBackgroundColor(getResources().getColor(R.color.blue));
        btn2.setBackgroundColor(getResources().getColor(R.color.blue));
        btn3.setBackgroundColor(getResources().getColor(R.color.blue));
        btn2.setEnabled(true);
        btn1.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(false);

    }


    public void guess(int index) {
        if (isCorrect(index)) {
            score++;
        }
        attempts++;
        showAnswers(index);
        updateScore();
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(true);
    }

    private void updateScore() {
        scoreText = findViewById(R.id.textScore);
        scoreText.setText("Score: "+ score + "/" + attempts);
    }

    private void showAnswers(int index) {

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

    //Sjølv timeren som telle ned fra 30 sek.
    public void startInactivityTimer() {
        //noke greier her
        progressBar.setMax(DELAY);
    }
    public void startTimer() {
        TextView timer = findViewById(R.id.timer);
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("Seconds remaining: " + millisUntilFinished + "s");
            }

            @Override
            public void onFinish() {
                attempts++;
                showAnswers(correctInt);
                updateScore();
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(true);
            }
        };

    }

    public boolean isCorrect (int position) {
        return position == correctInt;
    }

    /**
    private ArrayList<Animal> getNewQuestion() {
        shuffle(db.list);
        ArrayList<Animal> threeNames = new ArrayList<>();
        threeNames.add(db.getList().get(0));
        threeNames.add(db.getList().get(1));
        threeNames.add(db.getList().get(2));
        return threeNames;
    }
     */
}
