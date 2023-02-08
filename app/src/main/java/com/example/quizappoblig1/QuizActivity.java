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

import java.util.Random;

//hey ho

public class QuizActivity extends AppCompatActivity {

    ImageView image;
    Context context;
    private List<Bitmap> imageList;
    Random rnd = new Random();
    Database db = new Database(context);
    Button btn1;
    Button btn2;
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //get views

        image = findViewById(R.id.image);
        btn1 = findViewById(R.id.alt1);
        btn2 = findViewById(R.id.alt2);
        btn3 = findViewById(R.id.alt3);

        btn1.setOnClickListener((View.OnClickListener) this);
        btn2.setOnClickListener((View.OnClickListener) this);
        btn3.setOnClickListener((View.OnClickListener) this);




    }
    public void UserData(Context context) {
        this.context = context;
    }


    public List<String> getFilenames() {
        String[] fromDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).list();
        List<String> filenames = new ArrayList<>();
        assert fromDir != null;
        Collections.addAll(filenames, fromDir);
        return filenames;

    }


    public List<Bitmap> bitmapList() {
        List<String> imageFiles = getFilenames();
        List<Bitmap> bmList = new ArrayList<>();
        for (String file : imageFiles) {
            String path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + file;
            System.out.println("path: " + path);
            Bitmap b = BitmapFactory.decodeFile(path);
            Bitmap scaled = Bitmap.createScaledBitmap(b, 450, 500, true);
            bmList.add(scaled);
        }
        return bmList;
    }

    public void newQuestion() {
        ArrayList<Animal> liste = db.getNewQuestion();

        image.setImageBitmap(liste.get(0).getImage());
        btn1.setText(liste.get(0).getName());
        btn2.setText(liste.get(1).getName());
        btn3.setText(liste.get(2).getName());
    }

    public int getPosition() {
        return rnd.nextInt(imageList.size());
    }

}