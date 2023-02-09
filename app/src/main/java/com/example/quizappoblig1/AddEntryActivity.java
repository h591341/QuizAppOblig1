package com.example.quizappoblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class AddEntryActivity extends AppCompatActivity {

    Button imageBtn;
    ImageView imagePreview;
    int select_picture = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);


        Button addPicture = findViewById(R.id.addPicture);
        addPicture.setOnClickListener(v -> {
            selectImage();
        });

        imagePreview = findViewById(R.id.imagePreview);

        // BRUK MEDIAFILE
        // FÅ THUMBNAIL OG URI FOR FIL
        // SPØR OM FIL, HENT DEN
        // Images.Media
        // MediaStore.Images.Media... |URI|

        EditText pictureText = findViewById(R.id.pictureName);
        pictureText.setOnClickListener(this::showSoftKeyboard);

    }
    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), select_picture);
    }
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
