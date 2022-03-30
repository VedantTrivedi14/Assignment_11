package com.tatvasoftassignment.assignment_11.AsycTask;

import android.os.Handler;
import android.os.Looper;

import com.tatvasoftassignment.assignment_11.Model.Contacts;

import java.util.ArrayList;

public abstract class ContactsBackGroundTask {

    protected Handler localHandler;
    protected Thread localThread;

    public void execute(){
        this.localHandler = new Handler(Looper.getMainLooper());
        this.onPreExecute();

        this.localThread = new Thread(() -> {
            ArrayList<Contacts> arrayList = ContactsBackGroundTask.this.doInBackground();

            ContactsBackGroundTask.this.localHandler.post(() -> ContactsBackGroundTask.this.onPostExecute(arrayList));
        });
        this.localThread.start();
    }

    protected void onPreExecute() {}

    protected abstract ArrayList<Contacts> doInBackground(Void... Voids);

    protected void onPostExecute(ArrayList arrayList) {}
}
