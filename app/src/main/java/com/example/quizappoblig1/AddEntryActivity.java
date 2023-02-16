package com.example.quizappoblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;


public class AddEntryActivity extends AppCompatActivity {

    ImageView imagePreview;
    int select_picture = 200;

    String name;
    int image;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        //ArrayList<Animal> db = getIntent().getParcelableArrayListExtra("dbase", Animal.class);
        // Log.d("SATAN", db.get(0).getName());
        Button addPicture = findViewById(R.id.addPicture);
        addPicture.setOnClickListener(v -> selectImage());
        db = new Database();
        imagePreview = findViewById(R.id.imagePreview);

        // BRUK MEDIAFILE
        // FÅ THUMBNAIL OG URI FOR FIL
        // SPØR OM FIL, HENT DEN
        // Images.Media
        // MediaStore.Images.Media... |URI|

        EditText pictureText = findViewById(R.id.pictureName);
        pictureText.setOnClickListener(this::showSoftKeyboard);

        Button addEntry = findViewById(R.id.submitEntry);
        addEntry.setOnClickListener((v) -> {
            if(!pictureText.getText().toString().isEmpty() && imagePreview.getId() != -1) {
                try {
                    db.addEntry(new Animal(pictureText.getText().toString(), imagePreview.getId()));
                } catch (Exception e) {
                    Log.d("addEntry", "Noe gikk feil i 'addEntry' eller 'new Animal");
                }
                Log.d("addEntryM", db.getList().get(4).getName());
                Log.d("addEntryM", "" + db.getList().size());
            }
            });

       }
    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), select_picture);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == select_picture) {
                Uri selectedImageUri = data.getData();
                if(null != selectedImageUri) {
                    imagePreview.setImageURI(selectedImageUri);
                }
            }
        }
    }

    public void showSoftKeyboard(View view) {
        if(view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
