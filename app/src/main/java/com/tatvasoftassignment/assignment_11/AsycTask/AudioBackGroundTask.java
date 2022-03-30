package com.tatvasoftassignment.assignment_11.AsycTask;

import android.os.Handler;
import android.os.Looper;

import com.tatvasoftassignment.assignment_11.Model.Audios;

import java.util.ArrayList;

public abstract class AudioBackGroundTask {

    protected Handler localHandler;
    protected Thread localThread;

    public void execute(){
        this.localHandler = new Handler(Looper.getMainLooper());
        this.onPreExecute();

        this.localThread = new Thread(() -> {
            ArrayList<Audios> arrayList = AudioBackGroundTask.this.doInBackground();

            AudioBackGroundTask.this.localHandler.post(() -> AudioBackGroundTask.this.onPostExecute(arrayList));
        });
        this.localThread.start();
    }



    protected void onPreExecute() {}

    protected abstract ArrayList<Audios> doInBackground(Void... Voids);

    protected void onPostExecute(ArrayList arrayList) {}
}
