package com.example.quizappoblig1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {
    private ArrayList<Animal> animalList;

    public AnimalAdapter(ArrayList<Animal> animalList) {
        this.animalList = animalList;
    }

    @Override
    @NonNull
    public AnimalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animaltemplate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.ViewHolder holder, int position) {
            Log.d("db", animalList.get(0).getName());
            holder.nameView.setText(animalList.get(position).getName());
            holder.imageView.setImageResource(animalList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.animalName);
            imageView = itemView.findViewById(R.id.animalImage);
        }
    }
}
