package com.example.fitnessclub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TrainingViewHolder extends RecyclerView.ViewHolder{

    private final TextView trainingItemView;

    private TrainingViewHolder(View itemView){
        super(itemView);
        trainingItemView = itemView.findViewById(R.id.textView);

    }

    public void bind(String trainingName,int trainingDuration){
        trainingItemView.setText(trainingName+ ":" +trainingDuration + "minutes");
    }

    static TrainingViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item,parent,false);
        return new TrainingViewHolder(view);
    }
}
