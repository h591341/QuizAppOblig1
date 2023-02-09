package com.example.quizappoblig1;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizappoblig1.Database;

import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    ImageView image;
    Context context;
    private List<Bitmap> imageList;
    private List<String> nameList;
    private List<Bitmap> imagesList;
    Random rnd = new Random();
    Database db = new Database();
    Button btn1;
    Button btn2;
    Button btn3;

    Integer score = 0;
    Integer attempts = 0;
    int correctInt;
    TextView scoreText;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //get views

        image = findViewById(R.id.image);
        btn1 = findViewById(R.id.alt0);
        btn2 = findViewById(R.id.alt1);
        btn3 = findViewById(R.id.alt2);
        Button btn4 = findViewById(R.id.next);
        TextView scoreText = findViewById(R.id.score);

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





    public List<String> getFilenames() {
        String[] fromDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).list();
        List<String> filenames = new ArrayList<>();
        assert fromDir != null;
        Collections.addAll(filenames, fromDir);
        return filenames;

    }



    public void newQuestion() {
        ArrayList<Animal> liste = db.getNewQuestion();
        correctInt = rnd.nextInt(imageList.size());
        if(correctInt == 0) {
            image.setImageResource(liste.get(correctInt).getImage());
            btn1.setText(liste.get(correctInt).getName());
            btn2.setText(liste.get(1).getName());
            btn3.setText(liste.get(2).getName());
        } else if(correctInt == 1 ) {
            image.setImageResource(liste.get(correctInt).getImage());
            btn1.setText(liste.get(correctInt).getName());
            btn2.setText(liste.get(0).getName());
            btn3.setText(liste.get(2).getName());
        } else {
            image.setImageResource(liste.get(correctInt).getImage());
            btn1.setText(liste.get(correctInt).getName());
            btn2.setText(liste.get(0).getName());
            btn3.setText(liste.get(1).getName());
        }
    }

    public int getPosition() {
        return rnd.nextInt(imagesList.size());
    }


    public void guess(int index) {
        if (isCorrect(index)) {
            score++;
        }
        attempts++;
        updateScore();
    }

    public void updateScore() {
        scoreText = findViewById(R.id.score);
        scoreText.setText("SCORE: " + score + "/" + attempts);

    }

    public boolean isCorrect (int position) {
        return position == correctInt;
    }
}