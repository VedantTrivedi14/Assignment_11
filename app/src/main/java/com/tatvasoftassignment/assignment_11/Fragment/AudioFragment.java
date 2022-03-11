package com.tatvasoftassignment.assignment_11.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.tatvasoftassignment.assignment_11.AsycTask.AudioAsyncTask;
import com.tatvasoftassignment.assignment_11.databinding.FragmentAudioBinding;


public class AudioFragment extends Fragment {

    public static FragmentAudioBinding binding;
    Context context;

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
                binding.audioDummyText.setVisibility(View.INVISIBLE);
            } else {
                binding.audioDummyText.setVisibility(View.VISIBLE);
            }
        } else {

            getAudio();
        }
    }

    public void getAudio() {
        AudioAsyncTask asyncTask = new AudioAsyncTask(context);
        asyncTask.execute();
    }
}