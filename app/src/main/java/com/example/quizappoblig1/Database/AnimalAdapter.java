package com.example.quizappoblig1.Database;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import com.example.quizappoblig1.R;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {
    private List<Animal> animalList;

    public AnimalAdapter(AnimalDatabase db) {
        super();
        animalList = db.animalDao().getAnimalList2();
    }

    @Override
    @NonNull
    public AnimalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animaltemplate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if(animalList.size() == 0) return;
            holder.nameView.setText(animalList.get(position).getName());
            byte[] picture = animalList.get(position).getImage();
            holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(picture, 0, picture.length));
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
