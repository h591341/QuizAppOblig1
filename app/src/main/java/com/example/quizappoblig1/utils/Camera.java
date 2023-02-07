package com.example.quizappoblig1.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Camera {
    private TextView name;
    private Context context;
    private Activity activity;

    //Konstruktør
    public Camera(Context context, Activity activity, TextView name) {
        this.context = context;
        this.activity = activity;
        this.name = name;
    }

    //Lagrar bilde til fil i tlf (lagrar ikkje bilde, men som fil)
    public File createImageFile(String path) throws IOException {
        //Kor bilda blir lagra // må finne path
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(path, "jpg", storageDir);
    }

    //Lage filnamn
    public String createFilename () {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    return name.getText() + "-" + timeStamp + "-";
    }

    //Ta sjølve bilde
    public void takePicture () {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            int REQUEST_TAKE_PHOTO = 1;
            activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

}
