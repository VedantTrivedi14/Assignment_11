package com.tatvasoftassignment.assignment_11.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tatvasoftassignment.assignment_11.Model.Audios;
import com.tatvasoftassignment.assignment_11.databinding.RowAudioBinding;

import java.util.ArrayList;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    ArrayList<Audios> arrayList;
    Context context;
    RowAudioBinding binding;

    public AudioAdapter(ArrayList<Audios> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowAudioBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AudioAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioAdapter.ViewHolder holder, int position) {
        holder.binding.txtAudioName.setText(arrayList.get(position).getAudioName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RowAudioBinding binding;

        public ViewHolder(@NonNull RowAudioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
