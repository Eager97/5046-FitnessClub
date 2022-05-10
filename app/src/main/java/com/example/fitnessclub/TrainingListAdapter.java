package com.example.fitnessclub;

import android.media.browse.MediaBrowser;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.fitnessclub.entity.Training;

public class TrainingListAdapter extends ListAdapter<Training, TrainingViewHolder> {

    public TrainingListAdapter(@NonNull DiffUtil.ItemCallback<Training> diffCallback){
        super(diffCallback);
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TrainingViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
        Training currentTraining = getItem(position);
        holder.bind(currentTraining.getTrainingName(), (int) currentTraining.getTrainingDuration());
    }

    // ignore duplicate training
    static class TrainingDiff extends DiffUtil.ItemCallback<Training>{

        @Override
        public boolean areItemsTheSame(@NonNull Training oldItem, @NonNull Training newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Training oldItem, @NonNull Training newItem) {
            return oldItem.getTrainingName().equals(newItem.getTrainingName());
        }
    }
}
