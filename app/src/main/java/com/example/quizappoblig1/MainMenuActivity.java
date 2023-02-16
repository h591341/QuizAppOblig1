package com.example.quizappoblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

public class MainMenuActivity extends AppCompatActivity {

    Database db;
    Bundle bundle;
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
        bundle = new Bundle();
        bundle.putParcelable("db", db);

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
        //intent.putParcelableArrayListExtra("dbase", db.getList());
        //intent.putExtra("dbase", bundle);
        startActivity(intent);
    }

    public void openNewActivity(Class act, Boolean sw) {
        Intent intent = new Intent(getApplicationContext(), act);
        // intent.putParcelableArrayListExtra("dbase", db.getList());
        // intent.putExtra("dbase", bundle);
        intent.putExtra("switch", sw);
        startActivity(intent);
    }
}