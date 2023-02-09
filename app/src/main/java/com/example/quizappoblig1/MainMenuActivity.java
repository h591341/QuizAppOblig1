package com.example.quizappoblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

import java.io.Serializable;

public class MainMenuActivity extends AppCompatActivity {

    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Switch sw = findViewById(R.id.switch1);
        /**    sw.setOnClickListener((v) -> {
         if(sw.isChecked()) {
         sw.setChecked(false);
         } else {
         sw.setChecked(true);
         }
         });
         */

        db = new Database();

        Button quizButton = findViewById(R.id.quiz);
        quizButton.setOnClickListener((v) -> {
            openNewActivity(QuizActivity.class, sw.isChecked(), db);
        });
        Button addEntryButton = findViewById(R.id.addentry);
        addEntryButton.setOnClickListener((v) -> {
            openNewActivity(AddEntryActivity.class, db);
        });
    }

    public void openNewActivity(Class act, Database database) {
        Intent intent = new Intent(this, act);
        //intent.putExtra("hvl.com.KEY_NAME", database);
        startActivity(intent);
    }

    public void openNewActivity(Class act, Boolean sw, Database database) {
        Intent intent = new Intent(getApplicationContext(), act);
       // intent.putExtra("hvl.com.KEY_NAME", database);
        intent.putExtra("switch", sw);
        startActivity(intent);
    }
}