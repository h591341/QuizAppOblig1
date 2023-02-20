package com.example.quizappoblig1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AnimalAdapter extends ArrayAdapter<Animal> {

    ArrayList<Animal> animalList = new ArrayList<>();
    public AnimalAdapter(Context context, int textViewResourceId, ArrayList<Animal> animals) {
        super(context, textViewResourceId, animals);
        animalList = animals;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_add_entry, null);
        TextView textView = (TextView) view.findViewById(R.id.animalName);
        Log.d("textView", textView.toString());
        ImageView imageView = (ImageView) view.findViewById(R.id.animalImage);
        textView.setText(animalList.get(position).getName());
        imageView.setImageResource(animalList.get(position).getImage());
        return view;
    }
}
