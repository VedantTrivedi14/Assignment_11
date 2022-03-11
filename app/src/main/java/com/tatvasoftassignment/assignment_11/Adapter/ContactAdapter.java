package com.tatvasoftassignment.assignment_11.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tatvasoftassignment.assignment_11.Model.Contacts;
import com.tatvasoftassignment.assignment_11.databinding.RowContactBinding;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    ArrayList<Contacts> arrayList;
    Context context;
    RowContactBinding binding;


    public ContactAdapter(ArrayList<Contacts> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = RowContactBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.binding.txtContactName.setText(arrayList.get(position).getContactName());
        holder.binding.txtContactNumber.setText(arrayList.get(position).getContactNumber());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RowContactBinding binding;

        public ViewHolder(@NonNull RowContactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
