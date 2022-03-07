package com.tatvasoftassignment.assignment_11.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tatvasoftassignment.assignment_11.Adapter.AudioAdapter;
import com.tatvasoftassignment.assignment_11.databinding.FragmentAudioBinding;


public class AudioFragment extends Fragment {
    Context context;
    FragmentAudioBinding binding;

    public AudioFragment(Context context) {

        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onResume() {
        super.onResume();
        setAudio();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAudioBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void setAudio() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                getAudio();
            }
        } else {
            getAudio();
        }
    }

    public void getAudio() {
        binding.audioRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.audioRecyclerView.setAdapter(new AudioAdapter(context));
    }
}