package com.example.quizappoblig1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.quizappoblig1.Database.Animal;
import com.example.quizappoblig1.Database.AnimalAdapter;
import com.example.quizappoblig1.Database.AnimalDatabase;
import com.example.quizappoblig1.R;
import com.example.quizappoblig1.ViewModels.AnimalAsyncTask;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AddEntryActivity extends AppCompatActivity {

    ImageView imagePreview;
    int select_picture = 200;
    AnimalDatabase db;
    private RecyclerView rView;
    private AnimalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        Button addPicture = findViewById(R.id.addPicture);
        addPicture.setOnClickListener(v -> selectImage());
        db = AnimalDatabase.getInstance(this);


        imagePreview = findViewById(R.id.imagePreview);
        rView = findViewById(R.id.animalList);
        rView.setLayoutManager(new LinearLayoutManager(this));

        EditText pictureText = findViewById(R.id.pictureName);
        pictureText.setOnClickListener(this::showSoftKeyboard);

        AnimalAsyncTask repository = new AnimalAsyncTask(db.animalDao(), new AnimalAsyncTask.AsyncResponse() {
            @Override
            public void processFinish(List<Animal> output) {

            }
        });
        adapter = new AnimalAdapter(repository);
        rView.setAdapter(adapter);

        Button addEntry = findViewById(R.id.submitEntry);
        addEntry.setOnClickListener((v) -> {
            if(!pictureText.getText().toString().isEmpty() && imagePreview.getId() != -1) {
                try {
                    Bitmap bitmap = ((BitmapDrawable) imagePreview.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageInByte = baos.toByteArray();
                    Animal animal = new Animal(pictureText.getText().toString(), imageInByte);
                    db.animalDao().insertAnimal(animal);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.d("addEntry", "Noe gikk feil i 'addEntry' eller 'new Animal");
                }
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
