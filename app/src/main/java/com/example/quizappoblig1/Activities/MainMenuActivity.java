package com.example.quizappoblig1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

import com.example.quizappoblig1.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Switch sw = findViewById(R.id.switch1);


        Button quizButton = findViewById(R.id.quiz);
        quizButton.setOnClickListener((v) -> {
            openNewActivity(QuizActivity.class, sw.isChecked());
        });
        Button addEntryButton = findViewById(R.id.addentry);
        addEntryButton.setOnClickListener((v) -> {
            openNewActivity(AddEntryActivity.class);
        });
    }

    public void openNewActivity(Class act) {
        Intent intent = new Intent(this, act);
        startActivity(intent);
    }

    public void openNewActivity(Class act, Boolean sw) {
        Intent intent = new Intent(getApplicationContext(), act);
        intent.putExtra("switch", sw);
        startActivity(intent);
    }
}