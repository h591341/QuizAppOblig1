package com.example.quizappoblig1;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.Context;

import android.graphics.Bitmap;

import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class QuizActivity extends AppCompatActivity {

    ImageView image;
    Context context;
    private List<Bitmap> imageList;
    private List<Bitmap> imagesList;
    Random rnd = new Random();
    Database db;
    Button btn1;
    Button btn2;
    Button btn3;

    Integer score = 0;
    Integer attempts = 0;
    int correctInt;
    TextView scoreText;
    Button correctButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //get views
        db = new Database();
        image = findViewById(R.id.image);
        btn1 = findViewById(R.id.alt0);

        btn2 = findViewById(R.id.alt1);
        btn3 = findViewById(R.id.alt2);

        Button btn4 = findViewById(R.id.next);
        TextView scoreText = findViewById(R.id.Textscore);

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

    public List<String> getFilenames() {
        String[] fromDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).list();
        List<String> filenames = new ArrayList<>();
        assert fromDir != null;
        Collections.addAll(filenames, fromDir);
        return filenames;
    }

    public void newQuestion() {
        ArrayList<Animal> liste = db.getNewQuestion();
        correctInt = rnd.nextInt(db.list.size());
        if(correctInt == 0) {
            image.setImageResource(liste.get(correctInt).getImage());
            btn1.setText(liste.get(correctInt).getName());
            btn2.setText(liste.get(1).getName());
            btn3.setText(liste.get(2).getName());
            correctButton = findViewById(R.id.alt0);
        } else if(correctInt == 1 ) {
            image.setImageResource(liste.get(correctInt).getImage());
            btn2.setText(liste.get(0).getName());
            btn1.setText(liste.get(correctInt).getName());
            btn3.setText(liste.get(2).getName());
            correctButton = findViewById(R.id.alt1);
        } else {
            image.setImageResource(liste.get(correctInt-1).getImage());
            btn2.setText(liste.get(0).getName());
            btn3.setText(liste.get(1).getName());
            btn1.setText(liste.get(correctInt).getName());
            correctButton = findViewById(R.id.alt2);
        }
        btn1.setBackgroundColor(getResources().getColor(R.color.blue));
        btn2.setBackgroundColor(getResources().getColor(R.color.blue));
        btn3.setBackgroundColor(getResources().getColor(R.color.blue));
        btn2.setEnabled(true);
        btn1.setEnabled(true);
        btn3.setEnabled(true);

    }

    public int getPosition() {
        return rnd.nextInt(imagesList.size());
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
    }

    private void updateScore() {
        scoreText = findViewById(R.id.Textscore);
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

    public boolean isCorrect (int position) {
        return position == correctInt;
    }
}