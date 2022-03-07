package com.tatvasoftassignment.assignment_11.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tatvasoftassignment.assignment_11.Model.Contacts;
import com.tatvasoftassignment.assignment_11.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
        ArrayList<Contacts> arrayList;
        Context context;

public ContactAdapter(ArrayList<Contacts> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        }
@NonNull
public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_conteact, parent, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.txtContactName.setText(arrayList.get(position).getContactName());
        holder.txtContactNumber.setText(arrayList.get(position).getContactNumber());

        }

@Override
public int getItemCount() {
        return arrayList.size();
        }

public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtContactName, txtContactNumber;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        txtContactName = itemView.findViewById(R.id.txtContactName);
        txtContactNumber = itemView.findViewById(R.id.txtContactNumber);
    }
}
}
