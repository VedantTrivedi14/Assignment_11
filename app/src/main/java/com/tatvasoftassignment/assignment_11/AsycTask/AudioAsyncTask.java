package com.tatvasoftassignment.assignment_11.AsycTask;



import static com.tatvasoftassignment.assignment_11.Fragment.AudioFragment.binding;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.tatvasoftassignment.assignment_11.Adapter.AudioAdapter;
import com.tatvasoftassignment.assignment_11.Model.Audios;
import com.tatvasoftassignment.assignment_11.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AudioAsyncTask extends AudioBackGroundTask {

    Context context;
    ProgressDialog progressDialog;

    ArrayList<Audios> audioArrayList = new ArrayList<>();
    AudioAdapter audioAdapter;

    public AudioAsyncTask(Context context){
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getString(R.string.title));
        progressDialog.setMessage(context.getString(R.string.message));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected ArrayList doInBackground(Void... voids) {

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(
                uri, null, null, null, null
        );


        if (cursor != null && cursor.moveToNext()) {
            int title_of_audio = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            do {
                String title = cursor.getString(title_of_audio);
                Audios audio = new Audios();
                audio.setAudioName(title);
                audioArrayList.add(audio);
            } while (cursor.moveToNext());
        }
        assert cursor != null;
        cursor.close();
        return audioArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        progressDialog.dismiss();
        binding.audioRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        audioAdapter= new AudioAdapter(arrayList, context);
        binding.audioRecyclerView.setAdapter(audioAdapter);
    }
}
